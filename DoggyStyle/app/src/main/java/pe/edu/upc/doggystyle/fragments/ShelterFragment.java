package pe.edu.upc.doggystyle.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.List;

import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.adapters.ShelterAdapter;
import pe.edu.upc.doggystyle.interfaces.OnEntryClickListener;
import pe.edu.upc.doggystyle.models.Shelter;
import pe.edu.upc.doggystyle.network.ShelterApi;
import pe.edu.upc.doggystyle.utilities.DataService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShelterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShelterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShelterFragment extends Fragment implements OnEntryClickListener {
    // TODO: Rename parameter arguments, choose names that match
    RecyclerView recyclerView;
    ShelterAdapter shelterAdapter;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String TAG = "FindAppet";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ShelterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShelterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShelterFragment newInstance(String param1, String param2) {
        ShelterFragment fragment = new ShelterFragment();
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
        return inflater.inflate(R.layout.fragment_shelter, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shelterAdapter = new ShelterAdapter(view.getContext(), DataService.getInstance().getShelterEntries(), this);
        recyclerView = (RecyclerView)view.findViewById(R.id.shelter_recycler_view);
        recyclerView.setAdapter(shelterAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onShelterFragmentInteraction(1);
        }
    }

    @Override
    public void onEntryClick(int index) {
        mListener.onShelterFragmentInteraction(index);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onShelterFragmentInteraction(int index);
    }

    private void updateData() {
        ShelterApi sheterApi = new ShelterApi();
        AndroidNetworking.get(sheterApi.getShelterURL())
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<Shelter> shelterEntries = Shelter.build(response.getJSONArray("Result"));
                            shelterAdapter.setShelterEntries(shelterEntries).notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, anError.getLocalizedMessage());
                    }
                });
    }
}
