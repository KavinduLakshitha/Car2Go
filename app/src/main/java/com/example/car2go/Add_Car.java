package com.example.car2go;

public class Add_Car {
    private String name;
    private Integer distance;
    private String insurance;
    private String description;

    public Add_Car(String name, String s, String insurance, String description){}
    public Add_Car(String name,Integer distance,String insurance,String description){
        this.name = name;
        this.distance = distance;
        this.insurance = insurance;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Integer getDistance() {
        return distance;
    }

    public String getInsurance() {
        return insurance;
    }

    public String getDescription() {
        return description;
    }
}
