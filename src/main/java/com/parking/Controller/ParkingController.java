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

@RestController
@RequestMapping("/api")
public class ParkingController {

	@Autowired
	ParkingRepository parkingrepo;

	@Autowired
	RegisterRepository registerrepo;

	@GetMapping("/parking")
	public List<ParkingDetails> getParkingDetails() {
		return parkingrepo.findAll();
	}

	@PostMapping("/parking")
	public Status registerVehicle(@Valid @RequestBody RegistrationDetails register) {

		ParkingDetails details = null;
		long slot = 0;
		Status status = new Status();

		long registerLevel = register.getLevel();
		long registerSlot = register.getSlot();
		if (registerSlot<=10 && registerSlot>=0) {
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
					}
					else {
						List<RegistrationDetails> registerDetails = registerrepo.getRegisterData(registerSlot,registerLevel);
						if (registerDetails.isEmpty()) {
							details.setSlot(slot - 1);
							if (slot -1 == 0) {
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
		}String registrationID = register.getRegistration();
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
				}
				else {
					List<RegistrationDetails> registerDetails = registerrepo.getRegisterData(registerSlot,registerLevel);
					if (registerDetails.isEmpty()) {
						details.setSlot(slot - 1);
						if (slot -1 == 0) {
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
		
		return status;
	}

	@DeleteMapping("/parking")
	public Status deleteRegistration(@Valid @RequestBody RegistrationDetails register) {
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

	@GetMapping("/parking/_search/{color}/registration")
	public HashMap<String, ArrayList<String>> getregistrationbyColor(@PathVariable(value = "color") String color){
		List<RegistrationDetails> list=registerrepo.getregistrationbyColor(color);
		HashMap<String, ArrayList<String>> map= new HashMap<String, ArrayList<String>>();
		ArrayList<String> array=new ArrayList<String>();
		Iterator<RegistrationDetails > it=list.iterator();
		while(it.hasNext()) {
			array.add(it.next().getRegistration());
		}
		map.put("registration", array);
		return map;
	}
	
	@GetMapping("/parking/_search/{registration}/slot")
	public List<RegistrationDetails> getSlotbyRegistration(@PathVariable(value = "registration") String registration){
		List<RegistrationDetails> list=registerrepo.retrieveData(registration);
		return list;
	}
	
	@GetMapping("/parking/_search/{slot}/colour")
	public List<RegistrationDetails> getcolorbySlot(@PathVariable(value = "slot") String slot){
		List<RegistrationDetails> list=registerrepo.retrieveData(slot);
		return list;
	}
}
