package com.exploreinn.service;

import com.exploreinn.dto.UserDTO;
import com.exploreinn.exception.ExploreInnException;

public interface UserService {

	public UserDTO authenticateUser(String contactNumber, String password) throws ExploreInnException;

	public Integer registerUser(UserDTO user) throws ExploreInnException;
	
	
	
}
