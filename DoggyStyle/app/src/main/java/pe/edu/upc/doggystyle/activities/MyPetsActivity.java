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

import java.util.List;

import pe.edu.upc.doggystyle.fragments.AddPetFragment;
import pe.edu.upc.doggystyle.fragments.AdoptFragment;
import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.fragments.EditPetFragment;
import pe.edu.upc.doggystyle.fragments.MyPetsFragment;
import pe.edu.upc.doggystyle.fragments.PetDetailFragment;
import pe.edu.upc.doggystyle.fragments.RehomeFragment;
import pe.edu.upc.doggystyle.fragments.ShelterFragment;

public class MyPetsActivity extends AppCompatActivity
        implements AdoptFragment.OnFragmentInteractionListener,
        MyPetsFragment.OnFragmentInteractionListener,
        PetDetailFragment.OnDetailFragmentInteractionListener,
        RehomeFragment.OnRehomeFragmentInteractionListener,
        ShelterFragment.OnFragmentInteractionListener,
        AddPetFragment.OnAddPetFragmentInteractionListener,
        EditPetFragment.OnEditPetFragmentInteractionListener
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
                    fragment = new ShelterFragment();
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
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_container, fragment).commit();
        
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
        fragment = new PetDetailFragment();
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
        if(index == 1)
            fragment = new EditPetFragment();
        if(index == 2)
            fragment = new RehomeFragment();
        else
            fragment = new MyPetsFragment();
        addFragment();
    }
    @Override
    public void onRehomeFragmentInteraction() {
        popBack();
    }

    @Override
    public void onShelterFragmentInteraction(int index) {

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
}
