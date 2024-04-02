package com.exploreinn.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exploreinn.dto.UserDTO;
import com.exploreinn.exception.ExploreInnException;
import com.exploreinn.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("user-api")
public class UserAPI {

	@Autowired
	private UserService userService;
	
	@Autowired
	Environment environment;

	@PostMapping(value = "/userLogin")
	public ResponseEntity<UserDTO> authenticateUser(@RequestBody UserDTO user) throws ExploreInnException {
		UserDTO userFromDB = userService.authenticateUser(user.getContactNumber(), user.getPassword());
		return new ResponseEntity<UserDTO>(userFromDB, HttpStatus.OK);
	}

	@PostMapping(value = "/userRegister")
	public ResponseEntity<String> registerUser(@RequestBody UserDTO user) throws ExploreInnException {		
		Integer userId = userService.registerUser(user);
		String str = environment.getProperty("UserAPI.REGISTER_USER_SUCCESS1") + userId + environment.getProperty("UserAPI.REGISTER_USER_SUCCESS2");
		return new ResponseEntity<>(str , HttpStatus.CREATED);
	}

}
