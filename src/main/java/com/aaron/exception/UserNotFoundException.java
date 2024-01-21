package com.aaron.exception;

public class UserNotFoundException extends RuntimeException{
	
	public UserNotFoundException(int id_user) {
		// TODO Auto-generated constructor stub
		super("Could not found ther user with id " + id_user);
	}
}
