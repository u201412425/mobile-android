package pe.edu.upc.doggystyle.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.edu.upc.doggystyle.DoggyStyleApp;
import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.models.PetEntry;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPetFragment extends Fragment {
    TextInputEditText nameTextInputEditText;
    TextInputEditText descriptionTextInputEditText;
    TextInputEditText ageTextInputEditText;
    TextInputEditText specialFeaturesTextInputEditText;
    Spinner typeSpinner;
    private OnAddPetFragmentInteractionListener mListener;
   public AddPetFragment(){

   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_pet, container, false);
        nameTextInputEditText= (TextInputEditText) view.findViewById(R.id.nameTextInputEditText);
        descriptionTextInputEditText = (TextInputEditText) view.findViewById(R.id.descriptionTextInputEditText);
        ageTextInputEditText = (TextInputEditText) view.findViewById(R.id.ageTextInputEditText);
        specialFeaturesTextInputEditText = (TextInputEditText) view.findViewById(R.id.specialFeaturesTextInputEditText);
        typeSpinner = (Spinner) view.findViewById(R.id.typeSpinner);


        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.addNewPetFloatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed();
            }
        });
        return view;
    }

    void InsertPet(){
        final PetEntry petEntry = new PetEntry().setNamePet(nameTextInputEditText.getText().toString())
                .setDescription(descriptionTextInputEditText.getText().toString())
                .setAge(Integer.valueOf(ageTextInputEditText.getText().toString()) )
                .setSpecialFeatures(specialFeaturesTextInputEditText.getText().toString())
                .setType(typeSpinner.getSelectedItem().toString())
                .setUserId(DoggyStyleApp.getInstance().getCurrentSession().getId());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("UserId",Integer.valueOf( petEntry.getUserId()));
            jsonObject.put("NamePet", petEntry.getNamePet());
            jsonObject.put("Description", petEntry.getDescription());
            jsonObject.put("Type", petEntry.getType());
            jsonObject.put("SpecialFeatures", petEntry.getSpecialFeatures());
            jsonObject.put("Age", petEntry.getAge());
            jsonObject.put("PetShelterId", null);
            jsonObject.put("State", "ACT");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.put("http://doggystyle.vcsoft.pe/api/Pets/0")
                .addJSONObjectBody(jsonObject) // posting json
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // do anything with response
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d("Error", error.getLocalizedMessage());
                    }
                });

    }

    public void onButtonPressed() {
        if (mListener != null) {
            try {
                InsertPet();
            } catch (Exception e) {
                e.printStackTrace();
            }
           mListener.OnAddPetFragmentInteractionListener();
        }
    }
    public interface OnAddPetFragmentInteractionListener {
        // TODO: Update argument type and name
        void OnAddPetFragmentInteractionListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddPetFragmentInteractionListener) {
            mListener = (OnAddPetFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

}
