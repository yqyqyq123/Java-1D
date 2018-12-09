package com.zhaohong.parkingandroidapp;


import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

public class PreviousParking extends AppCompatActivity {
    private final String sharedPrefFile = "com.example.android.mainsharedprefs";
    public static String KEY;
    public int selectslot;
    public Button selectbutton;
    SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.previous);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        Intent intent = getIntent();
        int no = intent.getIntExtra("cpno",0);
        String KEY = getkey(no);
        Log.i("zhaohongprevious",Integer.toString(no));
        if(KEY=="Error"){
            new  AlertDialog.Builder(this)
                    .setMessage("Is Blocked" )
                    .setPositiveButton("OK" ,  null )
                    .show();
        }else {
            Log.i("zhaohongprevious",KEY);
            selectslot = mPreferences.getInt(KEY,0);
            selectbutton = findViewById(selectslot);
            selectbutton.setBackgroundResource(R.drawable.button_mycar);
        }
    }
    private String getkey(int no){
        if(no==1){
            return "MyKey1";
        }else if(no==2){
            return "MyKey2";
        }else if(no==3){
            return "MyKey3";
        }else if(no==4){
            return "MyKey4";
        }
        else {
            return "Error";
        }
    }

}
