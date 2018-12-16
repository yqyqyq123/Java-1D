package com.zhaohong.parkingandroidapp;

import android.util.Log;

public class LockedEntry extends Entry {
    private String entryID;
    private boolean locked;

    LockedEntry(String id){
        super(id);
    }
    @Override
    public void setLocked(){
        this.locked = true;
        Log.i("!!!!!!!!!","setLok");
    }
    @Override
    public boolean isLocked(){
        return this.locked;
    }
}
