package com.zhaohong.parkingandroidapp;

import java.util.ArrayList;
import java.util.List;

public class AppData {
    private static final List<Integer> bookedSlots = new ArrayList<Integer>();
    private static int selectedSlot = -1;
    private static String slotName = "";
    // Private constructor prevents instantiation from other classes
    private AppData() {}

    public static List getBookedSlots() {
        return bookedSlots;
    }

    public static void setBookedSlots(List slots) {
        bookedSlots.clear();
        bookedSlots.addAll(slots);
    }

    public static void bookSlot(int position) {
        if(!isSlotBooked(position)){
            bookedSlots.add(position);
        }
    }

    public static void setSlotName(String name) {
        slotName = name;
    }

    public static String getSlotName() {
        return slotName;
    }

    public static boolean isSlotBooked(int position) {
        return bookedSlots.contains(position);
    }

    public static void setSelectedSlot(int position) {
        selectedSlot = position;
    }

    public static int getSelectedSlot() {
        return selectedSlot;
    }
}