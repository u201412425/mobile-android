package pe.edu.upc.doggystyle.models;

/**
 * Created by goman on 7/5/2017.
 */

public class User {
    private String password;
    private String email;
    private String lastName;
    private String name;
    private String address;
    private String state;
    private String phone;
    private String description;
    private String capacity;
    private String availableCapacity;
    private String type;

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getState() {
        return state;
    }

    public User setState(String state) {
        this.state = state;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCapacity() {
        return capacity;
    }

    public User setCapacity(String capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getAvailableCapacity() {
        return availableCapacity;
    }

    public User setAvailableCapacity(String availableCapacity) {
        this.availableCapacity = availableCapacity;
        return this;
    }

    public String getType() {
        return type;
    }

    public User setType(String type) {
        this.type = type;
        return this;
    }
}
