package pe.edu.upc.doggystyle.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.doggystyle.DoggyStyleApp;
import pe.edu.upc.doggystyle.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RehomeFragment.OnRehomeFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RehomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RehomeFragment extends Fragment {
    Button buttonRehome;
    TextInputEditText descriptionTextInputEditText;
    TextView namePetTextView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnRehomeFragmentInteractionListener mListener;

    public RehomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RehomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RehomeFragment newInstance(String param1, String param2) {
        RehomeFragment fragment = new RehomeFragment();
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

        return inflater.inflate(R.layout.fragment_rehome, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        descriptionTextInputEditText = (TextInputEditText) view.findViewById(R.id.descriptionTextInputEditText);
        namePetTextView = (TextView) view.findViewById(R.id.namePetTextView);
        namePetTextView.setText(DoggyStyleApp.getInstance().getCurrentPet().getNamePet());
        buttonRehome = (Button) view.findViewById(R.id.reHomeButton);
        buttonRehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed();
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {
            reHomePet();
            mListener.onRehomeFragmentInteraction();
        }
    }

    public void reHomePet() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("PetId",DoggyStyleApp.getInstance().getCurrentPet().getPetId());
            jsonObject.put("Description",descriptionTextInputEditText.getText().toString());
            jsonObject.put("State", "ADO");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AndroidNetworking.put("http://doggystyle.vcsoft.pe/api/petadoption/0")
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
        if (context instanceof OnRehomeFragmentInteractionListener) {
            mListener = (OnRehomeFragmentInteractionListener) context;
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
    public interface OnRehomeFragmentInteractionListener {
        // TODO: Update argument type and name
        void onRehomeFragmentInteraction();
    }
}
