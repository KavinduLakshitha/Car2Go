package com.example.car2go.data;

public class TripDetails {

    private String tripDetailsID;
    private  String nic;
    private String start;
    private String end;
    private String totalDays;
    private String pickup;
    private String retLocation;

    public TripDetails(String id, String nic, String start, String end, String totalDays, String pickup, String retLocation) {

        this.nic = nic;
        this.tripDetailsID= id;
        this.start =  start;
        this.end = end;
        this.totalDays = totalDays;
        this.pickup = pickup;
        this.retLocation = retLocation;

    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getTripDetailsID() {

        return tripDetailsID;
    }

    public void setTripDetailsID(String tripDetailsID) {

        this.tripDetailsID = tripDetailsID;
    }

    public String getStart() {

        return start;
    }

    public void setStart(String start) {

        this.start = start;
    }

    public String getEnd() {

        return end;
    }

    public void setEnd(String end) {

        this.end = end;
    }

    public String getTotalDays() {

        return totalDays;
    }

    public void setTotalDays(String totalDays) {

        this.totalDays = totalDays;
    }

    public String getPickup() {

        return pickup;
    }

    public void setPickup(String pickup) {

        this.pickup = pickup;
    }

    public String getRetLocation() {

        return retLocation;
    }

    public void setRetLocation(String retLocation) {

        this.retLocation = retLocation;
    }
}


