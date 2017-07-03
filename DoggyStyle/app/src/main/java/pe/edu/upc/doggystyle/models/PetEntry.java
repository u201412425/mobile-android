package pe.edu.upc.doggystyle.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by p6 on 18/05/2017.
 */

public class PetEntry {
    private int petId;
    private int userId;
    private String namePet;
    private String description;
    private String state;
    private int type;
    private String specialFeatures;
    private String petShelterId;
    private int age;
    private String imagenUrl;
    private String name;
    private String[] vaccines;
    private int birthYear, birthMonth, birthDay;

    public PetEntry() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getVaccines() {
        return vaccines;
    }

    public void setVaccines(String[] vaccines) {
        this.vaccines = vaccines;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(int birthMonth) {
        this.birthMonth = birthMonth;
    }

    public int getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(int birthDay) {
        this.birthDay = birthDay;
    }




    public static PetEntry build(JSONObject jsonPetEntry) {
        if(jsonPetEntry == null) return null;
        try {
            List<String> sortBysAvailable = new ArrayList<>();
            return (new PetEntry()).setPetId(jsonPetEntry.getInt("PetId"))
                    .setUserId(jsonPetEntry.getInt("UserId"))
                    .setNamePet(jsonPetEntry.getString("NamePet"))
                    .setDescription(jsonPetEntry.getString("Description"))
                    .setNamePet(jsonPetEntry.getString("NamePet"))
                    .setState(jsonPetEntry.getString("State"))
                    .setType(jsonPetEntry.getInt("Type"))
                    .setSpecialFeatures(jsonPetEntry.getString("SpecialFeatures"))
                    .setPetShelterId(jsonPetEntry.getString("PetShelterId"))
                    .setAge(jsonPetEntry.getInt("Age"))
                    .setImagenUrl(jsonPetEntry.getString("ImagenUrl"));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<PetEntry> build(JSONArray jsonPetEntries) {
        List<PetEntry> sources = new ArrayList<>();

        int length = jsonPetEntries.length();
        for (int i = 0; i < length; i++)
            try {
                sources.add(PetEntry.build(jsonPetEntries.getJSONObject(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        return sources;
    }

    public int getPetId() {
        return petId;
    }

    public PetEntry setPetId(int petId) {
        this.petId = petId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public PetEntry setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getNamePet() {
        return namePet;
    }

    public PetEntry setNamePet(String namePet) {
        this.namePet = namePet;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PetEntry setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getState() {
        return state;
    }

    public PetEntry setState(String state) {
        this.state = state;
        return this;
    }

    public String getTypeString() {
        if(type==1)
            return "Dog";
        if(type==2)
            return "Cat";
        else
            return "I don't know";
    }
    public int getType(){
        return type;
    }
    public PetEntry setType(int type) {
        this.type = type;
        return this;
    }
    public PetEntry setType(String value) {
        if(value=="Dog")
            this.type = 1;
        if(value=="Cat")
            this.type = 2;
        else
            this.type = 1;

        return this;
    }
    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public PetEntry setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
        return this;
    }

    public String getPetShelterId() {
        return petShelterId;
    }

    public PetEntry setPetShelterId(String petShelterId) {
        this.petShelterId = petShelterId;
        return this;
    }

    public int getAge() {
        return age;
    }

    public PetEntry setAge(int age) {
        this.age = age;
        return this;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public PetEntry setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
        return this;
    }
}
