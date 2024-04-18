package com.revature.controller;

import com.revature.MainDriver;
import com.revature.exceptions.UserFailException;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.service.UserService;
import com.sun.tools.javac.Main;


public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	public void authenticate(UsernamePasswordAuthentication loginRequestData) {
		// TODO: implement
		try{
			User possibleUser = userService.authenticate(loginRequestData);
			if (possibleUser.getId() != 0){
				MainDriver.loggedInUserId = possibleUser.getId();
				MainDriver.loggedInUserName= possibleUser.getUsername();
				System.out.println(String.format("Hello %s! Welcome to the Planetarium", possibleUser.getUsername()));
			} else {
				throw new UserFailException("Username/Password combo invalid, please try again");
			}

		}catch (UserFailException e){
			System.out.println(e.getMessage());
		}
	}

	public void register(User registerRequestData) {
		// TODO: implement
		try {
			User userResponse = userService.register(registerRequestData);
			if (userResponse != null && (userResponse.getId() != 0)) {
				System.out.println("Registration successful! Please login!");
			} else {
				throw new UserFailException("Registration failed: please double check your username and password and try again.");
			}
		}
		catch (UserFailException e){
			System.out.println(e.getMessage());
		}

	}

	public void logout() {
		MainDriver.loggedInUserId=0;
	}
	
	public boolean checkAuthorization(int userId) {	
		// TODO: implement
		return false;
	}
}
