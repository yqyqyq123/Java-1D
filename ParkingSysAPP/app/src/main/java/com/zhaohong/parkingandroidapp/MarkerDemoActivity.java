/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zhaohong.parkingandroidapp;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.os.AsyncTask;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowCloseListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;

/**
 * This shows how to place markers on a map.
 */
public class MarkerDemoActivity extends AppCompatActivity implements
        OnMarkerClickListener, OnMapAndViewReadyListener.OnGlobalLayoutAndMapReadyListener {

    //这里这里
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference mDocRef2 = db.getReference("java1dcarpark").child("currentLocation");
    Map<String, Double> mCurrentMap;
    DatabaseReference mDocConfig1 = db.getReference("java1dcarpark").child("carpark1Configure");

    DatabaseReference mDocConfig2 = db.getReference("java1dcarpark").child("carpark2Configure");

    //到这里
    private static final LatLng carpark1 = new LatLng(1.333225, 103.959113);

    private static final LatLng carpark2 = new LatLng(1.336743, 103.964262);

    private static final LatLng carpark3 = new LatLng(1.345637, 103.963486);

    private static final LatLng carpark4 = new LatLng(1.343094, 103.965666);

    LatLng currentLocation = new LatLng(1.341195, 103.964157);;

    Double park1 = 0.0;
    Double park2 = 0.0;
    Double park3 = 0.0;
    Double park4 = 0.0;

    double[] parks = {park1,park2,park3,park4};

    class CustomInfoWindowAdapter implements InfoWindowAdapter {

        // These are both viewgroups containing an ImageView with id "badge" and two TextViews with id
        // "title" and "snippet".
        private final View mWindow;

        private final View mContents;

        CustomInfoWindowAdapter() {
            mWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);
            mContents = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            if (mOptions.getCheckedRadioButtonId() != R.id.custom_info_window) {
                // This means that getInfoContents will be called.
                return null;
            }
            render(marker, mWindow);
            return mWindow;
        }

        @Override
        public View getInfoContents(Marker marker) {
            if (mOptions.getCheckedRadioButtonId() != R.id.custom_info_contents) {
                // This means that the default info contents will be used.
                return null;
            }
            render(marker, mContents);
            return mContents;
        }

        private void render(Marker marker, View view) {

            String title = marker.getTitle();
            TextView titleUi = ((TextView) view.findViewById(R.id.title));
            if (title != null) {
                // Spannable string allows us to edit the formatting of the text.
                SpannableString titleText = new SpannableString(title);
                titleText.setSpan(new ForegroundColorSpan(Color.RED), 0, titleText.length(), 0);
                titleUi.setText(titleText);
            } else {
                titleUi.setText("");
            }

            String snippet = marker.getSnippet();
            TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
            if (snippet != null && snippet.length() > 12) {
                SpannableString snippetText = new SpannableString(snippet);
                snippetText.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 10, 0);
                snippetText.setSpan(new ForegroundColorSpan(Color.BLUE), 12, snippet.length(), 0);
                snippetUi.setText(snippetText);
            } else {
                snippetUi.setText("");
            }
        }
    }
    //above not important....

    private GoogleMap mMap;

    private Marker mCarpark1;

    private Marker mCarpark2;

    private Marker mCarpark3;

    private Marker mCarpark4;

    private Marker mCurrent;

    private Marker bestcarpark;

    String selectCarpark;

    private Marker clickmark;

    ArrayList<LatLng> mmPoints;

    double bestpark;

    Marker[] markers = {mCarpark1,mCarpark2,mCarpark3,mCarpark4};
    /**
     * Keeps track of the last selected marker (though it may no longer be selected).  This is
     * useful for refreshing the info window.
     */
    private Marker mLastSelectedMarker;
    private RadioGroup mOptions;
    //这四个double variable
    double currentLatitude;
    double currentLongitude;
    String carpark1info = "";
    String carpark2info = "";
    String carpark3info = "";
    String carpark4info = "";

    Location curLoc = new Location("currentLocation");
    Location carpark1_location = new Location("carpark1");
    Location carpark2_location = new Location("carpark2");
    Location carpark3_location = new Location("carpark3");
    Location carpark4_location = new Location("carpark4");
    Double distance_1,distance_2,distance_3,distance_4;

    private final Random mRandom = new Random();
    //FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference mWhichCarpark = db.getReference("java1dcarpark").child("selectCarpark");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marker_demo);

        //这里这里 从这里开始
        //FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference mDocRef = db.getReference("java1dcarpark").child("currentLocation");
        DatabaseReference mDocConfig1 = db.getReference("java1dcarpark").child("carpark1Configure");
        DatabaseReference mWhichCarpark = db.getReference("java1dcarpark").child("selectCarpark");


        mWhichCarpark.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                selectCarpark = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        mDocRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mCurrent.remove();
                Map <String, Double> mCurrentmap = (Map)dataSnapshot.getValue();
                currentLatitude = mCurrentmap.get("Latitude");
                currentLongitude = mCurrentmap.get("Longitude");
                Log.d("!!!!!!!!DATABASEVALUE","Value is: "+mCurrentmap.toString());
                currentLocation = new LatLng(currentLatitude,currentLongitude);

                mCurrent = mMap.addMarker(new MarkerOptions()
                        .position(currentLocation)
                        .title("currentlocation")
                        .anchor(0.5f,0.5f)
                        .snippet("you are here"));
                //bestcarpark = GetMin(parks,markers);
                //bestcarpark.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                if(park1<park1&&park1<park3&&park1<park4){
                    bestcarpark = mCarpark1;
                }else if(park2<park1&&park2<park3&&park2<park4){
                    bestcarpark = mCarpark2;
                }else if(park3<park1&&park3<park2&&park3<park4){
                    bestcarpark = mCarpark3;
                }else {
                    bestcarpark = mCarpark4;
                }
                bestcarpark.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("!!!READlocation", "FAIL", databaseError.toException());
            }
        });

        curLoc.setLatitude(currentLocation.latitude);
        curLoc.setLongitude(currentLocation.longitude);


        carpark1_location.setLatitude(carpark1.latitude);
        carpark1_location.setLongitude(carpark1.longitude);
        distance_1 = (carpark1_location.distanceTo(curLoc))* 0.000621371 ;

        carpark2_location.setLatitude(carpark2.latitude);
        carpark2_location.setLongitude(carpark2.longitude);
        distance_2 = (carpark2_location.distanceTo(curLoc))* 0.000621371 ;


        carpark3_location.setLatitude(carpark3.latitude);
        carpark3_location.setLongitude(carpark3.longitude);
        distance_3 = (carpark3_location.distanceTo(curLoc))* 0.000621371 ;


        carpark4_location.setLatitude(carpark4.latitude);
        carpark4_location.setLongitude(carpark4.longitude);
        distance_4 = (carpark4_location.distanceTo(curLoc))* 0.000621371 ;


        mDocConfig1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, String> countMap = (Map) dataSnapshot.getValue();
                Map<String, String> priceMap = (Map) dataSnapshot.getValue();
                String count1 = String.valueOf(countMap.get("carpark1Count"));
                String price1 = String.valueOf(priceMap.get("carpark1Price"));
                Log.d("!!!!!!!!DATABASEVALUE","Count/price is: "+String.valueOf(count1)+"/"+price1);
                carpark1info = Double.toString(distance_1)+"Vacancy: "+count1+"/20"+"\n"+"price: "+price1;
                mCarpark1.setSnippet(distance_1+"miles"+"\n"+"Vacancy: "+count1+"/20"+"\n"+"price: "+price1);
                park1 = (double)Integer.parseInt(count1)/20+Integer.parseInt(price1)+distance_1*100;
                Log.i("zhaohongeeee",park1.toString());
                /*Log.i("zhaohongtessss"," "+count1+" "+price1);
                Log.i("zhaohongTtttttt",distance_1.toString());
                */
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("!!!READcount", "FAIL", databaseError.toException());
            }
        });
        DatabaseReference mDocConfig2 = db.getReference("java1dcarpark").child("carpark2Configure");

        mDocConfig2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, String> countMap = (Map) dataSnapshot.getValue();
                Map<String, String> priceMap = (Map) dataSnapshot.getValue();
                String count2 = String.valueOf(countMap.get("carpark2Count"));
                String price2 = String.valueOf(countMap.get("carpark2Price"));
                Log.d("!!!!!!!!DATABASEVALUE","Count/price is: "+String.valueOf(count2)+"/"+price2);
                carpark2info = Double.toString(distance_2)+"Vacancy: "+count2+"/20"+"\n"+"price: "+price2;
                mCarpark2.setSnippet(distance_2+"miles"+"\n"+"Vacancy: "+count2+"/20"+"\n"+"price: "+price2);
                park2 = (double)Integer.parseInt(count2)/20+Integer.parseInt(price2)+distance_2*100;
                Log.i("zhohhhhh",park2.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("!!!READcount", "FAIL", databaseError.toException());
            }
        });

        DatabaseReference mDocConfig3 = db.getReference("java1dcarpark").child("carpark3Configure");

        mDocConfig3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, String> countMap = (Map) dataSnapshot.getValue();
                Map<String, String> priceMap = (Map) dataSnapshot.getValue();
                String count3 = String.valueOf(countMap.get("carpark3Count"));
                String price3 = String.valueOf(countMap.get("carpark3Price"));
                Log.d("!!!!!!!!DATABASEVALUE","Count/price is: "+String.valueOf(count3)+"/"+price3);
                carpark3info = Double.toString(distance_3)+"Vacancy: "+count3+"/20"+"\n"+"price: "+price3;
                mCarpark3.setSnippet(distance_3+"miles"+"\n"+"Vacancy: "+count3+"/20"+"\n"+"price: "+price3);
                park3 = (double)Integer.parseInt(count3)/20+Integer.parseInt(price3)+distance_3*100;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("!!!READcount", "FAIL", databaseError.toException());
            }
        });

        DatabaseReference mDocConfig4 = db.getReference("java1dcarpark").child("carpark4Configure");

        mDocConfig4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, String> countMap = (Map) dataSnapshot.getValue();
                Map<String, String> priceMap = (Map) dataSnapshot.getValue();
                String count4 = String.valueOf(countMap.get("carpark4Count"));
                String price4 = String.valueOf(countMap.get("carpark4Price"));
                Log.d("!!!!!!!!DATABASEVALUE","Count/price is: "+String.valueOf(count4)+"/"+price4);
                carpark4info = Double.toString(distance_4)+"Vacancy: "+count4+"/20"+"\n"+"price: "+price4;
                mCarpark4.setSnippet(distance_4+"miles"+"\n"+"Vacancy: "+count4+"/20"+"\n"+"price: "+price4);
                park4 = (double)Integer.parseInt(count4)/20+Integer.parseInt(price4)+distance_4*100;
                if(park4<bestpark){
                    bestpark=park4;
                }
                Log.i("zhohhhhh",park4.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("!!!READcount", "FAIL", databaseError.toException());
            }
        });
        //一直到这里~~~~~

        mOptions = (RadioGroup) findViewById(R.id.custom_info_window_options);
        mOptions.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mLastSelectedMarker != null && mLastSelectedMarker.isInfoWindowShown()) {
                    // Refresh the info window when the info window's content has changed.
                    mLastSelectedMarker.showInfoWindow();
                }
            }
        });

        mmPoints = new ArrayList<LatLng>();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        new OnMapAndViewReadyListener(mapFragment, this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        //Log.i("llllhhhh",park1.toString());

        mMap = map;
        // Hide the zoom controls as the button panel will cover it.
        mMap.getUiSettings().setZoomControlsEnabled(false);

        // Add lots of markers to the map.
        addMarkersToMap();


        // Setting an info window adapter allows us to change the both the contents and look of the
        // info window.
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

        // Set listeners for marker events.  See the bottom of this class for their behavior.
        mMap.setOnMarkerClickListener(this);

        // Override the default content description on the view, for accessibility mode.
        // Ideally this string would be localised.
        mMap.setContentDescription("Map with lots of markers.");

        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(carpark1)
                .include(carpark2)
                .include(carpark3)
                .include(carpark4)
                .include(currentLocation)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 40));


        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }
            Context mContext = getBaseContext();
            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(mContext);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(mContext);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(mContext);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);
                return info;
            }
        });


    }

    // carpark1和carpark2改了一丢丢~~~
    private void addMarkersToMap() {
        // Uses a colored icon.
        mCarpark1 = mMap.addMarker(new MarkerOptions()
                .position(carpark1)
                .title("carpark1")
                .snippet(Double.toString(distance_1)+"miles"+"\n"+carpark1info)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        // Uses a custom icon with the info window popping out of the center of the icon.

        // Creates a draggable marker. Long press to drag.
        mCarpark2 = mMap.addMarker(new MarkerOptions()
                .position(carpark2)
                .title("carpark2")
                .snippet(Double.toString(distance_2)+"miles"+"\n"+carpark2info)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mCarpark3 = mMap.addMarker(new MarkerOptions()
                .position(carpark3)
                .title("carpark3")
                .snippet(Double.toString(distance_3)+"miles"+"\n"+carpark3info)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mCarpark4 = mMap.addMarker(new MarkerOptions()
                .position(carpark4)
                .title("carpark4")
                .snippet(Double.toString(distance_4)+"miles"+"\n"+carpark4info)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mCurrent = mMap.addMarker(new MarkerOptions()
                .position(currentLocation)
                .title("currentlocation")
                .anchor(0.5f,0.5f)
                .snippet("you are here"));
    }
    /**
     * Demonstrates converting a {@link Drawable} to a {@link BitmapDescriptor},
     * for use as a marker icon.
     */
    private BitmapDescriptor vectorToBitmap(@DrawableRes int id, @ColorInt int color) {
        Drawable vectorDrawable = ResourcesCompat.getDrawable(getResources(), id, null);
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        DrawableCompat.setTint(vectorDrawable, color);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(this, R.string.map_not_ready, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /** Called when the Clear button is clicked. */
    public void onClearMap(View view) {
        if (!checkReady()) {
            return;
        }
        mMap.clear();
    }

    /** Called when the Reset button is clicked. */
    public void onResetMap(View view) {
        if (!checkReady()) {
            return;
        }
        // Clear the map because we don't want duplicates of the markers.
        mMap.clear();
        addMarkersToMap();
        if(park1<park1&&park1<park3&&park1<park4){
            bestcarpark = mCarpark1;
        }else if(park2<park1&&park2<park3&&park2<park4){
            bestcarpark = mCarpark2;
        }else if(park3<park1&&park3<park2&&park3<park4){
            bestcarpark = mCarpark3;
        }else {
            bestcarpark = mCarpark4;
        }
        Log.i("parksss",Double.toString(park1)+" "+Double.toString(park2)+" "+Double.toString(park3)+" "+Double.toString(park4));
        bestcarpark.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
    }



    public void onPrevious(View view){
        Log.i("previous",selectCarpark);
        if(selectCarpark.equals("carpark1")) {
            Intent intent = new Intent(MarkerDemoActivity.this, PreviousParking.class);
            intent.putExtra("cpno",1);
            startActivity(intent);
        }else if(selectCarpark.equals("carpark2")){
            Intent intent = new Intent(MarkerDemoActivity.this,PreviousParking.class);
            intent.putExtra("cpno",2);
            startActivity(intent);
        }else if(selectCarpark.equals("carpark3")){
            Intent intent = new Intent(MarkerDemoActivity.this,PreviousParking.class);
            intent.putExtra("cpno",3);
            startActivity(intent);
        }else if(selectCarpark.equals("carpark4")){
            Intent intent = new Intent(MarkerDemoActivity.this,PreviousParking.class);
            intent.putExtra("cpno",4);
            startActivity(intent);
        }else {
            Toast.makeText(MarkerDemoActivity.this,
                    "Haven't parking before",
                    Toast.LENGTH_LONG).show();
        }
    }



    public void onArrive(View view){
        if(!checkReady()){
            return;
        }
        if(clickmark!=null){
            mWhichCarpark.setValue(String.valueOf(clickmark.getTitle()));
            if(clickmark == mCarpark1){
                Intent intent = new Intent(MarkerDemoActivity.this,insideparking3.class);
                intent.putExtra("carparkno",1);
                startActivity(intent);
                Log.i("zhaohongtesss","1");
            }else if(clickmark == mCarpark2){
                Intent intent = new Intent(MarkerDemoActivity.this,insideparking3.class);
                intent.putExtra("carparkno",2);
                startActivity(intent);
                Log.i("zhaohongtesss","2");
            }else if(clickmark == mCarpark3){
                Intent intent = new Intent(MarkerDemoActivity.this,insideparking3.class);
                intent.putExtra("carparkno",3);
                startActivity(intent);
            }else if(clickmark == mCarpark4){
                Intent intent = new Intent(MarkerDemoActivity.this,insideparking3.class);
                intent.putExtra("carparkno",4);
                startActivity(intent);
            }
        }else {
            Toast.makeText(MarkerDemoActivity.this,
                    "No carpark selected",
                    Toast.LENGTH_LONG).show();
        }
    }

    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters+"&key=" + "AIzaSyBmJEsjb-GyuLQIdW9MaL2RVpED1bzyaKI" ;
        //Log.i("zhaohong","here2"+url);

        return url;
    }

    /** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){
            Log.d("Exception download url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>{

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try{
                // Fetching the data from web service
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsParser parser = new DirectionsParser();

                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(20);
                lineOptions.color(Color.RED);
            }

            // Drawing polyline in the Google Map for the i-th route
            mMap.addPolyline(lineOptions);
        }
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        clickmark = null;
        LatLng origin=currentLocation;
        LatLng dest;
        String url;
        DownloadTask downloadTask;
        if (marker.equals(mCarpark1)) {
            clickmark = mCarpark1;
            dest=carpark1;
            url = getDirectionsUrl(origin, dest);
            downloadTask = new DownloadTask();
            downloadTask.execute(url);
        }else if(marker.equals(mCarpark2)){
            clickmark = mCarpark2;
            dest=carpark2;
            url = getDirectionsUrl(origin, dest);
            downloadTask = new DownloadTask();
            downloadTask.execute(url);
        }else if(marker.equals(mCarpark3)){
            clickmark = mCarpark3;
            dest=carpark3;
            url = getDirectionsUrl(origin, dest);
            downloadTask = new DownloadTask();
            downloadTask.execute(url);
        }else if(marker.equals(mCarpark4)){
            clickmark = mCarpark4;
            dest=carpark4;
            url = getDirectionsUrl(origin, dest);
            downloadTask = new DownloadTask();
            downloadTask.execute(url);
        }
        // Markers have a z-index that is settable and gettable.
        float zIndex = marker.getZIndex() + 1.0f;
        marker.setZIndex(zIndex);
        Toast.makeText(this, marker.getTitle() + " z-index set to " + zIndex,
                Toast.LENGTH_SHORT).show();

        mLastSelectedMarker = marker;
        // We return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
    Marker GetMin(double [] parks, Marker[] markers){
        double min = parks[0];
        Marker out = markers[0];
        for(int i=0;i<parks.length;i++){
            if(parks[i]<min){
                min = parks[i];
                out = markers[i];
            }
        }
        return out;
    }
}
