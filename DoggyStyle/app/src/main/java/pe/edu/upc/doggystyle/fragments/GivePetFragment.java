package pe.edu.upc.doggystyle.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import pe.edu.upc.doggystyle.DoggyStyleApp;
import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.adapters.MyPetsAdapter;
import pe.edu.upc.doggystyle.interfaces.OnEntryClickListener;
import pe.edu.upc.doggystyle.models.PetEntry;
import pe.edu.upc.doggystyle.network.PetApi;
import pe.edu.upc.doggystyle.utilities.DataService;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GivePetFragment.OnGivePetFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GivePetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GivePetFragment extends Fragment implements OnEntryClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    MyPetsAdapter myPetsAdapter;
    List<PetEntry> petEntries;
    private static String TAG = "FindAppet";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnGivePetFragmentInteractionListener mListener;

    public GivePetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GivePetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GivePetFragment newInstance(String param1, String param2) {
        GivePetFragment fragment = new GivePetFragment();
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
        return inflater.inflate(R.layout.fragment_give_pet, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myPetsAdapter = new MyPetsAdapter(getContext(), DataService.getInstance().getMyPetsEntries(), this);
        recyclerView = (RecyclerView)view.findViewById(R.id.myPetsRecyclerView);
        recyclerView.setAdapter(myPetsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int index) {
        if (mListener != null) {
            mListener.OnGivePetFragmentInteractionListener(index);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGivePetFragmentInteractionListener) {
            mListener = (OnGivePetFragmentInteractionListener) context;
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
    public void onEntryClick(int index) {
        mListener.OnGivePetFragmentInteractionListener(index);
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
    public interface OnGivePetFragmentInteractionListener {
        // TODO: Update argument type and name
        void OnGivePetFragmentInteractionListener(int index);
    }
    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    private void updateData() {
        int userId = DoggyStyleApp.getInstance().getCurrentSession().getId();
        AndroidNetworking.get("http://doggystyle.vcsoft.pe/api/petadoption/"+userId)
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            petEntries = PetEntry.build(response.getJSONArray("Result"));
                            myPetsAdapter.setPetEntries(petEntries).notifyDataSetChanged();
                        } catch (JSONException e) {
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
