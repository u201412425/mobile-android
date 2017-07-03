package pe.edu.upc.doggystyle.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import pe.edu.upc.doggystyle.DoggyStyleApp;
import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.models.PetEntry;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PetDetailFragment.OnDetailFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PetDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetDetailFragment extends Fragment {
    ANImageView petImageView;
    TextView petNameTextView;
    TextView petDescriptionTextView;
    TextView petTypeTextView;
    TextView petStateTextView;
    TextView petAgeViewTextView;
    TextView petSpecialFeaturesTextView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnDetailFragmentInteractionListener mListener;

    public PetDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PetDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PetDetailFragment newInstance(String param1, String param2) {
        PetDetailFragment fragment = new PetDetailFragment();
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
        return inflater.inflate(R.layout.fragment_pet_detail, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
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

        ImageButton reHomePetButton = (ImageButton) view.findViewById(R.id.reHomePetButton);
        reHomePetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed(2);
            }
        });


        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.editPetFloatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed(1);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int index) {
        if (mListener != null) {
            mListener.onDetailFragmentInteraction(index);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDetailFragmentInteractionListener) {
            mListener = (OnDetailFragmentInteractionListener) context;
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
    public interface OnDetailFragmentInteractionListener {
        // TODO: Update argument type and name
        void onDetailFragmentInteraction(int index);
    }
}
