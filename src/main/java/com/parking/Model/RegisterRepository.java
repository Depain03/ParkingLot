package com.parking.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.parking.Pojo.RegistrationDetails;

@Repository
public interface RegisterRepository extends JpaRepository<RegistrationDetails, String>{
	
	@Modifying
	@Transactional
	@Query(value="select r from RegistrationDetails r where r.registration = :registration")
	List<RegistrationDetails> retrieveData(@Param("registration") String registration);
	
	@Modifying
	@Transactional
	@Query(value="select r from RegistrationDetails r where r.slot = :slot and r.level = :level")
	List<RegistrationDetails> getRegisterData(@Param("slot") long slot, @Param("level") long level);
	
	@Modifying
	@Transactional
	@Query(value="select r from RegistrationDetails r where r.colour = :colour")
	List<RegistrationDetails> getregistrationbyColor(@Param("colour") String colour);
}
