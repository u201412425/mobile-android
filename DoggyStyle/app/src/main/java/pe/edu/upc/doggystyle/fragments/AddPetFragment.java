package pe.edu.upc.doggystyle.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import pe.edu.upc.doggystyle.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPetFragment extends Fragment {
    private OnAddPetFragmentInteractionListener mListener;
   public AddPetFragment(){

   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_pet, container, false);

        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.addNewPetFloatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonPressed();
            }
        });
        return view;
    }

    public void onButtonPressed() {
        if (mListener != null) {
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
