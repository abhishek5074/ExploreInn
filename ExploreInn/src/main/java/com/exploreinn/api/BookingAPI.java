package com.exploreinn.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exploreinn.dto.BookingDTO;
import com.exploreinn.exception.ExploreInnException;
import com.exploreinn.service.BookingService;

@CrossOrigin
@RestController
@RequestMapping(value="booking-api")
public class BookingAPI {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping(value="/bookings/{userId}/{destinationId}")
	public ResponseEntity<Integer> addBooking(@RequestBody BookingDTO booking , @PathVariable Integer userId, @PathVariable String destinationId) throws ExploreInnException{
		Integer res = bookingService.addBooking(booking, userId, destinationId);
	    return new ResponseEntity<>(res,HttpStatus.OK);
	}
	@GetMapping(value="/{userId}")
	public ResponseEntity<List<BookingDTO>> getBookingRecords(@PathVariable Integer userId) throws Exception{
		List<BookingDTO> results = bookingService.getBooking(userId);
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/bookings/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable Integer bookingId)throws ExploreInnException{   
		String res = bookingService.deleteBooking(bookingId);
	    return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	

}
