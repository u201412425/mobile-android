package pe.edu.upc.doggystyle.models;

import android.text.Editable;

/**
 * Created by Ricardo on 21/06/2017.
 */

public class Shelter {
    String name;
    String city;
    String distric;

    public Shelter(String name, String city, String distric) {
        this.name = name;
        this.city = city;
        this.distric = distric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setName(Editable name) {
        this.name = name.toString();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public void setCity(Editable city) {
        this.city = city.toString();
    }

    public String getDistric() {
        return distric;
    }

    public void setDistric(String distric) {
        this.distric = distric;
    }
    public void setDistric(Editable distric) {
        this.distric = distric.toString();
    }

    public String getLocation() {
        return distric + ", " + city;
    }
}
