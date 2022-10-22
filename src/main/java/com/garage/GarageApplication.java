package com.garage;

import com.garage.service.ParkingService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GarageApplication{

	@Autowired
	private ParkingService parkingService;

	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		SpringApplication.run(GarageApplication.class, args);
	}

}
