package pe.edu.upc.doggystyle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.models.PetEntry;
import pe.edu.upc.doggystyle.models.User;

public class RegisterActivity extends AppCompatActivity {
    Button registerButton;
    TextInputEditText nameTextInputEditText;
    TextInputEditText lastNameTextInputEditText;
    TextInputEditText phoneTextInputEditText;
    TextInputEditText emailTextInputEditText;
    TextInputEditText passwordTextInputEditText;
    TextInputEditText addressTextInputEditText;
    TextInputEditText descriptionTextInputEditText;
    TextInputEditText capacityTextInputEditText;
    TextInputEditText availableCapacityTextInputEditText;

    CheckBox isShelterCheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nameTextInputEditText=(TextInputEditText)findViewById(R.id.nameTextInputEditText);
        lastNameTextInputEditText=(TextInputEditText)findViewById(R.id.lastNameTextInputEditText);
        phoneTextInputEditText=(TextInputEditText)findViewById(R.id.phoneTextInputEditText);
        emailTextInputEditText=(TextInputEditText)findViewById(R.id.emailTextInputEditText);
        passwordTextInputEditText=(TextInputEditText)findViewById(R.id.passwordTextInputEditText);
        addressTextInputEditText=(TextInputEditText)findViewById(R.id.addressTextInputEditText);
        descriptionTextInputEditText= (TextInputEditText) findViewById(R.id.descriptionTextInputEditText);
        capacityTextInputEditText = (TextInputEditText) findViewById(R.id.capacityTextInputEditText);
        registerButton = (Button)findViewById(R.id.registerButton);
        isShelterCheckBox = (CheckBox) findViewById(R.id.isShelterCheckBox);
        isShelterCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)                {
                    descriptionTextInputEditText.setVisibility(View.VISIBLE);
                    capacityTextInputEditText.setVisibility(View.VISIBLE);
                }
                else{
                    descriptionTextInputEditText.setVisibility(View.GONE);
                    capacityTextInputEditText.setVisibility(View.GONE);
                }
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearUsuario();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private boolean crearUsuario(){
        final User user = new User().setName(nameTextInputEditText.getText().toString())
                .setAddress(addressTextInputEditText.getText().toString())
                .setEmail(emailTextInputEditText.getText().toString())
                .setLastName(lastNameTextInputEditText.getText().toString())
                .setPhone(phoneTextInputEditText.getText().toString())
                .setPassword(passwordTextInputEditText.getText().toString())
                .setState("ACT")
                .setType(isShelterCheckBox.isChecked()? "2":"1");
        if(user.getEmail().isEmpty() || user.getPassword().isEmpty())
        {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, "Existen campos sin completar", Toast.LENGTH_SHORT);
            toast.show();
        }
        if(isShelterCheckBox.isChecked())
        {
            user.setCapacity(capacityTextInputEditText.getText().toString())
                .setDescription(descriptionTextInputEditText.getText().toString())
                .setAvailableCapacity(capacityTextInputEditText.getText().toString());
        }

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("UserName", user.getEmail());
            jsonObject.put("Password", user.getPassword());
            jsonObject.put("Email", user.getEmail());
            jsonObject.put("Name", user.getLastName());
            jsonObject.put("LastName", user.getLastName());
            jsonObject.put("Address", user.getAddress());
            jsonObject.put("Phone", user.getPhone());
            jsonObject.put("Type",isShelterCheckBox.isChecked()? 2:1 );
            jsonObject.put("Description", user.getDescription());
            jsonObject.put("Capacity", user.getCapacity());
            jsonObject.put("AviableCapacity",user.getCapacity());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.put("http://doggystyle.vcsoft.pe/api/Users/0")
                .addJSONObjectBody(jsonObject) // posting json
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                        Context context = getApplicationContext();
                        Toast toast = Toast.makeText(context, "El usuario ha sido creado", Toast.LENGTH_SHORT);
                        toast.show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        finish();
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("Error", error.getLocalizedMessage());
                    }
                });
        return true;
    }


}
