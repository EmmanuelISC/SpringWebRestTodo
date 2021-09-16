package com.eeliasISC.basic.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//This is a controller
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class BasicAuthenticationController {
	
	@Autowired
	private MessageSource messageSource;

	
	//hello-world-bean
	@GetMapping(path="/basicauth")
	public AuthenticationBean hellWorldBean()
	{
		return new AuthenticationBean("You are authenticated");
	}
	
	
	

}
