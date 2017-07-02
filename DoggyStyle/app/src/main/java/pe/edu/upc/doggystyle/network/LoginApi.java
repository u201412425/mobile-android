package pe.edu.upc.doggystyle.network;
import android.util.Log;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.doggystyle.models.Session;
import pe.edu.upc.doggystyle.utilities.Constants;
import pe.edu.upc.doggystyle.utilities.SharedPreferencesManager;

/**
 * Created by Hypnotic on 20/06/2017.
 */

public class LoginApi {
    private static String LOGIN_SOURCES = Constants.URL_SERVER + "/Session";
    private static String REGISTER_SOURCES = Constants.URL_SERVER + "/users/";
    private Session session = null;

    public  Session Login(EditText user,EditText password){


        AndroidNetworking.get(LoginApi.LOGIN_SOURCES)
                .addHeaders("User",user.getText().toString())
                .addHeaders("Password",password.getText().toString())
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
                            session.setToken(result.getString(Constants.Session.USERID));
                            session.setToken(result.getString(Constants.Session.TYPE));

                        }catch (JSONException e){

                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d("s","fail");
                        Log.d("a",anError.getMessage());
                    }
                });

        return session;
    }


}
