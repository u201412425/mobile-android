package pe.edu.upc.doggystyle.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.doggystyle.models.Session;

/**
 * Created by Hypnotic on 20/06/2017.
 */

public class SharedPreferencesManager {
    Context context;
    SharedPreferences sp;

    public SharedPreferencesManager(Context context) {
        this.context = context;
    }

    public void saveUserOnPreferences(Session session){
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        JSONObject userJson = new JSONObject();
        try {
            userJson.put("Token",session.getToken());
            userJson.put("Id",session.getId());
            userJson.put("Rol",session.getRol());
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        sp.edit().putString("userSession",userJson.toString()).commit();
    }

    public Session getUserOnPreferences(){
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        Session user = new Session();
        JSONObject userJson = null;
        try {
            userJson = new JSONObject(sp.getString("userSession",""));
            if(!userJson.isNull("Token")) user.setToken(userJson.getString("Token"));
            if(!userJson.isNull("Id")) user.setId(userJson.getInt("Id"));
            if(!userJson.isNull("Rol")) user.setId(userJson.getInt("Rol"));
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return user;

    }
}
