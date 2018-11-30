package com.example.chokhaoze.mycarparkapp2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EditCarParkConfiguration extends AppCompatActivity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_car_park_configuration);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final TextView textViewCount = findViewById(R.id.textViewCount);

        final Button buttonSlot1 = findViewById(R.id.buttonSlot1);
        final Button buttonSlot2 = findViewById(R.id.buttonSlot2);
        final Button buttonSlot3 = findViewById(R.id.buttonSlot3);
        final Button buttonSlot4 = findViewById(R.id.buttonSlot4);
        final Button buttonSlot5 = findViewById(R.id.buttonSlot5);
        final Button buttonSlot6 = findViewById(R.id.buttonSlot6);
        final Button buttonSlot7 = findViewById(R.id.buttonSlot7);
        final Button buttonSlot8 = findViewById(R.id.buttonSlot8);
        final Button buttonSlot9 = findViewById(R.id.buttonSlot9);
        final Button buttonSlot10 = findViewById(R.id.buttonSlot10);
        final Button buttonSaveConfig = findViewById(R.id.buttonSaveConfig);
        final Button buttonResetConfig = findViewById(R.id.buttonResetConfig);

        buttonSlot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot1.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 1 Selected", Toast.LENGTH_SHORT).show();
                count += 1;
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count) + "/10");
            }

        });

        buttonSlot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot2.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 2 Selected", Toast.LENGTH_SHORT).show();
                count += 1;
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count) + "/10");
            }
        });

        buttonSlot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot3.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 3 Selected", Toast.LENGTH_SHORT).show();
                count += 1;
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count) + "/10");
            }
        });

        buttonSlot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot4.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 4 Selected", Toast.LENGTH_SHORT).show();
                count += 1;
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count) + "/10");
            }
        });

        buttonSlot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot5.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 5 Selected", Toast.LENGTH_SHORT).show();
                count += 1;
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count) + "/10");
            }
        });

        buttonSlot6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot6.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 6 Selected", Toast.LENGTH_SHORT).show();
                count += 1;
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count) + "/10");
            }
        });

        buttonSlot7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot7.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 7 Selected", Toast.LENGTH_SHORT).show();
                count += 1;
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count) + "/10");
            }
        });

        buttonSlot8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot8.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 8 Selected", Toast.LENGTH_SHORT).show();
                count += 1;
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count) + "/10");
            }
        });

        buttonSlot9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot9.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 9 Selected", Toast.LENGTH_SHORT).show();
                count += 1;
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count) + "/10");
            }
        });

        buttonSlot10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot10.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 10 Selected", Toast.LENGTH_SHORT).show();
                count += 1;
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count) + "/10");
            }
        });

        buttonSaveConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSaveConfig.setBackgroundResource(R.drawable.button_unselector);
                Toast.makeText(EditCarParkConfiguration.this,"Saving...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditCarParkConfiguration.this , SetCarParkConfiguration.class);//links MainActivity to SubActivity
                startActivity(intent);
            }

        });

        buttonResetConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditCarParkConfiguration.this,"Reseting...", Toast.LENGTH_SHORT).show();
                buttonSlot1.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot2.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot3.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot4.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot5.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot6.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot7.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot8.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot9.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot10.setBackgroundResource(R.drawable.button_unselector);
                count = 0;
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count));
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

        if (id == R.id.set_carpark_configuration){
            Intent intent = new Intent(this, SetCarParkConfiguration.class);
            startActivity(intent);
            return true;//method signature needs boolean as a return when the action is completed successfully
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