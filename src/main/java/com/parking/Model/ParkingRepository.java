package com.parking.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parking.Pojo.*;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingDetails, Long> {
	
}
