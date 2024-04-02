package com.exploreinn.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exploreinn.dto.BookingDTO;
import com.exploreinn.dto.DestinationDTO;
import com.exploreinn.dto.DetailsDTO;
import com.exploreinn.dto.ItineraryDTO;
import com.exploreinn.dto.UserDTO;
import com.exploreinn.entity.Booking;
import com.exploreinn.entity.Destination;
import com.exploreinn.entity.User;
import com.exploreinn.exception.ExploreInnException;
import com.exploreinn.repository.BookingRepository;
import com.exploreinn.repository.PackageRepository;
import com.exploreinn.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingServiceImpl implements BookingService{
	
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PackageRepository packageRepository;
	
	public Integer addBooking(BookingDTO booking, Integer userId, String destinationId) throws ExploreInnException {
	      if (userId != null && destinationId != null) {
	         Booking bookingEntity = new Booking();
	         bookingEntity.setBookingId(booking.getBookingId());
	         bookingEntity.setCheckIn(booking.getCheckIn());
	         bookingEntity.setCheckOut(booking.getCheckOut());
	         
	         Optional<Destination> optional = packageRepository.findById(destinationId);
	 		 Destination destination = optional.orElseThrow(() -> new ExploreInnException("BookingService.ITINERARY_UNAVAILABLE"));
	 		
	         bookingEntity.setDestinationEntity(destination);
	         bookingEntity.setNoOfPeople(booking.getNoOfPeople());
	         bookingEntity.setTimeOfBooking(LocalDateTime.now());
	         bookingEntity.setTotalCost(this.getTotalCost(booking.getNoOfPeople(), ChronoUnit.DAYS.between(booking.getCheckIn(), booking.getCheckOut()),destination.getChargePerPerson()));
	         
	         Optional<User> userOp = userRepository.findById(userId);
	         User user = userOp.orElseThrow(() -> new ExploreInnException("BookingService.NO_USER"));
	         
	         bookingEntity.setUserEntity(user);
	         Booking booking2 = (Booking) bookingRepository.save(bookingEntity);
	         return booking2.getBookingId();
	      } else {
	         throw new ExploreInnException("BookingService.BOOKING_ERROR");
	      }
	   }

	   private float getTotalCost(int noOfPeople, long totalDays, float chargePerPerson) {
	      return (float)noOfPeople * chargePerPerson * (float)totalDays;
	   }

	   public List<BookingDTO> getBooking(Integer userId) throws ExploreInnException {
	      List<Booking> bookingList = bookingRepository.findByUserEntityUserId(userId);
	      Optional<User> userOp = userRepository.findById(userId);
	      User user = userOp.orElseThrow(() -> new ExploreInnException("BookingService.NO_USER"));
	      UserDTO userDTO = new UserDTO();
	      userDTO.setContactNumber(user.getContactNumber());
	      userDTO.setEmailId(user.getEmailId());
	      userDTO.setPassword(user.getPassword());
	      userDTO.setUserId(user.getUserId());
	      userDTO.setUserName(user.getUserName());
	      
	      List<BookingDTO> bookingDtos = new ArrayList<>();
	      Iterator<Booking> iterator = bookingList.iterator();

	      while(iterator.hasNext()) {
	         Booking b = (Booking)iterator.next();
	         BookingDTO dto = new BookingDTO();
	         dto.setBookingId(b.getBookingId());
	         dto.setCheckIn(b.getCheckIn());
	         dto.setCheckOut(b.getCheckOut());
	         
	         Optional<Destination> optional = packageRepository.findById(b.getDestinationEntity().getDestinationId());
	 		 Destination destination = optional.orElseThrow(() -> new ExploreInnException("BookingService.ITINERARY_UNAVAILABLE"));
	         
	         DestinationDTO destinationDTO = new DestinationDTO();
	         destinationDTO.setDestinationId(destination.getDestinationId());
	         destinationDTO.setAvailability(destination.getAvailability());
	         destinationDTO.setChargePerPerson(destination.getChargePerPerson());
	         destinationDTO.setContinent(destination.getContinent());
	         destinationDTO.setDestinationName(destination.getDestinationName());
	         
	         DetailsDTO detailsDTO = new DetailsDTO();
	         detailsDTO.setAbout(destination.getDetails().getAbout());
	         detailsDTO.setDetailsId(destination.getDetails().getDetailsId());
	         detailsDTO.setHighlights(destination.getDetails().getHighlights());
	         
	         ItineraryDTO itineraryDTO = new ItineraryDTO();
	         itineraryDTO.setFirstDay(destination.getDetails().getItinerary().getFirstDay());
	         itineraryDTO.setItineraryId(destination.getDetails().getItinerary().getItineraryId());
	         itineraryDTO.setLastDay(destination.getDetails().getItinerary().getLastDay());
	         itineraryDTO.setRestOfDays(destination.getDetails().getItinerary().getLastDay());
	         
	         detailsDTO.setItinerary(itineraryDTO);
	         detailsDTO.setPace(destination.getDetails().getPace());
	         detailsDTO.setPackageInclusion(destination.getDetails().getPackageInclusion());
	         
	         destinationDTO.setDetails(detailsDTO);
	         destinationDTO.setDiscount(destination.getDiscount());
	         destinationDTO.setFlightCharge(destination.getFlightCharge());
	         destinationDTO.setImageUrl(destination.getImageUrl());
	         destinationDTO.setNoOfNights(destination.getNoOfNights());
	         
	         dto.setDestination(destinationDTO);
	         dto.setNoOfPeople(b.getNoOfPeople());
	         dto.setTimeOfBooking(b.getTimeOfBooking());
	         dto.setTotalCost(b.getTotalCost());
	         dto.setUsers(userDTO);
	         bookingDtos.add(dto);
	      }

	      if (bookingDtos.isEmpty()) {
	         throw new ExploreInnException("BookingService.NO_BOOKING");
	      } else {
	         return bookingDtos;
	      }
	   }

	   public String deleteBooking(Integer bookingId) throws ExploreInnException {
	      Optional<Booking> bookingOp = this.bookingRepository.findById(bookingId);
	      bookingOp.orElseThrow(() -> {
	         return new ExploreInnException("BookingService.NO_BOOKING");
	      });
	      if (bookingId != null) {
	         this.bookingRepository.deleteById(bookingId);
	      }

	      return "successfully deleted";
	   }

}
