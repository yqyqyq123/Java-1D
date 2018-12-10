package com.zhaohong.parkingandroidapp;

public interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyregs(Observer o);
}
