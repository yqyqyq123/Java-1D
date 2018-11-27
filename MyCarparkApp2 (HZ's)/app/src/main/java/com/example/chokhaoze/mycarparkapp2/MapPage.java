package com.example.chokhaoze.mycarparkapp2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MapPage extends AppCompatActivity {
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
            startActivity(intent);
            return true;
        }

        if (id == R.id.set_carpark_configuration){
            Intent intent = new Intent(this, SetCarParkConfiguration.class);
            startActivity(intent);
            return true;//method signature needs boolean as a return when the action is completed successfully
        }


        if (id == R.id.open_google_map) {
            String location = getString(R.string.current_location);
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("geo").opaquePart("0.0").appendQueryParameter("q", location);
            Uri geoLocation = builder.build();

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(geoLocation);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

            //return true;//App signature's requirement
        }

        return super.onOptionsItemSelected(item);
    }

}
