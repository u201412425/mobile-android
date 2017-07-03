package pe.edu.upc.doggystyle.models;

import android.support.annotation.Nullable;
import android.text.Editable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo on 21/06/2017.
 */

public class Shelter {
    int shelterId;
    String name;
    String phone;
    String description;
    String State;
    int capacity;
    int avialableCapacity;

    private String imagenUrl;

    public Shelter() {
    }

    public Shelter(int shelterId, String name, String phone, String description, String state, int capacity, int avialableCapacity) {
        this.shelterId = shelterId;
        this.name = name;
        this.phone = phone;
        this.description = description;
        this.State = state;
        this.capacity = capacity;
        this.avialableCapacity = avialableCapacity;
    }

    public static List<Shelter> build(JSONArray jsonPetEntries) {
        List<Shelter> shelters = new ArrayList<>();

        for (int i = 0; i < jsonPetEntries.length(); i++)
            try {
                shelters.add(build(jsonPetEntries.getJSONObject(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        return shelters;
    }

    public static Shelter build(JSONObject jsonShelterEntry) {
        Shelter shelter = new Shelter();
        try {
            shelter.setShelterId(jsonShelterEntry.getInt("PetShelterId"))
                    .setName(jsonShelterEntry.getString("Name"))
                    .setPhone(jsonShelterEntry.getString("Phone"))
                    .setDescription(jsonShelterEntry.getString("Description"))
                    .setState(jsonShelterEntry.getString("State"))
                    .setCapacity(jsonShelterEntry.getInt("Capacity"))
                    .setAvialableCapacity(jsonShelterEntry.getInt("AviableCapacity"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return shelter;
    }

    public int getShelterId() {
        return shelterId;
    }

    public Shelter setShelterId(int shelterId) {
        this.shelterId = shelterId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Shelter setName(String name) {
        this.name = name;
        return this;
    }

    public Shelter setName(Editable name) {
        this.name = name.toString();
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Shelter setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Shelter setPhone(Editable phone) {
        this.phone = phone.toString();
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Shelter setDescription(String description) {
        this.description = description;
        return this;
    }

    public Shelter setDescription(Editable description) {
        this.description = description.toString();
        return this;
    }

    public String getState() {
        return State;
    }

    public Shelter setState(String state) {
        State = state;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public Shelter setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public int getAvialableCapacity() {
        return avialableCapacity;
    }

    public Shelter setAvialableCapacity(int avialableCapacity) {
        this.avialableCapacity = avialableCapacity;
        return this;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public Shelter setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
        return this;
    }
}
