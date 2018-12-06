package com.zhaohong.parkingandroidapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.util.Log;

public class DrawPath extends Activity {
    private LinearLayout linlay;
    private Bitmap background;
    private Canvas canvas;
    private int x1;
    private int x2;
    private int y;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加了一个layout
        setContentView(R.layout.drawpath);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            x1 = extras.getInt("x1");
            x2 = extras.getInt("x2");
            y = extras.getInt("y");
        }

        Canvas canvas = new Canvas();
        onDraw(canvas);

        Button confirmbtn = (Button)findViewById(R.id.buttonconfirmD);
        //加了个exit
        confirmbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }
    protected void onDraw(Canvas canvas){
        linlay = (LinearLayout) findViewById(R.id.simpleCanvasTriangle);


        // Canvas
        background = Bitmap.createBitmap(250, 400, Bitmap.Config.ARGB_4444);
        canvas = new Canvas(background);

        // draw colors on canvas
        Paint painting = new Paint();
        painting.setColor(getResources().getColor(R.color.holo_blue_light));

        painting.setStrokeWidth(4);
        painting.setStyle(Paint.Style.STROKE);
        painting.setStrokeWidth(10);

        Path path=new Path();
        path.moveTo(400,400);
        path.lineTo(x2,y);
        path.moveTo(x2,y);
        path.lineTo(x1,y);
        Log.i("zhaohong","finish draw");

        path.close();
        canvas.drawPath(path,painting);


        // draw everything into linear layout
        linlay.setBackground(new BitmapDrawable(getResources(), background));
    }
}

