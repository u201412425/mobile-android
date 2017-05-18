package pe.edu.upc.doggystyle.models;

import java.util.Date;

/**
 * Created by p6 on 18/05/2017.
 */

public class PetEntry {
    private String name;
    private String[] vaccines;
    private int birthYear, birthMonth, birthDay;

    public PetEntry(String name, int birthYear, int birthMonth, int birthDay, String... vaccines) {
        this.name = name;
        this.vaccines = vaccines;
        this.birthYear = birthYear;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
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
}
