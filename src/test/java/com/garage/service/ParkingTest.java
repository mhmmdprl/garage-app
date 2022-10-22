package com.garage.service;

import com.garage.enums.ResponseType;
import com.garage.enums.VehicleType;
import com.garage.exception.CustomException;
import com.garage.model.Ticket;
import com.garage.model.Vehicle;
import com.garage.response.ResponseMessage;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public class ParkingTest {

    @InjectMocks
    private ParkingService parkingService;


    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Before
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void vehicle_park_and_leave() {
        Vehicle vehicleTruck = new Vehicle(UUID.randomUUID().toString(), "GREEN", VehicleType.TRUCK);
        Ticket ticket = this.parkingService.park(vehicleTruck);
        Assert.assertEquals(vehicleTruck.getPlate(), ticket.getPlate());

        String message = this.parkingService.leave(ticket.getTicketId());
        StringBuilder sb = new StringBuilder("Vacant Slots : ");
        ticket.getSlotNumbers().forEach(number -> sb.append(number + " "));
        Assert.assertEquals(sb.toString(), message);
    }

    @Test
    public void when_park_is_full_return_exception_message() {
        Vehicle vehicleTruck = new Vehicle("34 PRL 123", "GREEN", VehicleType.TRUCK);
        Vehicle vehicleTruck2 = new Vehicle("34 PRL 124", "REED", VehicleType.TRUCK);
        Vehicle vehicleTruck3 = new Vehicle("34 PRL 125", "BLUE", VehicleType.TRUCK);

        try {
            this.parkingService.park(vehicleTruck);
            this.parkingService.park(vehicleTruck2);
            this.parkingService.park(vehicleTruck3);
        } catch (CustomException e) {
            Assert.assertEquals("Garage is full!", e.getMessage());
            this.parkingService.vacateAllSlot();
        }
    }

    @Test
    public void check_validation_park_service() {
        Vehicle vehicleTruck = new Vehicle("34 PRL 123", "GREEN", VehicleType.TRUCK);
        vehicleTruck.setPlate(null);
        ResponseMessage response = this.restTemplate.postForObject("http://localhost:8080/garage/park", vehicleTruck,
            ResponseMessage.class);
        Assert.assertEquals(ResponseType.ERROR, response.getType());
    }

    @Test
    public void check_invalid_id_leave_service() {
        String invalidId = "Invalid id";
        HttpEntity entity = new HttpEntity(new HttpHeaders());
        ResponseMessage response = this.restTemplate.exchange("http://localhost:8080/garage/leave?id=" + invalidId, HttpMethod.PUT, entity,
            ResponseMessage.class).getBody();
        Assert.assertEquals(ResponseType.ERROR, response.getType());
    }

    @Test
    public void check_status_service() {
        ResponseMessage response = this.restTemplate.getForObject("http://localhost:8080/garage/status",
            ResponseMessage.class);
        Assert.assertEquals(ResponseType.SUCCESS, response.getType());
    }
}
