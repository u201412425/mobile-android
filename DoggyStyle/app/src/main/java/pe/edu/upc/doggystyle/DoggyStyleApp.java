package pe.edu.upc.doggystyle;

import android.app.Application;
import com.androidnetworking.AndroidNetworking;
/**
 * Created by Hypnotic on 20/06/2017.
 */

public class DoggyStyleApp extends Application {

    private static DoggyStyleApp instance;

    public  DoggyStyleApp(){
        super();
        instance = this;
    }

    public static DoggyStyleApp getInstance(){ return instance;}

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
