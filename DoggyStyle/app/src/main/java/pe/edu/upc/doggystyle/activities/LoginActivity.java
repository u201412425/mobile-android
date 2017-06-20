package pe.edu.upc.doggystyle.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.Response;
import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.network.LoginApi;

public class LoginActivity extends AppCompatActivity {
    TextView registerBtnText;
    Button loginBtn;
    TextInputEditText userTextInputEditText;
    TextInputEditText passwordTextInputEditText;


    //    SharedPreferencesManager spm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        userTextInputEditText = (TextInputEditText)findViewById(R.id.user_input);
        passwordTextInputEditText = (TextInputEditText)findViewById(R.id.password_input);

        final boolean connection;
        connection = haveNetworkConnection();
        loginBtn = (Button) findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(connection){
                    login();
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

    private void login()
    {

        AndroidNetworking.get(LoginApi.LOGIN_SOURCES)
                .addHeaders("User",userTextInputEditText.getText().toString())
                .addHeaders("Password",passwordTextInputEditText.getText().toString())
                .setTag("tesst")
                .setPriority(Priority.HIGH.MEDIUM)
                .build()
                .getAsOkHttpResponse(new OkHttpResponseListener() {
                    @Override
                    public void onResponse(Response response) {
                        String a = response.header("Token");
                        Context context = getApplicationContext();
                        Toast toast = Toast.makeText(context, a, Toast.LENGTH_SHORT);
                        toast.show();
//                            startActivity(new Intent(LoginActivity.this,MyPets.class));

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        startActivity(new Intent(LoginActivity.this,MyPets.class));
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//
//                    }
//                });


    }

}
