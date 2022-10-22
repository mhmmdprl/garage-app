package com.garage.dto;

import com.garage.enums.VehicleType;
import java.util.List;

public class StatusDto {

    private String plate;
    private String color;
    private List<Integer> slotNumberList;
    private VehicleType vehicleType;

    public StatusDto(String plate, String color, List<Integer> slotNumberList) {
        this.plate = plate;
        this.slotNumberList = slotNumberList;
        this.color = color;
        this.vehicleType = VehicleType.get(slotNumberList.size());
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Integer> getSlotNumberList() {
        return slotNumberList;
    }

    public void setSlotNumberList(List<Integer> slotNumberList) {
        this.slotNumberList = slotNumberList;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
