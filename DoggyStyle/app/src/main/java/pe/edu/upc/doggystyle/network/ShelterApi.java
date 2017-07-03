package pe.edu.upc.doggystyle.network;

import pe.edu.upc.doggystyle.utilities.Constants;

/**
 * Created by Ricardo on 03/07/2017.
 */

public class ShelterApi {
    public static String PET_POST = Constants.URL_SERVER + "/Pets/0";
    public String getShelterURL(){
        return  Constants.URL_SERVER+ "/shelter";
    }
    public String putShelterURL(int petId){
        return  Constants.URL_SERVER+ "/"+"/Pets/"+Integer.toString(petId);
    }
}
