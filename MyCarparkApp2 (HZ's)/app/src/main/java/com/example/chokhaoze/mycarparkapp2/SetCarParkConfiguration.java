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

public class SetCarParkConfiguration extends AppCompatActivity {

    Button buttonCarPark1;
    Button buttonCarPark2;
    Button buttonCarPark3;
    Button buttonCarPark4;
    Button buttonCarPark5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_car_park_configuration);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonCarPark1 = findViewById(R.id.buttonCarPark1);
        buttonCarPark1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetCarParkConfiguration.this , EditCarParkConfiguration.class);//links MainActivity to SubActivity
                //the "back" arrow appears on the SubActivity because SubActivity is tied to MainActivity  in the Manifest Page
                startActivity(intent);
            }
        });

        buttonCarPark2 = findViewById(R.id.buttonCarPark2);
        buttonCarPark2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetCarParkConfiguration.this , EditCarPark2Configuration.class);//links MainActivity to SubActivity
                //the "back" arrow appears on the SubActivity because SubActivity is tied to MainActivity  in the Manifest Page
                startActivity(intent);
            }
        });

        buttonCarPark3 = findViewById(R.id.buttonCarPark3);
        buttonCarPark3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetCarParkConfiguration.this , EditCarPark3Configuration.class);//links MainActivity to SubActivity
                //the "back" arrow appears on the SubActivity because SubActivity is tied to MainActivity  in the Manifest Page
                startActivity(intent);
            }
        });

        buttonCarPark4 = findViewById(R.id.buttonCarPark4);
        buttonCarPark4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetCarParkConfiguration.this , EditCarPark4Configuration.class);//links MainActivity to SubActivity
                //the "back" arrow appears on the SubActivity because SubActivity is tied to MainActivity  in the Manifest Page
                startActivity(intent);
            }
        });

        buttonCarPark5 = findViewById(R.id.buttonCarPark5);
        buttonCarPark5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetCarParkConfiguration.this , EditCarPark5Configuration.class);//links MainActivity to SubActivity
                //the "back" arrow appears on the SubActivity because SubActivity is tied to MainActivity  in the Manifest Page
                startActivity(intent);
            }
        });

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

        /*if (id == R.id.menu_set_exchange_rate){//code brings user to SubActivity
            Intent intent = new Intent(this, SubActivity.class);
            startActivity(intent);
            return true;//method signature needs boolean as a return when the action is completed successfully
        }*/


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
