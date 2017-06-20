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
//
//    public SharedPreferencesManager(Context context) {
//        this.context = context;
//    }
//
//    public void saveUserOnPreferences(Session session){
//        sp = PreferenceManager.getDefaultSharedPreferences(context);
//        JSONObject userJson = new JSONObject();
//        try {
//            userJson.put("UserId",session.getUserId());
//            userJson.put("Name",user.getName());
//            userJson.put("LastName",user.getLastName());
//            userJson.put("Email",user.getEmail());
//            userJson.put("Role",user.getRole());
//            userJson.put("DocumentNumber",user.getDocumentNumber());
//            userJson.put("BusinessName",user.getBusinessName());
//            userJson.put("DNI",user.getDNI());
//        }catch (JSONException ex){
//            ex.printStackTrace();
//        }
//        sp.edit().putString("userSession",userJson.toString()).commit();
//    }
//
//    public User getUserOnPreferences(){
//        sp = PreferenceManager.getDefaultSharedPreferences(context);
//        User user = new User();
//        JSONObject userJson = null;
//        try {
//            userJson = new JSONObject(sp.getString("userSession",""));
//            if(!userJson.isNull("UserId")) user.setUserId(userJson.getInt("UserId"));
//            if(!userJson.isNull("Name")) user.setName(userJson.getString("Name"));
//            if(!userJson.isNull("Role")) user.setRole(userJson.getString("Role"));
//            if(!userJson.isNull("LastName")) user.setLastName(userJson.getString("LastName"));
//            if(!userJson.isNull("Email")) user.setEmail(userJson.getString("Email"));
//            if(!userJson.isNull("DocumentNumber")) user.setDocumentNumber(userJson.getString("DocumentNumber"));
//            if(!userJson.isNull("BusinessName")) user.setBusinessName(userJson.getString("BusinessName"));
//            if(!userJson.isNull("DNI")) user.setDNI(userJson.getString("DNI"));
//        }catch (JSONException ex){
//            ex.printStackTrace();
//        }
//        return user;

//    }
}
