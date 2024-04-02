package com.exploreinn.service;

import java.util.List;

import com.exploreinn.dto.DestinationDTO;
import com.exploreinn.exception.ExploreInnException;

public interface PackageService {
	
	public List<DestinationDTO> getPackagesSearch(String continent) throws ExploreInnException;
	public DestinationDTO getItinerary(String destinationId) throws ExploreInnException;
	public List<DestinationDTO> getPackages() throws ExploreInnException;

}
