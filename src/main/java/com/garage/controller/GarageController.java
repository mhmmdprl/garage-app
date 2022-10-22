package com.garage.controller;

import com.garage.response.ResponseMessage;
import com.garage.service.ParkingService;
import com.garage.model.Ticket;
import com.garage.model.Vehicle;
import com.garage.util.ResponseUtil;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/garage")
public class GarageController {

    @Autowired
    private ParkingService parkingService;

    @PostMapping("/park")
    public Ticket park(@RequestBody @Valid Vehicle vehicle) {
        return this.parkingService.park(vehicle);
    }

    @PutMapping("/leave")
    public ResponseMessage<String> leave(@RequestParam(value = "id") String plateOrTicketId) {
        return ResponseUtil.createSuccessMessage(this.parkingService.leave(plateOrTicketId));
    }

    @GetMapping("/status")
    public Object getStatus() {
        return ResponseUtil.createSuccessMessage(this.parkingService.getStatus());
    }
}
