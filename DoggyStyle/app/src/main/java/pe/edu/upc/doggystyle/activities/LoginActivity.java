package pe.edu.upc.doggystyle.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;


import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.models.Session;
import pe.edu.upc.doggystyle.network.LoginApi;
import pe.edu.upc.doggystyle.utilities.Constants;
import pe.edu.upc.doggystyle.utilities.SharedPreferencesManager;

public class LoginActivity extends AppCompatActivity {
    TextView registerBtnText;
    Button loginBtn;
    TextInputEditText userTextInputEditText;
    TextInputEditText passwordTextInputEditText;
    Session session;
    SharedPreferencesManager spm;
    LoginApi loginApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loginApi = new LoginApi();

        userTextInputEditText = (TextInputEditText)findViewById(R.id.user_input);
        passwordTextInputEditText = (TextInputEditText)findViewById(R.id.password_input);


        loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean connection;
                connection = haveNetworkConnection();

                if(connection){
                    Login();
                }
                else{

                }
            }
        });
        registerBtnText = (TextView) findViewById(R.id.register_btn_txt);
        registerBtnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }



    private void Login(){

        AndroidNetworking.get("http://doggystyle.vcsoft.pe/api/Session/")
                .addHeaders("User",userTextInputEditText.getText().toString())
                .addHeaders("Password",passwordTextInputEditText.getText().toString())
                .setTag("tesst")
                .setPriority(Priority.HIGH.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject result = response.getJSONObject("Result");
                            session = new Session();

                            session.setToken(result.getString(Constants.Session.TOKEN));
                            session.setId(Integer.parseInt(result.getString(Constants.Session.USERID)));
                            session.setRol(Integer.parseInt(result.getString(Constants.Session.TYPE)));
                            Context context = getApplicationContext();
                            Toast toast = Toast.makeText(context,result.getString(Constants.Session.USERID), Toast.LENGTH_SHORT);
                            toast.show();
                            if(!session.getToken().equals("")){
                                if(session.getRol() == 1){
                                    Intent intent = new Intent(LoginActivity.this,MyPetsActivity.class);
                                    startActivity(intent);
                                }else
                                {
                                    Intent intent = new Intent(LoginActivity.this,MyShelterActivity.class);
                                    startActivity(intent);
                                }

                            }

                        }catch (JSONException e){

                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d("s","fail");
                        Log.d("a",anError.getMessage());
                    }
                });
    }
}
