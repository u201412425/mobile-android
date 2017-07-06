package pe.edu.upc.doggystyle.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.doggystyle.R;
import pe.edu.upc.doggystyle.fragments.*;

public class MyShelterActivity extends AppCompatActivity
    implements MyPetsShelterFragment.OnMyPetsShelterFragmentInteractionListener ,
            GivePetFragment.OnGivePetFragmentInteractionListener{

    Fragment fragment;
    FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_pets:
                    fragment = new MyPetsShelterFragment();
                    return true;
                case R.id.navigation_give:
                    fragment = new GivePetShelterFragment();
                    return true;
            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content, fragment).commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shelter);

        fragmentManager = getSupportFragmentManager();
        fragment = new MyPetsShelterFragment();
        addFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    void addFragment(){
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment).commit();
    }

    void popBack(){
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        List<Fragment> fragments = fragmentManager.getFragments();
        transaction.remove(fragments.get(fragments.size()-1)).commit();
    }

    @Override
    public void OnGivePetFragmentInteractionListener(Uri uri) {
        fragment = new GivePetFragment();
        addFragment();
    }

    @Override
    public void OnMyPetsShelterFragmentInteractionListener(int index) {
        if(index==0)
            fragment = new PetDetailFragment();
        else
            fragment = new AddPetFragment();
        addFragment();
    }
}
