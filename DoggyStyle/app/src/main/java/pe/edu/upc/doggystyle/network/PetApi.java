package pe.edu.upc.doggystyle.network;

import pe.edu.upc.doggystyle.utilities.Constants;

/**
 * Created by goman on 7/2/2017.
 */

public class PetApi {
    public String getURL(int userId){
        return  Constants.URL_SERVER+ "/"+Integer.toString(userId)+"/Pets";
    }
}
