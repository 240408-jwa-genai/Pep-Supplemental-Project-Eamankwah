package com.revature.service;

import com.revature.exceptions.UserFailException;
import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.repository.UserDao;

public class UserService {

	private UserDao dao;

	public UserService(UserDao dao){
		this.dao = dao;
	}

	public User authenticate(UsernamePasswordAuthentication loginRequestData) {
		// TODO: implement
		try {
			User possibleUser = dao.getUserByUsername(loginRequestData.getUsername());
			if (possibleUser != null) {
				// the commented line is redundant
//			boolean usernamesMatch = loginRequestData.getUsername().equals(possibleUser.getUsername());
				boolean passwordsMatch = loginRequestData.getPassword().equals(possibleUser.getPassword());
				if (passwordsMatch) {
					return possibleUser;
				}
				else{
					throw new UserFailException("\nLogin failed, verify credentials");
				}
			}
		}catch (UserFailException e){
			System.out.println(e.getMessage());
		}
		return new User();
	}

	public User register(User registerRequestData) {
		// TODO: implement
		try {
			if (registerRequestData.getPassword().length() < 30 && registerRequestData.getUsername().length() < 30) {
				User unknownUser = dao.getUserByUsername(registerRequestData.getUsername());
				if (unknownUser.getId() != 0) {
					throw new UserFailException("A user with chosen username exists!");
				} else if (!(registerRequestData.getUsername().equals(unknownUser.getUsername()))) {
					UsernamePasswordAuthentication newUser = new UsernamePasswordAuthentication();
					newUser.setUsername(registerRequestData.getUsername());
					newUser.setPassword(registerRequestData.getPassword());

					return dao.createUser(newUser);
				}
			} //if
			else
			{

				throw new UserFailException("Either your username or password exceeds 30 characters");
			}//else
		}catch(UserFailException e){
			System.out.println(e.getMessage());
		}
		return null;
	}
}
