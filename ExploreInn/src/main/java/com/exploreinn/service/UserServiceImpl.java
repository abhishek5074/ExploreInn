package com.exploreinn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exploreinn.dto.UserDTO;
import com.exploreinn.entity.User;
import com.exploreinn.exception.ExploreInnException;
import com.exploreinn.repository.UserRepository;

import jakarta.transaction.Transactional;


@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;


	@Override
	public UserDTO authenticateUser(String contactNumber, String password) throws ExploreInnException {

		User optionalUser = userRepository.findByContactNumber(contactNumber);
		if (optionalUser == null) {
			throw new ExploreInnException("UserService.INVALID_CREDENTIALS");
		}

		String passwordFromDB = optionalUser.getPassword();

		if (passwordFromDB != null) {
				if (password.equals(passwordFromDB)) {
					UserDTO userObject = new UserDTO();
					userObject.setContactNumber(optionalUser.getContactNumber());
					userObject.setEmailId(optionalUser.getEmailId());
					userObject.setUserId(optionalUser.getUserId());
					userObject.setUserName(optionalUser.getUserName());
					return userObject;
				} else
					throw new ExploreInnException("UserService.INVALID_CREDENTIALS");
				}
		else {
			throw new ExploreInnException("UserService.INVALID_CREDENTIALS");
		}

	}

	@Override
	public Integer registerUser(UserDTO user) throws ExploreInnException {
		
		
		if(user.getEmailId().isEmpty() || user.getContactNumber().isEmpty() || user.getPassword().isEmpty() || user.getUserName().isEmpty())
			throw new ExploreInnException("UserService.INVALID_CREDENTIALS");
		
		User userOp = userRepository.findByContactNumber(user.getContactNumber());
		if(userOp != null) {
			throw new ExploreInnException("UserService.CONTACT_NUMBER_ALREADY_EXISTS");
		}
			
		
		User userEntity = new User();
		userEntity.setContactNumber(user.getContactNumber());
		userEntity.setEmailId(user.getEmailId());
		userEntity.setPassword(user.getPassword());
		userEntity.setUserName(user.getUserName());
		User saveUser = userRepository.save(userEntity);
		
		return saveUser.getUserId();
	}

}
