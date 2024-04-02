package com.exploreinn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exploreinn.dto.DestinationDTO;
import com.exploreinn.dto.DetailsDTO;
import com.exploreinn.dto.ItineraryDTO;
import com.exploreinn.entity.Destination;
import com.exploreinn.exception.ExploreInnException;
import com.exploreinn.repository.PackageRepository;

@Service
public class PackageServiceImpl implements PackageService{
	
	@Autowired
	private PackageRepository packageRepository;
	
	
	@Override
	public List<DestinationDTO> getPackagesSearch(String continent) throws ExploreInnException{
		List<Destination> list = packageRepository.findAllByContinent(continent);
		
		List<DestinationDTO> dtos = new ArrayList<>();
		
		for ( Destination ob : list) {
			DestinationDTO dto = convertDestinationToDestinationDTO(ob);
			dtos.add(dto);
		}
		
		if(dtos.isEmpty())
			throw new ExploreInnException("PackageService.PACKAGE_UNAVAILABLE");
		
		return dtos;		
	}
	
	public DestinationDTO getItinerary(String destinationId) throws ExploreInnException{
		Optional<Destination> optional = packageRepository.findById(destinationId);
		Destination destination = optional.orElseThrow(() -> new ExploreInnException("PackageService.ITINERARY_UNAVAILABLE"));
		
		DestinationDTO dto = convertDestinationToDestinationDTO(destination); 
		return dto;
	}
	
	@Override
	public List<DestinationDTO> getPackages() throws ExploreInnException {
		Iterable<Destination> list = packageRepository.findAll();
		
		List<DestinationDTO> dtos = new ArrayList<>();
		
		for(Destination ob : list ) {
			DestinationDTO dto = convertDestinationToDestinationDTO(ob);
			dtos.add(dto);
		}
		
		if(dtos.isEmpty())
			throw new ExploreInnException("PackageService.PACKAGE_UNAVAILABLE");
		 
		return dtos;
	}
	
	private DestinationDTO convertDestinationToDestinationDTO(Destination ob) {
		DestinationDTO dto = new DestinationDTO();
		
		dto.setAvailability(ob.getAvailability());
		dto.setChargePerPerson(ob.getChargePerPerson());
		dto.setContinent(ob.getContinent());
		dto.setDestinationId(ob.getDestinationId());
		dto.setDestinationName(ob.getDestinationName());
		
		DetailsDTO detailsDTO = new DetailsDTO();
		detailsDTO.setAbout(ob.getDetails().getAbout());
		detailsDTO.setDetailsId(ob.getDetails().getDetailsId());
		detailsDTO.setHighlights(ob.getDetails().getHighlights());
		
		ItineraryDTO itineraryDTO = new ItineraryDTO();
		itineraryDTO.setFirstDay(ob.getDetails().getItinerary().getFirstDay());
		itineraryDTO.setItineraryId(ob.getDetails().getItinerary().getItineraryId());
		itineraryDTO.setLastDay(ob.getDetails().getItinerary().getLastDay());
		itineraryDTO.setRestOfDays(ob.getDetails().getItinerary().getRestOfDays());
		
		detailsDTO.setItinerary(itineraryDTO);
		detailsDTO.setPace(ob.getDetails().getPace());
		detailsDTO.setPackageInclusion(ob.getDetails().getPackageInclusion());
		
		dto.setDetails(detailsDTO);
		dto.setDiscount(ob.getDiscount());
		dto.setFlightCharge(ob.getFlightCharge());
		dto.setImageUrl(ob.getImageUrl());
		dto.setNoOfNights(ob.getNoOfNights());
		
		return dto;
	}
	
}
