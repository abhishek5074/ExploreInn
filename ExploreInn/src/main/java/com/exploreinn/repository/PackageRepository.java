package com.exploreinn.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.exploreinn.entity.Destination;

public interface PackageRepository extends CrudRepository<Destination, String> {
	
	List<Destination> findAllByContinent(String continent);

}
