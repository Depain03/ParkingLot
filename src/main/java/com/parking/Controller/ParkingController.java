package com.parking.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.Model.ParkingRepository;
import com.parking.Model.RegisterRepository;
import com.parking.Pojo.*;
import com.parking.Service.ParkingService;

@RestController
@RequestMapping("/api")
public class ParkingController {

	@Autowired
	ParkingService service;
	
	@GetMapping("/parking")
	public List<ParkingDetails> getParkingDetails() {
		return service.getParkingDetails();
	}

	@GetMapping("/parking/_search/{color}/registration")
	public ArrayList<String> getregistrationbyColor(@PathVariable(value = "color") String color) {
		return service.getregistrationbyColor(color);
	}

	@GetMapping("/parking/_search/{registration}/slot")
	public List<RegistrationDetails> getSlotbyRegistration(@PathVariable(value = "registration") String registration) {
		return service.getSlotbyRegistration(registration);
	}

	@GetMapping("/parking/_search/{color}/slots")
	public ArrayList<HashMap<String,Long>> getcolorbySlot(@PathVariable(value = "color") String color) {
		return service.getcolorbySlot(color);
	}
	
	@PostMapping("/parking")
	public Status registerVehicle(@Valid @RequestBody RegistrationDetails register) {
		return service.registerVehicle(register);
	}

	@DeleteMapping("/parking")
	public Status deleteRegistration(@Valid @RequestBody RegistrationDetails register) {
		return service.deleteRegistration(register);
	}

	
}
