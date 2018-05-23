package com.parking.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.Model.ParkingRepository;
import com.parking.Model.RegisterRepository;
import com.parking.Pojo.ParkingDetails;
import com.parking.Pojo.RegistrationDetails;
import com.parking.Pojo.Status;

@Service
public class ParkingService {

	@Autowired
	ParkingRepository parkingrepo;
	
	@Autowired
	RegisterRepository registerrepo;
	
	public List<ParkingDetails> getParkingDetails(){
		return parkingrepo.findAll();
	}
	
	public ArrayList<String> getregistrationbyColor(String color){
		List<RegistrationDetails> list = registerrepo.getregistrationbyColor(color);
		ArrayList<String> array = new ArrayList<String>();
		Iterator<RegistrationDetails> it = list.iterator();
		while (it.hasNext()) {
			array.add(it.next().getRegistration());
		}
		return array;
	}
	
	public List<RegistrationDetails> getSlotbyRegistration(String registration){
		List<RegistrationDetails> list = registerrepo.retrieveData(registration);
		return list;
	}
	
	public ArrayList<HashMap<String,Long>> getcolorbySlot(String color){
		List<RegistrationDetails> list = registerrepo.getregistrationbyColor(color);
		ArrayList<HashMap<String,Long>> array = new ArrayList<HashMap<String,Long>>();
		Iterator<RegistrationDetails> it = list.iterator();
		while (it.hasNext()) {
			HashMap<String,Long> map = new HashMap<String, Long>();
			RegistrationDetails details=it.next();
			map.put("slot", details.getSlot());
			map.put("level", details.getLevel());
			array.add(map);
		}
		return array;
	}

	public Status registerVehicle(RegistrationDetails register) {


		ParkingDetails details = null;
		long slot = 0;
		Status status = new Status();

		long registerLevel = register.getLevel();
		long registerSlot = register.getSlot();
		if (registerSlot <= 10 & registerSlot >= 0) {
			String registrationID = register.getRegistration();
			Optional<ParkingDetails> optional = parkingrepo.findById(registerLevel);
			if (optional.isPresent()) {
				details = optional.get();
				slot = details.getSlot();
				if (slot == 0) {
					status.setStatus("false");
				} else {
					List<RegistrationDetails> list = registerrepo.retrieveData(registrationID);
					if (!list.isEmpty()) {
						status.setStatus("false");
					} else {
						List<RegistrationDetails> registerDetails = registerrepo.getRegisterData(registerSlot,
								registerLevel);
						if (registerDetails.isEmpty()) {
							details.setSlot(slot - 1);
							if (slot - 1 == 0) {
								details.setAvailable("false");
							}
							parkingrepo.save(details);
							registerrepo.save(register);
							status.setStatus("true");
						} else {
							status.setStatus("false");
						}
					}
				}
			} else {
				status.setStatus("false");
			}
		}
		 else {
			status.setStatus("false");
		}

		return status;
	
	}
	
	public Status deleteRegistration(RegistrationDetails register) {

		String registrationID = register.getRegistration();
		List<RegistrationDetails> list = registerrepo.retrieveData(registrationID);
		Status status = new Status();
		String flag = "false";
		if (!list.isEmpty()) {
			RegistrationDetails details = null;
			ParkingDetails parkingDetails = null;
			long parkingSlot = 0;
			Iterator<RegistrationDetails> it = list.iterator();
			while (it.hasNext()) {
				details = it.next();
				registerrepo.deleteById(registrationID);
				Optional<ParkingDetails> optional = parkingrepo.findById(details.getLevel());
				if (optional.isPresent()) {
					parkingDetails = optional.get();
					parkingSlot = parkingDetails.getSlot();

					parkingDetails.setSlot(1 + parkingSlot);
					parkingDetails.setAvailable("true");
					parkingrepo.save(parkingDetails);

					flag = "true";
				}
			}
		}

		status.setStatus(flag);
		return status;

	
	}
}
