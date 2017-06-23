package pe.edu.upc.doggystyle.utilities;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.doggystyle.models.PetEntry;
import pe.edu.upc.doggystyle.models.Shelter;

/**
 * Created by p6 on 18/05/2017.
 */

public class DataService {
    static DataService dataService;
    private List<PetEntry> adoptEntries;
    private List<PetEntry> myPetsEntries;
    private List<Shelter> shelterEntries;
    DataService(){
        adoptEntries = new ArrayList<>();
        myPetsEntries = new ArrayList<>();
        shelterEntries = new ArrayList<>();
        adoptEntries.add(new PetEntry("Collie",1993,11,0,"Tetanos"));
        adoptEntries.add(new PetEntry("Yorkshire",1995,12,10,"Rabia"));
        myPetsEntries.add(new PetEntry("San Bernardo",1990,11,0,"Rabia"));
        myPetsEntries.add(new PetEntry("Bulldog",1990,12,10,"Tetanos"));
        shelterEntries.add(new Shelter("San Jacinto", "Lima", "Surco"));
        shelterEntries.add(new Shelter("Santa Marta", "Lima", "San Juan de Lurigancho"));
    }
    public static DataService getInstance(){
        if(dataService==null) dataService = new DataService();
        return dataService;
    }

    public List<PetEntry> getAdoptEntries() {
        return adoptEntries;
    }

    public List<PetEntry> getMyPetsEntries() {
        return myPetsEntries;
    }

    public List<Shelter> getShelterEntries() {
        return shelterEntries;
    }
}
