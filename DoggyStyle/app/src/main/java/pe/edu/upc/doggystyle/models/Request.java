package pe.edu.upc.doggystyle.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by goman on 7/6/2017.
 */

public class Request {
    private String name;
    private String description;
    private String adoptionRequestId;

    public String getName() {
        return name;
    }

    public Request setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Request setDescription(String description) {
        this.description = description;
        return this;
    }

    public static Request build(JSONObject jsonRequest) {
        if(jsonRequest == null) return null;
        try {
            return (new Request()).setDescription(jsonRequest.getString("Description"))
                    .setName(jsonRequest.getString("FullName"))
                    .setAdoptionRequestId(jsonRequest.getString("AdoptionRequestId"));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Request> build(JSONArray jsonRequests) {
        List<Request> requests = new ArrayList<>();

        int length = jsonRequests.length();
        for (int i = 0; i < length; i++)
            try {
                requests.add(Request.build(jsonRequests.getJSONObject(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        return requests;
    }

    public String getAdoptionRequestId() {
        return adoptionRequestId;
    }

    public Request setAdoptionRequestId(String adoptionRequestId) {
        this.adoptionRequestId = adoptionRequestId;
        return this;
    }
}
