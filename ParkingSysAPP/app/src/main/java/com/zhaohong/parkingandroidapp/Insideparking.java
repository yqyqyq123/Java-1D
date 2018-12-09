package com.zhaohong.parkingandroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.content.SharedPreferences;

//这一页……我改了挺多的……可能就最后几个function完全没碰过
public class Insideparking extends AppCompatActivity {
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference configRef = db.getReference("java1dcarpark").child("carpark1Configure");
    Map<String, Integer> insideConfig = new HashMap<>();
    //public Integer[] mark = {1,0,0,0,1,0,0,0,1,1,0,0,0,0,0,1,1,0,0,1};
     //= new ArrayList<>(Arrays.asList(mark));
    Map<String, Integer> selectDataMap = new HashMap<>();
    ArrayList<Integer> marktest = new ArrayList<>();
    Button[] buttons = new Button[20];
    int selectslot;
    Button selectbutton;

    //share preference
    private final String sharedPrefFile = "com.example.android.mainsharedprefs";
    public static final String KEY = "MyKey";
    SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insidepark);
        Log.i("!!!!!!!!!!carpark","12344");
        Intent intent = getIntent();
        //String no = intent.getStringExtra("carparkno");
        //og.i("!!!!!!!!!!carpark",no);

        //share preference
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        //firebase
        //final String carparkConfigure = getSelectCarpark(no);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference configRef = db.getReference("java1dcarpark").child("carpark1Configure");

        Log.i("!!!!!!!!prefer",Integer.toString(selectslot));

        configRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, ArrayList<Long>> configDataMap = (Map)dataSnapshot.getValue();

                ArrayList<Long> configDataList = (ArrayList<Long>)configDataMap.get("carpark1Configure");
                for(int i = 0; i<20; i++){
                    marktest.add(Integer.valueOf(String.valueOf(configDataList.get(i+1))));
                }
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
                        Log.i("!!!!!!!getshenme",selectbutton.toString());

                        if(selectbutton!=null){
                            selectbutton.setBackgroundResource(R.drawable.button_mycar);

                            int[] coords = new int[2];
                            selectbutton.getLocationInWindow(coords);
                            int absoluteX = coords[0];
                            int absoluteY = coords[1];
                            if(selectbutton==(Button)findViewById(R.id.buttonSlot1)||selectbutton==(Button)findViewById(R.id.buttonSlot2)||selectbutton==(Button)findViewById(R.id.buttonSlot4)||selectbutton==(Button)findViewById(R.id.buttonSlot6)||selectbutton==(Button)findViewById(R.id.buttonSlot7)||selectbutton==(Button)findViewById(R.id.buttonSlot9)||selectbutton==(Button)findViewById(R.id.buttonSlot11)||selectbutton==(Button)findViewById(R.id.buttonSlot13)||selectbutton==(Button)findViewById(R.id.buttonSlot16)||selectbutton==(Button)findViewById(R.id.buttonSlot18)){
                                LayoutInflater inflater = LayoutInflater.from(Insideparking.this);
                                View vw = inflater.inflate(R.layout.insidepark, null);
                                Intent intent = new Intent(Insideparking.this, DrawPath.class);
                                intent.putExtra("x",absoluteX+selectbutton.getWidth()/2);
                                intent.putExtra("y",absoluteY+selectbutton.getHeight()/2+30);
                                Log.i("zhaohong",Integer.toString(absoluteX)+Integer.toString(absoluteY));
                                startActivity(intent);
                            }else {
                                LayoutInflater inflater = LayoutInflater.from(Insideparking.this);
                                View vw = inflater.inflate(R.layout.insidepark, null);
                                Intent intent = new Intent(Insideparking.this, DrawPath.class);
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
    ArrayList<Entry> entries = new ArrayList<>();
    public void addentry(ArrayList mark){
        for(int i=0; i<mark.size();i++){
            if(!mark.get(i).equals(0)){
                Entry e = new Entry("buttonSlot"+Integer.toString(i+1));
                e.setLocked(true);
                entries.add(e);
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
                int indentify= getResources().getIdentifier(s, "id", getPackageName());
                Button button = (Button)findViewById(indentify);
                button.setBackgroundResource(R.drawable.button_unselector);

            }else {
                String s = e.getEntryID();
                //Log.i("zhaohong",e.getEntryID()+"1");
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
    private String getSelectCarpark(String no){
        if(no =="1"){
          return "carpark1Configure";
        }else {
            return "carpark2Configure";
        }
    }
}



