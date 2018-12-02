package com.yq.parkingandroidapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditCarParkConfiguration extends AppCompatActivity {
    int count1 = 0;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    private DocumentReference mConfig1 = db.collection("carparkData").document("config1");

    Map <String, Integer> carparkConfig1 = new HashMap<>();
    EditText price1Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_car_park_3_configuration);
        final EditText price1Text = (EditText) findViewById(R.id.price1);
        for(int i = 1; i < 21; i++){
            carparkConfig1.put(Integer.toString(i),1);
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
        final Button buttonSlot11 = findViewById(R.id.buttonSlot11);
        final Button buttonSlot12 = findViewById(R.id.buttonSlot12);
        final Button buttonSlot13 = findViewById(R.id.buttonSlot13);
        final Button buttonSlot14 = findViewById(R.id.buttonSlot14);
        final Button buttonSlot15 = findViewById(R.id.buttonSlot15);
        final Button buttonSlot16 = findViewById(R.id.buttonSlot16);
        final Button buttonSlot17 = findViewById(R.id.buttonSlot17);
        final Button buttonSlot18 = findViewById(R.id.buttonSlot18);
        final Button buttonSlot19 = findViewById(R.id.buttonSlot19);
        final Button buttonSlot20 = findViewById(R.id.buttonSlot20);
        final Button buttonResetConfig = findViewById(R.id.buttonResetConfig1);



        buttonSlot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot1.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 1 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("1",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }

        });

        buttonSlot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot2.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 2 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("2",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot3.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 3 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("3",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot4.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 4 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("4",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot5.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 5 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("5",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot6.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 6 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("6",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot7.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 7 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("7",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot8.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 8 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("8",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot9.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 9 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("9",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot10.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 10 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("10",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot11.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 11 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("11",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }

        });

        buttonSlot12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot12.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 12 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("12",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot13.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 13 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("13",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot14.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 14 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("14",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot15.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 15 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("15",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot16.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 16 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("16",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot17.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 17 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("17",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot18.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 18 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("18",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot19.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 19 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("19",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
            }
        });

        buttonSlot20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSlot20.setBackgroundResource(R.drawable.button_selector);
                Toast.makeText(EditCarParkConfiguration.this,"Slot 20 Selected", Toast.LENGTH_SHORT).show();
                count1 += 1;
                carparkConfig1.put("20",0);
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1) + "/20");
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
                buttonSlot11.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot12.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot13.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot14.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot15.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot16.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot17.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot18.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot19.setBackgroundResource(R.drawable.button_unselector);
                buttonSlot20.setBackgroundResource(R.drawable.button_unselector);
                count1 = 0;
                textViewCount.setText("Number of Vacancies = " + String.valueOf(count1));
                for(int i = 1; i < 21; i++){
                    carparkConfig1.put(Integer.toString(i),1);
                }
                price1Text.setText(null);
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

    String getCount1(){
        return "Number of Vacancies = " + String.valueOf(count1) + "/20";
    }

    public void saveConfig1(View view) {
        EditText price1Text = (EditText) findViewById(R.id.price1);
        String price1 = price1Text.getText().toString();
        Map<String, Object> carpark1ConfigData = new HashMap<>();
        carpark1ConfigData.put("carpark1Config", carparkConfig1);
        carpark1ConfigData.put("carpark1Price", price1);
        carpark1ConfigData.put("carpark1Count", count1);


        mConfig1.set(carpark1ConfigData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("carpark1ConfigData", "Carpark 1 config has been saved!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("carpark1ConfigData", "Carpark 1 config was not saved!");
            }
        });


    }

}
