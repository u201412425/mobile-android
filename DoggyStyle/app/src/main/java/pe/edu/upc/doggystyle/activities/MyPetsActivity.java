package pe.edu.upc.doggystyle.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import pe.edu.upc.doggystyle.DoggyStyleApp;
import pe.edu.upc.doggystyle.fragments.AddPetFragment;
import pe.edu.upc.doggystyle.fragments.AdoptFragment;
import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.fragments.EditPetFragment;
import pe.edu.upc.doggystyle.fragments.GivePetFragment;
import pe.edu.upc.doggystyle.fragments.MyPetsFragment;
import pe.edu.upc.doggystyle.fragments.PetDetailAdoptFragment;
import pe.edu.upc.doggystyle.fragments.PetDetailFragment;
import pe.edu.upc.doggystyle.fragments.RehomeFragment;
import pe.edu.upc.doggystyle.fragments.RequestPetFragment;
import pe.edu.upc.doggystyle.fragments.ShelterDetailFragment;
import pe.edu.upc.doggystyle.fragments.ShelterFragment;
import pe.edu.upc.doggystyle.models.PetEntry;

public class MyPetsActivity extends AppCompatActivity
        implements AdoptFragment.onAdoptFragmentInteraction,
        MyPetsFragment.OnFragmentInteractionListener,
        PetDetailFragment.OnDetailFragmentInteractionListener,
        RehomeFragment.OnRehomeFragmentInteractionListener,
        ShelterFragment.OnFragmentInteractionListener,
        AddPetFragment.OnAddPetFragmentInteractionListener,
        EditPetFragment.OnEditPetFragmentInteractionListener,
        ShelterDetailFragment.OnFragmentInteractionListener,
        GivePetFragment.OnGivePetFragmentInteractionListener,
        RequestPetFragment.OnRequestPetFragmentInteractionListener,
        PetDetailAdoptFragment.OnDetailAdoptFragmentInteractionListener
        {

    Fragment fragment;
    FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new AdoptFragment();
                break;
                case R.id.navigation_dashboard:
                    fragment = new MyPetsFragment();
                    break;
                case R.id.navigation_notifications:
                    fragment = new GivePetFragment();
                    break;
            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_container, fragment).commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pets);

        fragmentManager = getSupportFragmentManager();
        fragment = new AdoptFragment();
        addFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    void addFragment(){
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment).commit();
    }

    void popBack(){
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        List<Fragment> fragments = fragmentManager.getFragments();
        transaction.remove(fragments.get(fragments.size()-1)).commit();
    }

    @Override
    public void onAdoptFragmentInteraction(int index) {
        if(index==0)
            fragment = new PetDetailAdoptFragment();
        else
            fragment = new ShelterFragment();
        addFragment();
    }

    @Override
    public void onMyPetsFragmentInteraction(int index) {
        if(index==0)
            fragment = new PetDetailFragment();
        else
            fragment = new AddPetFragment();
        addFragment();
    }

    @Override
    public void onDetailFragmentInteraction(int index) {
        switch(index) {
            case 1:
                fragment = new EditPetFragment();
                break;
            case 2:
                fragment = new RehomeFragment();
                break;
            default:
                fragment = new MyPetsFragment();
        }
        addFragment();
    }

    @Override
    public void onRehomeFragmentInteraction() {
        popBack();
    }

    @Override
    public void onShelterFragmentInteraction(int index) {
        switch (index){
            case 0:
                fragment = new ShelterDetailFragment();
                break;
            default:
                break;
        }

        addFragment();
    }

    @Override
    public void OnAddPetFragmentInteractionListener() {
        fragment = new MyPetsFragment();
        addFragment();
    }

    @Override
    public void onEditPetFragmentInteraction() {
        fragment = new MyPetsFragment();
        addFragment();
    }

    // Shelter Detail Interaction
    @Override
    public void onFragmentInteraction(Uri uri) {
        fragment = new ShelterDetailFragment();
        addFragment();
    }

    @Override
    public void OnGivePetFragmentInteractionListener(int index) {
        fragment = new RequestPetFragment();
        addFragment();
    }

    @Override
    public void OnRequestPetFragmentInteractionListener(int index) {

    }

    @Override
    public void OnDetailAdoptFragmentInteractionListener() {
        fragment = new MyPetsFragment();
        addFragment();
    }
        }
