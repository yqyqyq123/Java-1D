package com.zhaohong.parkingandroidapp;

public class Exit {

    private String exitID;
    private Lot parkingLot;

    /**
     * Exit object constructor
     * @param id, parkingLot
     */
    public Exit(String id, Lot parkingLot){
        this.exitID = id;
        this.parkingLot = parkingLot;

    }

    /**
     * Signals the parking lot of a car exiting the lot
     */
    public void notifyLotOfExitingCar(){
        synchronized(parkingLot){
            parkingLot.checkForCarsExiting();
        }
    }

}
