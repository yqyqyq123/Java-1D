package com.zhaohong.parkingandroidapp;

public abstract class Entry{

    private String entryID;
    private boolean locked;

    /**
     * Entry object constructor
     * @param id of this entry, the parking lot object it is associated with
     */
    public Entry(String id){
        this.entryID = id;
        this.locked = false;
    }

    /**
     * Getter method for the entry ID variable
     * @return the ID of the entry
     */
    public String getEntryID() {
        return entryID;
    }

    /**
     * Setter method for the entry ID
     * @param entryID
     */
    public void setEntryID(String entryID) {
        this.entryID = entryID;
    }

    /**
     * Signals the parking lot this entry is associated with
     * to check for available space
     */

    /**
     * Getter method for the locked variable
     * @return a boolean value that determines whether the entry is locked or not
     */
    public abstract boolean isLocked();


    /*public void setLocked(boolean locked) {

        this.locked = locked;
    }*/

    public abstract void setLocked();

}
