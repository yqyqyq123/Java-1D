package com.zhaohong.parkingandroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.view.LayoutInflater;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class insideparking2 extends AppCompatActivity{
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference configRef = db.getReference("java1dcarpark").child("carpark2Configure");
    //DatabaseReference selectRef = db.getReference("java1dcarpark").child("carpark1Configure").child("carpark1Config");
    Map<String, Integer> insideConfig = new HashMap<>();
    //public Integer[] mark = {1,0,0,0,1,0,0,0,1,1,0,0,0,0,0,1,1,0,0,1};
    //= new ArrayList<>(Arrays.asList(mark));
    Map<String, Integer> selectDataMap = new HashMap<>();
    ArrayList<Integer> marktest = new ArrayList<>();
    String count1;
    String price1;
    Button[] buttons = new Button[20];
    int selectslot;
    Button selectbutton;
    private final String sharedPrefFile = "com.example.android.mainsharedprefs";
    public static final String KEY = "MyKey2";
    SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insidepark2);
        Intent intent = getIntent();

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference configRef = db.getReference("java1dcarpark").child("carpark2Configure");
        //final DatabaseReference selectRef = db.getReference("java1dcarpark").child("carpark1Configure").child("carpark1Config");

        //configRef.addListenerForSingleValueEvent(new ValueEventListener() {

        configRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, ArrayList<Long>> configDataMap = (Map)dataSnapshot.getValue();
                //Map<String, String> priceMap = (Map) dataSnapshot.getValue();
                //String count2 = String.valueOf(countMap.get("carpark2Count"));


                ArrayList<Long> configDataList = (ArrayList<Long>)configDataMap.get("carpark2Config");

                //ArrayList<Integer> configDataPlz = new ArrayList<>();
                for(int i = 0; i<20; i++){
                    //String tmp = String.valueOf(configDataList.get(i+1));

                    marktest.add(Integer.valueOf(String.valueOf(configDataList.get(i+1))));
                    Log.i("testtt",String.valueOf(configDataList.get(i+1)));
                }
                //stockArr = stockList.toArray(stockArr);
                Log.d("!!!!!!!!!!!!!!config","Value"+marktest);
                Log.i("zhaohng", "here is fine1");
                addentry(marktest);
                Log.i("zhaohong","here333");
                setBack();
                Log.i("zhaohong","here 444");
                addbuttons();
                addonclick();
                Button confirmbtn = (Button)findViewById(R.id.buttonconfirm);
                confirmbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectslot = mPreferences.getInt(KEY,0);
                        selectbutton = findViewById(selectslot);

                        if(selectbutton!=null){
                            selectbutton.setBackgroundResource(R.drawable.button_mycar);
                            int[] coords = new int[2];
                            selectbutton.getLocationInWindow(coords);
                            int absoluteX = coords[0];
                            int absoluteY = coords[1];
                            if(selectbutton==(Button)findViewById(R.id.buttonSlot1)||selectbutton==(Button)findViewById(R.id.buttonSlot2)||selectbutton==(Button)findViewById(R.id.buttonSlot4)||selectbutton==(Button)findViewById(R.id.buttonSlot6)||selectbutton==(Button)findViewById(R.id.buttonSlot7)||selectbutton==(Button)findViewById(R.id.buttonSlot9)||selectbutton==(Button)findViewById(R.id.buttonSlot11)||selectbutton==(Button)findViewById(R.id.buttonSlot13)||selectbutton==(Button)findViewById(R.id.buttonSlot16)||selectbutton==(Button)findViewById(R.id.buttonSlot18)){
                                LayoutInflater inflater = LayoutInflater.from(insideparking2.this);
                                View vw = inflater.inflate(R.layout.insidepark2, null);
                                Intent intent = new Intent(insideparking2.this, DrawPath2.class);
                                intent.putExtra("x",absoluteX+selectbutton.getWidth()/2);
                                intent.putExtra("y",absoluteY+selectbutton.getHeight()/2+30);
                                Log.i("zhaohong",Integer.toString(absoluteX)+Integer.toString(absoluteY));
                                startActivity(intent);
                            }else {
                                LayoutInflater inflater = LayoutInflater.from(insideparking2.this);
                                View vw = inflater.inflate(R.layout.insidepark2, null);
                                Intent intent = new Intent(insideparking2.this, DrawPath2.class);
                                intent.putExtra("x",absoluteX+selectbutton.getWidth()/2);
                                intent.putExtra("y",absoluteY-selectbutton.getHeight()/2-30);
                                Log.i("zhaohong",Integer.toString(absoluteX)+Integer.toString(absoluteY));
                                startActivity(intent);
                            }



                        }else {
                            new  AlertDialog.Builder(view.getContext())
                                    .setMessage("nothing select" )
                                    .setPositiveButton("OK" ,  null )
                                    .show();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("!!!READconfig", "FAIL", databaseError.toException());
            }
        });



    }

    //public int[] mark = {1,0,0,0,1,0,0,0,1,1,0,0,0,0,0,1,1,0,0,1};
    ArrayList<Entry> entries = new ArrayList<>();
    public void addentry(ArrayList mark){
        for(int i=0; i<mark.size();i++){
            if(!mark.get(i).equals(0)){
                Entry e = new Entry("buttonSlot"+Integer.toString(i+1));
                e.setLocked(true);
                entries.add(e);

                //Log.i("zhaohong",e.getEntryID());
            }else {
                Entry e = new Entry("buttonSlot"+Integer.toString(i+1));
                entries.add(e);
            }
        }
    }
    public void setBack(){
        for(Entry e:entries){
            if(e.isLocked()){
                String s = e.getEntryID();
                Log.i("zhaohong",e.getEntryID()+"0");
                int indentify= getResources().getIdentifier(s, "id", getPackageName());
                //Log.i("zhaohongtest",Integer.toString(indentify));
                Button button = (Button)findViewById(indentify);
                Log.i("zhtest",(button.getText()).toString());
                button.setBackgroundResource(R.drawable.button_unselector);

            }else {
                String s = e.getEntryID();
                Log.i("zhaohong",e.getEntryID()+"1");
                int indentify= getResources().getIdentifier(s, "id", getPackageName());
                Button button = (Button)findViewById(indentify);
                button.setBackgroundResource(R.drawable.button_selector);
            }
        }
    }

    public void addbuttons(){
        for(int i=0; i<entries.size();i++){
            String s = entries.get(i).getEntryID();
            int indentify= getResources().getIdentifier(s, "id", getPackageName());
            Button button = (Button)findViewById(indentify);
            buttons[i] = button;
        }


    }
    public void selectButton(Button button){
        if(selectbutton!=null){
            selectbutton.setBackgroundResource(R.drawable.button_selector);
            selectbutton=null;
        }
        selectbutton = button;

        SharedPreferences.Editor preferenceEditor = mPreferences.edit();
        preferenceEditor.putInt(KEY,selectbutton.getId());
        preferenceEditor.commit();

        selectbutton.setBackgroundResource(R.drawable.button_mycar);
    }
    public void addonclick(){
        for(int i=0;i<buttons.length;i++){
            final int finalI = i;
            buttons[finalI].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //setSelectslot(button);
                    try {
                        setSelectslot(buttons[finalI]);
                    } catch (final Exception e) {
                        // TODO: log the exception
                        Log.i("zhaohongtest","buttoncannot");
                    }
                    Log.e("Test", "左上角坐标x：" + buttons[finalI].getLeft());
                    Log.e("Test", "左上角坐标y：" + buttons[finalI].getTop());
                    Log.e("Test", "右下角坐标x：" + buttons[finalI].getRight());
                    Log.e("Test", "右下角坐标y：" + buttons[finalI].getBottom());
                    Log.e("Test","width"+buttons[finalI].getWidth());
                    Log.e("Test","height"+buttons[finalI].getHeight());
                }
            });
        }
    }
    public void setSelectslot(Button button){
        for(int i=0; i<entries.size();i++){
            if(buttons[i]==button){
                if(entries.get(i).isLocked()){
                    new  AlertDialog.Builder(this)
                            .setMessage("Is Blocked" )
                            .setPositiveButton("OK" ,  null )
                            .show();
                }else {
                    selectButton(button);
                }
            }
        }
    }



}

