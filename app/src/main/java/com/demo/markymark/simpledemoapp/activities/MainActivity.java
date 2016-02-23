package com.demo.markymark.simpledemoapp.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.demo.markymark.simpledemoapp.Constants;
import com.demo.markymark.simpledemoapp.R;
import com.demo.markymark.simpledemoapp.helpers.NetworkHelper;

public class MainActivity extends AppCompatActivity {

    private final int ANIM_DURATION = 2000;
    private TextView asciiFaceTv;
    private ProgressBar toolbarProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        asciiFaceTv = (TextView) findViewById(R.id.tv_ascii_face);
        toolbarProgress = (ProgressBar) findViewById(R.id.toolbar_progress);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsciiFaceUpdater().execute();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            int rotation = 360;
            if (asciiFaceTv.getRotation() > 0)
                rotation = 0;

            asciiFaceTv.animate().rotation(rotation).setDuration(ANIM_DURATION).start();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class AsciiFaceUpdater extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            toolbarProgress.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            return NetworkHelper.getRequest(Constants.ASCII_FACE_URL);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null) {
                asciiFaceTv.setText(s);
            }
            toolbarProgress.setVisibility(View.INVISIBLE);

        }
    }
}
