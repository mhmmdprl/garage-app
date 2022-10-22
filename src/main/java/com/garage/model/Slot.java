package com.garage.model;

public class Slot {

    private String ticketId;
    private int slotNumber;
    private boolean isEmpty;
    private Vehicle parkVehicle;

    public Slot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.ticketId = null;
        isEmpty = true;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public Vehicle getParkVehicle() {
        return parkVehicle;
    }

    public void setParkVehicle(Vehicle parkVehicle) {
        this.parkVehicle = parkVehicle;
    }

    public void vacateSlot() {
        parkVehicle = null;
        this.isEmpty = true;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void fillSlot(Vehicle parkVehicle, String ticketId) {
        this.parkVehicle = parkVehicle;
        this.ticketId = ticketId;
        this.isEmpty = false;
    }


}
