package com.exploreinn.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer bookingId;
	private Integer noOfPeople;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private LocalDateTime timeOfBooking;
	private float totalCost;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "destination_id", unique = true)
	private Destination destinationEntity;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User userEntity;
	
	public LocalDateTime getTimeOfBooking() {
		return timeOfBooking;
	}
	public void setTimeOfBooking(LocalDateTime timeOfBooking) {
		this.timeOfBooking = timeOfBooking;
	}
	
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getNoOfPeople() {
		return noOfPeople;
	}
	public void setNoOfPeople(Integer noOfPeople) {
		this.noOfPeople = noOfPeople;
	}
	public LocalDate getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(LocalDate checkIn) {
		this.checkIn = checkIn;
	}
	public LocalDate getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(LocalDate checkOut) {
		this.checkOut = checkOut;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	public Destination getDestinationEntity() {
		return destinationEntity;
	}
	public void setDestinationEntity(Destination destinationEntity) {
		this.destinationEntity = destinationEntity;
	}
	public User getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(User userEntity) {
		this.userEntity = userEntity;
	}
	

}
