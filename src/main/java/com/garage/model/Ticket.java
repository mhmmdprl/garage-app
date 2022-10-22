package com.garage.model;

import com.garage.enums.VehicleType;
import java.util.Date;
import java.util.List;

public class Ticket {

    private String ticketId;
    private List<Integer> slotNumbers;
    private String plate;
    private Date date;
    private VehicleType vehicleType;

    public Ticket(String ticketId, List<Integer> slotNumber, String plate, VehicleType vehicleSize, Date date) {
        this.ticketId = ticketId;
        this.slotNumbers = slotNumber;
        this.plate = plate;
        this.date = date;
        this.setVehicleType(vehicleSize);
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Integer> getSlotNumbers() {
        return slotNumbers;
    }

    public void setSlotNumbers(List<Integer> slotNumbers) {
        this.slotNumbers = slotNumbers;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
