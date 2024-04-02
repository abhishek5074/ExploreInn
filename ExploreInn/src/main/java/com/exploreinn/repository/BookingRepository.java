package com.exploreinn.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.exploreinn.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
	
	List<Booking> findByUserEntityUserId(Integer userId);


}
