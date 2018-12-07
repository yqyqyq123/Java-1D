package com.zhaohong.parkingandroidapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.util.Log;
import android.view.Display;
import java.io.ByteArrayInputStream;

public class DrawPath extends AppCompatActivity {
    private LinearLayout linlay;
    private Bitmap background;
    private Canvas canvas;
    private int x;
    private int y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加了一个layout
        setContentView(R.layout.drawpath);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            x = extras.getInt("x");
            y = extras.getInt("y");
        }
        Canvas canvas = new Canvas();
        onDraw(canvas);

    }
    protected void onDraw(Canvas canvas){
        linlay = (LinearLayout) findViewById(R.id.simpleCanvasTriangle);
        // Canvas
        Display metrics = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        metrics. getSize(size);
        int width = size. x;
        int height = size. y;
        Log.e("test",Integer.toString(width)+"height"+Integer.toString(height));

        background = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        canvas = new Canvas(background);

        // draw colors on canvas
        Paint painting = new Paint();
        painting.setColor(getResources().getColor(R.color.holo_blue_light));

        painting.setStrokeWidth(4);
        painting.setStyle(Paint.Style.STROKE);
        painting.setStrokeWidth(10);

        Path path=new Path();
        path.moveTo(1000,1500);
        path.lineTo(1000,y);
        path.moveTo(1000,y);
        path.lineTo(x,y);
        //Log.i("zhaohong","finish draw"+Integer.toString(x1)+" "+Integer.toString(x2));

        path.close();
        canvas.drawPath(path,painting);

        // draw everything into linear layout
        linlay.setBackground(new BitmapDrawable(getResources(), background));
    }

}

