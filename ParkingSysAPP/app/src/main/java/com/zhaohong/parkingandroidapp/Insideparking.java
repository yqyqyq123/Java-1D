package com.zhaohong.parkingandroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
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

import java.util.ArrayList;

public class Insideparking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insidepark);
        Intent intent = getIntent();
        Log.i("zhaohng", "here is fine1");
        addentry();
        Log.i("zhaohong","here333");
        setBack();
        Log.i("zhaohong","here 444");
        addbuttons();
        addonclick();
        Button confirmbtn = (Button)findViewById(R.id.buttonconfirm);
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectbutton!=null){
                    int x1 = selectbutton.getLeft();
                    int x2 = selectbutton.getRight();
                    int y = selectbutton.getBottom();
                    LayoutInflater inflater = LayoutInflater.from(Insideparking.this);
                    View vw = inflater.inflate(R.layout.insidepark, null);
                    Intent intent = new Intent(Insideparking.this, DrawPath.class);
                    intent.putExtra("x1",x1);
                    intent.putExtra("x2",x2);
                    intent.putExtra("y",y);
                    Log.i("zhaohong",Integer.toString(x1)+Integer.toString(x2)+Integer.toString(y));
                    startActivity(intent);
                }else {
                    new  AlertDialog.Builder(view.getContext())
                            .setMessage("nothing select" )
                            .setPositiveButton("OK" ,  null )
                            .show();
                }
            }
        });
    }
    public int[] mark = {1,0,0,0,1,0,0,0,1,1,0,0,0,0,0,1,1,0,0,1};
    ArrayList<Entry> entries = new ArrayList<>();
    public void addentry(){
        for(int i=0; i<mark.length;i++){
            if(mark[i]!=0){
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
    Button[] buttons = new Button[20];
    Entry selectslot;
    Button selectbutton;
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

    public void getcorrdinate(){
        if(selectbutton!=null){
            final ViewTreeObserver vto = selectbutton.getViewTreeObserver();
            vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    selectbutton.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    int height = selectbutton.getMeasuredHeight();
                    int width = selectbutton.getMeasuredWidth();
                    Log.e("Test", "高度：" + height);
                    Log.e("Test", "宽度：" + width);
                    Log.e("Test", "左上角坐标x：" + selectbutton.getLeft());
                    Log.e("Test", "左上角坐标y：" + selectbutton.getTop());
                    Log.e("Test", "右下角坐标x：" + selectbutton.getRight());
                    Log.e("Test", "右下角坐标y：" + selectbutton.getBottom());
                }
            });
        }
    }


}

