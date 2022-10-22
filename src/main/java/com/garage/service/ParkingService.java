package com.garage.service;

import com.garage.dto.StatusDto;
import com.garage.exception.CustomException;
import com.garage.model.Slot;
import com.garage.model.Ticket;
import com.garage.model.Vehicle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ParkingService {

    private static List<Slot> SLOTS = Collections.synchronizedList(new ArrayList<>());
    private final static Logger LOGGER = LoggerFactory.getLogger(ParkingService.class);

    static {
        synchronized (SLOTS) {
            for (int i = 1; i <= 10; i++) {
                SLOTS.add(new Slot(i));
            }
        }
        LOGGER.info("Created parking area with {} slots", 10);
    }


    public synchronized Ticket park(Vehicle vehicle) {
        doesPlateExists(vehicle.getPlate());
        List<Integer> slotNumberList = getAvailableSlotNumber(vehicle);
        String ticketId = UUID.randomUUID().toString();
        slotNumberList.forEach(slotNumber -> SLOTS.get(slotNumber - 1).fillSlot(vehicle, ticketId));
        LOGGER.info("Allocated slot number: {}", vehicle.getVehicleSize().getSize());
        Ticket ticket = new Ticket(ticketId, slotNumberList, vehicle.getPlate(),
            vehicle.getVehicleSize(), new Date());
        return ticket;
    }


    public synchronized String leave(String plateOrTicketId) {
        StringBuilder stringBuilder;
        if (SLOTS.stream().anyMatch(slot -> plateOrTicketId.equals(slot.getTicketId()))) {
            stringBuilder = new StringBuilder("Vacant Slots : ");
            SLOTS.stream().filter(slot -> plateOrTicketId.equals(slot.getTicketId())).forEach(slot -> {
                stringBuilder.append(slot.getSlotNumber());
                stringBuilder.append(" ");
                slot.vacateSlot();
            });
            return stringBuilder.toString();
        } else if (SLOTS.stream().anyMatch(slot -> slot.getParkVehicle() != null && plateOrTicketId.equals(slot.getParkVehicle().getPlate()))) {
            stringBuilder = new StringBuilder();
            SLOTS.stream().filter(slot -> slot.getParkVehicle() != null && plateOrTicketId.equals(slot.getParkVehicle().getPlate())).forEach(slot -> {
                stringBuilder.append(slot.getSlotNumber());
                stringBuilder.append(" ");
                slot.vacateSlot();
            });
            return stringBuilder.toString();
        }
        throw new CustomException("Invalid plate or ticketId : {0}", HttpStatus.BAD_REQUEST, plateOrTicketId);
    }

    private List<Integer> getAvailableSlotNumber(Vehicle vehicle) {
        int count = 0;
        List<Integer> slotNumbers = new ArrayList<>();
        for (Slot slot : SLOTS) {
            if (slot.isEmpty()) {
                count++;
                slotNumbers.add(slot.getSlotNumber());
                if (vehicle.getVehicleSize().getSize().equals(count)) {
                    return slotNumbers;
                }
            } else {
                count = 0;
                slotNumbers.clear();
            }
        }
        LOGGER.error("Garage is full for vehicle with plate : {} ", vehicle.getPlate());
        throw new CustomException("Garage is full!", HttpStatus.BAD_REQUEST, null);
    }

    public synchronized List<StatusDto> getStatus() {

        List<StatusDto> statusDtoList;
        List<Slot> notEmptySlotList = SLOTS.stream().filter(slot -> !slot.isEmpty()).collect(Collectors.toList());
        if (notEmptySlotList != null) {
            statusDtoList = new ArrayList<>();
            Set<String> plates = notEmptySlotList.stream()
                .map(slot -> slot.getParkVehicle().getPlate()).collect(Collectors.toSet());

            plates.forEach(plate -> {
                List<Slot> slotWithPlate = notEmptySlotList.stream().filter(slot -> slot.getParkVehicle().getPlate().equals(plate)).collect(Collectors.toList());
                List<Integer> slotNumberList = slotWithPlate.stream()
                    .map(slot -> slot.getSlotNumber()).collect(Collectors.toList());
                String color = slotWithPlate.stream()
                    .map(slot -> slot.getParkVehicle().getColor()).findFirst().orElse("Invalid Color");
                statusDtoList.add(new StatusDto(plate, color, slotNumberList));
            });

            return statusDtoList;
        }
        return Collections.emptyList();
    }

    private void doesPlateExists(String plate) {
        boolean existsPlate = SLOTS.stream().filter(slot -> !slot.isEmpty()).anyMatch(slot -> slot.getParkVehicle().getPlate().equals(plate));
        if (existsPlate) {
            throw new CustomException("Vehicle already exists with plate {0}", HttpStatus.BAD_REQUEST, plate);
        }
    }

    public void vacateAllSlot() {
        for (Slot slot : SLOTS) {
            slot.vacateSlot();
        }
    }

}
