package pe.edu.upc.doggystyle.fragments;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.doggystyle.DoggyStyleApp;
import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.models.PetEntry;
import pe.edu.upc.doggystyle.network.PetApi;

public class EditPetFragment extends Fragment {
    TextInputEditText nameTextInputEditText;
    TextInputEditText descriptionTextInputEditText;
    Spinner typeSpinner;
    TextInputEditText ageTextInputEditText;
    TextInputEditText specialFeaturesTextInputEditText;

    private OnEditPetFragmentInteractionListener mListener;

    public EditPetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_pet, container, false);
        nameTextInputEditText= (TextInputEditText) view.findViewById(R.id.nameTextInputEditText);
        descriptionTextInputEditText = (TextInputEditText) view.findViewById(R.id.descriptionTextInputEditText);
        ageTextInputEditText = (TextInputEditText) view.findViewById(R.id.ageTextInputEditText);
        specialFeaturesTextInputEditText = (TextInputEditText) view.findViewById(R.id.specialFeaturesTextInputEditText);
        typeSpinner = (Spinner) view.findViewById(R.id.typeSpinner);
        PetEntry currentPetEntry = DoggyStyleApp.getInstance().getCurrentPet();

        nameTextInputEditText.setText(currentPetEntry.getNamePet());
        descriptionTextInputEditText.setText(currentPetEntry.getDescription());
        ageTextInputEditText.setText(String.valueOf(currentPetEntry.getAge()) );
        specialFeaturesTextInputEditText.setText(currentPetEntry.getSpecialFeatures());
        typeSpinner.setSelection(currentPetEntry.getType()-1);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.editPetFloatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
            if (mListener != null) {
                EditPet();
                mListener.onEditPetFragmentInteraction();
            }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditPetFragmentInteractionListener) {
            mListener = (OnEditPetFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void EditPet(){
        final PetEntry petEntry = new PetEntry().setNamePet(nameTextInputEditText.getText().toString())
                .setDescription(descriptionTextInputEditText.getText().toString())
                .setAge(Integer.valueOf(ageTextInputEditText.getText().toString()) )
                .setSpecialFeatures(specialFeaturesTextInputEditText.getText().toString())
                .setType(typeSpinner.getSelectedItem().toString())
                .setUserId(DoggyStyleApp.getInstance().getCurrentPet().getUserId())
                .setPetId(DoggyStyleApp.getInstance().getCurrentPet().getPetId());
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

        PetApi petApi=new PetApi();
        AndroidNetworking.put(petApi.getURLPetPut(petEntry.getPetId()))
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnEditPetFragmentInteractionListener {
        // TODO: Update argument type and name
        void onEditPetFragmentInteraction();
    }
}
