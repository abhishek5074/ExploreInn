package com.exploreinn.service;

import java.util.List;

import com.exploreinn.dto.BookingDTO;
import com.exploreinn.exception.ExploreInnException;

public interface BookingService {

	public Integer addBooking(BookingDTO booking,Integer userId, String destinationId) throws ExploreInnException;
	public List<BookingDTO> getBooking(Integer userId) throws ExploreInnException;
	public String deleteBooking(Integer bookingId) throws ExploreInnException;
	
}
