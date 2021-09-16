package com.eeliasISC.restfulwebservices.jwt.resource;
public class AuthenticationException extends RuntimeException {
	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}