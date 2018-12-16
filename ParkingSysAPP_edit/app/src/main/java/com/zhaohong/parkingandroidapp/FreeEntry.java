package com.zhaohong.parkingandroidapp;

import android.util.Log;

public class FreeEntry extends Entry{
    private String entryID;
    private boolean locked;
    FreeEntry(String id){
        super(id);
    }
    @Override
    public void setLocked() {
        this.locked = false;
        Log.i("!!!!!!!!!!!!!!!!","setfree");
    }
    @Override
    public boolean isLocked(){
        return this.locked;
    }
}
