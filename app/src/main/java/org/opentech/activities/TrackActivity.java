package org.opentech.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.opentech.R;
import org.opentech.db.DatabaseManager;
import org.opentech.fragments.ScheduleFragment;

public class TrackActivity extends ActionBarActivity {

    private String track;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        track = getIntent().getStringExtra("TRACK");

        getSupportFragmentManager().beginTransaction().replace(R.id.container, ScheduleFragment.newInstance(track), ScheduleFragment.TAG).addToBackStack(null).commit();
        setTitle("Tracks: " + track);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.directions) {

            DatabaseManager db = DatabaseManager.getInstance();
            String map = "https://www.google.com.sg/maps/place/Biopolis/@1.304256,103.79179,16z/data=!4m2!3m1!1s0x0:0x9965b36cbf8d88c3";//db.getTrackMapUrl(track);
            Log.d("TRack ACTIVITY" , map);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
