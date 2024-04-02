package com.exploreinn.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exploreinn.dto.DestinationDTO;
import com.exploreinn.exception.ExploreInnException;
import com.exploreinn.service.PackageService;

@CrossOrigin
@RestController
@RequestMapping(value="package-api")
public class PackageAPI {
	
	@Autowired
	private PackageService packageService; 
	
	@GetMapping(value="/packages/{continent}")
	public ResponseEntity<List<DestinationDTO>> getPackagesSearch(@PathVariable String continent) throws ExploreInnException{
		return new ResponseEntity<>(packageService.getPackagesSearch(continent),HttpStatus.OK);
	}

	@GetMapping(value="/itinerary/{destinationId}")
	public ResponseEntity<DestinationDTO> getItinerary(@PathVariable String destinationId) throws ExploreInnException{	
			return new ResponseEntity<>(packageService.getItinerary(destinationId),HttpStatus.OK);
	}
	
	@GetMapping(value="/getPackages")
	public ResponseEntity<List<DestinationDTO>> getPackages() throws ExploreInnException{
		return new ResponseEntity<>(packageService.getPackages(),HttpStatus.OK);
	}
}
