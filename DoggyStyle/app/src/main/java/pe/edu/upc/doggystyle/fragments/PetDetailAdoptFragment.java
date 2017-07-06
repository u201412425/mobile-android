package pe.edu.upc.doggystyle.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.widget.ANImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import pe.edu.upc.doggystyle.DoggyStyleApp;
import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.models.PetEntry;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PetDetailAdoptFragment.OnDetailAdoptFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PetDetailAdoptFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetDetailAdoptFragment extends Fragment {
    ANImageView petImageView;
    TextView petNameTextView;
    TextView petDescriptionTextView;
    TextView petTypeTextView;
    TextView petStateTextView;
    TextView petAgeViewTextView;
    TextView petSpecialFeaturesTextView;
    TextInputEditText adoptDescriptionTextInputEditText;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnDetailAdoptFragmentInteractionListener mListener;

    public PetDetailAdoptFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PetDetailAdoptFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PetDetailAdoptFragment newInstance(String param1, String param2) {
        PetDetailAdoptFragment fragment = new PetDetailAdoptFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pet_detail_adopt, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        petImageView = (ANImageView) view.findViewById(R.id.petImageView);
        petNameTextView = (TextView) view.findViewById(R.id.petNameTextView);
        petDescriptionTextView=(TextView) view.findViewById(R.id.petDescriptionTextView);
        petTypeTextView = (TextView) view.findViewById(R.id.petTypeTextView);
        petSpecialFeaturesTextView = (TextView) view.findViewById(R.id.petSpecialFeaturesTextView);
        petStateTextView = (TextView) view.findViewById(R.id.petStateTextView);
        petAgeViewTextView =(TextView) view.findViewById(R.id.petAgeTextView);
        adoptDescriptionTextInputEditText = (TextInputEditText) view.findViewById(R.id.adoptDescriptionTextInputEditText);

        PetEntry currentPet= DoggyStyleApp.getInstance().getCurrentPet();
        petImageView.setDefaultImageResId(R.drawable.ic_pets_black_24dp);
        petImageView.setErrorImageResId(R.drawable.ic_pets_black_24dp);
        petImageView.setImageUrl(currentPet.getImagenUrl());
        petNameTextView.setText(currentPet.getNamePet());
        petDescriptionTextView.setText(currentPet.getDescription());
        petTypeTextView.setText(currentPet.getTypeString());
        petSpecialFeaturesTextView.setText(currentPet.getSpecialFeatures());
        petStateTextView.setText(currentPet.getState());
        petAgeViewTextView.setText(String.valueOf(currentPet.getAge()) );


        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.adoptPetFloatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed();
            }
        });
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            adoptPet();
            mListener.OnDetailAdoptFragmentInteractionListener();
        }
    }

    void adoptPet(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("AdoptionRequestId", 0);
            jsonObject.put("UserId", DoggyStyleApp.getInstance().getCurrentSession().getId() );
            jsonObject.put("PetIdAdoptionId", DoggyStyleApp.getInstance().getCurrentPet().getPetId());
            jsonObject.put("Description", adoptDescriptionTextInputEditText.getText().toString());
            jsonObject.put("State", "ACT");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.put("http://doggystyle.vcsoft.pe/api/adoptionrequest/0")
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
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDetailAdoptFragmentInteractionListener) {
            mListener = (OnDetailAdoptFragmentInteractionListener) context;
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
    public interface OnDetailAdoptFragmentInteractionListener {
        // TODO: Update argument type and name
        void OnDetailAdoptFragmentInteractionListener();
    }
}
