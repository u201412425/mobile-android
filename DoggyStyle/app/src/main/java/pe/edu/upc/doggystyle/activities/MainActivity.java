package pe.edu.upc.doggystyle.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import pe.edu.upc.doggystyle.R;

public class MainActivity extends AppCompatActivity {
    private class DelayLogin extends AsyncTask <Object, Void, Void> {
        @Override
        protected Void doInBackground(Object... params) {
            try {
                Thread.sleep((Integer)params[1]);
                startActivity(new Intent((Context)params[0], LoginActivity.class));
            } catch (InterruptedException e){
                System.out.println(e);
            }
            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new DelayLogin().execute(this,1000);
    }
}
