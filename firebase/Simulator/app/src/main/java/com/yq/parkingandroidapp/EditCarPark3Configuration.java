package com.yq.parkingandroidapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditCarPark3Configuration extends AppCompatActivity {
    int count3 = 0;

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference configRef = db.getReference("java1dcarpark").child("carpark3Configure");

    Map<String, Integer> carparkConfig3 = new HashMap<>();
    //int[] carparkConfig3 = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    EditText price3Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_car_park_3_configuration);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference configRef = db.getReference("java1dcarpark").child("carpark3Configure");

        final EditText price3Text = (EditText) findViewById(R.id.price3);

        for(int i = 1; i < 11; i++){
            carparkConfig3.put(Integer.toString(i),1);
        }


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

        final Button buttonResetConfig = findViewById(R.id.buttonResetConfig3);



        buttonSlot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot1.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarPark3Configuration.this,"Slot 1 Selected", Toast.LENGTH_SHORT).show();
                count3 += 1;
                carparkConfig3.put("1",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count3) + "/10");
            }

        });

        buttonSlot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot2.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarPark3Configuration.this,"Slot 2 Selected", Toast.LENGTH_SHORT).show();
                count3 += 1;
                carparkConfig3.put("2",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count3) + "/10");
            }
        });

        buttonSlot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot3.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarPark3Configuration.this,"Slot 3 Selected", Toast.LENGTH_SHORT).show();
                count3 += 1;
                carparkConfig3.put("3",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count3) + "/10");
            }
        });

        buttonSlot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot4.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarPark3Configuration.this,"Slot 4 Selected", Toast.LENGTH_SHORT).show();
                count3 += 1;
                carparkConfig3.put("4",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count3) + "/10");
            }
        });

        buttonSlot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot5.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarPark3Configuration.this,"Slot 5 Selected", Toast.LENGTH_SHORT).show();
                count3 += 1;
                carparkConfig3.put("5",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count3) + "/10");
            }
        });

        buttonSlot6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot6.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarPark3Configuration.this,"Slot 6 Selected", Toast.LENGTH_SHORT).show();
                count3 += 1;
                carparkConfig3.put("6",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count3) + "/10");
            }
        });

        buttonSlot7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot7.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarPark3Configuration.this,"Slot 7 Selected", Toast.LENGTH_SHORT).show();
                count3 += 1;
                carparkConfig3.put("7",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count3) + "/10");
            }
        });

        buttonSlot8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot8.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarPark3Configuration.this,"Slot 8 Selected", Toast.LENGTH_SHORT).show();
                count3 += 1;
                carparkConfig3.put("8",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count3) + "/10");
            }
        });

        buttonSlot9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot9.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarPark3Configuration.this,"Slot 9 Selected", Toast.LENGTH_SHORT).show();
                count3 += 1;
                carparkConfig3.put("9",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count3) + "/10");
            }
        });

        buttonSlot10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot10.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarPark3Configuration.this,"Slot 10 Selected", Toast.LENGTH_SHORT).show();
                count3 += 1;
                carparkConfig3.put("10",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count3) + "/10");
            }
        });


        buttonResetConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditCarPark3Configuration.this,"Reseting...", Toast.LENGTH_SHORT).show();
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

                count3 = 0;
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count3));
                for(int i = 1; i < 11; i++){
                    carparkConfig3.put(Integer.toString(i),1);
                }
                price3Text.setText(null);
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

    String getcount3(){
        return "Number of Vacancies = " + String.valueOf(count3) + "/10";
    }

    public void saveConfig3(View view) {
        EditText price3Text = (EditText) findViewById(R.id.price3);
        String price3 = price3Text.getText().toString();
        String counts1 = Integer.toString(count3);
        Map<String, Object> carpark3ConfigData = new HashMap<>();
        carpark3ConfigData.put("carpark3Config", carparkConfig3);
        carpark3ConfigData.put("carpark3Price", price3);
        carpark3ConfigData.put("carpark3Count", counts1);


        configRef.setValue(carpark3ConfigData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("carpark3ConfigData", "Carpark 3 config has been saved!");
                Toast.makeText(EditCarPark3Configuration.this,"Configuration is saved",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditCarPark3Configuration. this , SetCarParkConfiguration.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("carpark3ConfigData", "Carpark 3 config was not saved!");
            }
        });


    }

}
