package pe.edu.upc.doggystyle.models;

/**
 * Created by Hypnotic on 20/06/2017.
 */

public class Session {
    private String token;
    private Integer id;
    private Integer rol;
    public  Session(){

    }

    public Session(String token, Integer id, Integer rol) {
        this.token = token;
        this.id = id;
        this.rol = rol;
    }

    public  Integer getRol()
    {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
