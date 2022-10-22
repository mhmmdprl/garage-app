package com.garage.model;

import com.garage.enums.VehicleType;
import javax.validation.constraints.NotNull;

public class Vehicle {

    @NotNull
    private String plate;
    @NotNull
    private String color;
    @NotNull
    private VehicleType vehicleSize;

    public Vehicle(String plate, String color, VehicleType vehicleSize) {
        this.plate = plate;
        this.color = color;
        this.vehicleSize = vehicleSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getVehicleSize() {
        return vehicleSize;
    }

    public void setVehicleSize(VehicleType vehicleSize) {
        this.vehicleSize = vehicleSize;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
