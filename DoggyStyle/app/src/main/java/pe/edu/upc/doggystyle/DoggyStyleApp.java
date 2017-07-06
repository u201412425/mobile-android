package pe.edu.upc.doggystyle;

import android.app.Application;
import com.androidnetworking.AndroidNetworking;

import pe.edu.upc.doggystyle.models.PetEntry;
import pe.edu.upc.doggystyle.models.Session;
import pe.edu.upc.doggystyle.models.Shelter;
import pe.edu.upc.doggystyle.utilities.DataService;

/**
 * Created by Hypnotic on 20/06/2017.
 */

public class DoggyStyleApp extends Application {

    private static DoggyStyleApp instance;
    private DataService service= new DataService();

    public  DoggyStyleApp(){
        super();
        instance = this;
    }

    public static DoggyStyleApp getInstance(){ return instance;}
    public PetEntry getCurrentPet(){return getInstance().service.getCurrentPet();}
    public PetEntry getCurrentPetAdopt(){return getInstance().service.getCurrentPetAdopt();}
    public void setCurrentPet(PetEntry petEntry){
        getInstance().service.setCurrentPet(petEntry);}
    public void setCurrentPetAdopt(PetEntry petEntry) {getInstance().service.setCurrentPetAdopt(petEntry);}

    public Shelter getCurrentShelter() {
        return getInstance().service.getCurrentShelter();
    }
    public void setCurrentSession(Session session){
        getInstance().service.setCurrentSession(session);
    }

    public void setCurrentShelter(Shelter currentShelter){
        getInstance().service.setCurrentShelter(currentShelter);
    }
    public Session getCurrentSession() {
        return getInstance().service.getCurrentSession();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
