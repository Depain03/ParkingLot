package com.parking.Pojo;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="parking_details")
public class ParkingDetails {

	@Id
	private long level;
	private long slot;
	private String available;
	
	public Long getLevel() {
		return level;
	}
	public void setLevel(Long level) {
		this.level = level;
	}
	public Long getSlot() {
		return slot;
	}
	public void setSlot(Long slot) {
		this.slot = slot;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}

	
	
}
