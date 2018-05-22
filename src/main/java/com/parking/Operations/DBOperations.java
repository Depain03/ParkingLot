package com.parking.Operations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.parking.Model.ParkingRepository;
import com.parking.Model.RegisterRepository;
import com.parking.Pojo.ParkingDetails;

public class DBOperations {

	@Autowired
	ParkingRepository parkingrepo;

	@Autowired
	RegisterRepository registerrepo;
	
	@Bean
	public List<ParkingDetails> getParkingDetails() {
		return parkingrepo.findAll();
	}
}
