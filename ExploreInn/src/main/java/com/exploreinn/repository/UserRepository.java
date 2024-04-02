package com.exploreinn.repository;

import org.springframework.data.repository.CrudRepository;

import com.exploreinn.entity.User;
public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByContactNumber(String contactNumber);

}