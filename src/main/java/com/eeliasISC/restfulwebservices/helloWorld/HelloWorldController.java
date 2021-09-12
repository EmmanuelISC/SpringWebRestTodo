package com.eeliasISC.restfulwebservices.helloWorld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//This is a controller
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class HelloWorldController {
	
	//GET
	//URI /hello-world
	//Method
	@GetMapping(path="hello-world")
	public String hellWorld()
	{
		return "Hello Swisslog Team";
	}
	
	//hello-world-bean
	@GetMapping(path="hello-world-bean")
	public HelloWorldBean hellWorldBean()
	{
		return new HelloWorldBean("Hello SwissLog Team");
	}
	
	//Creating a map request with a parameter
	//http://localhost:8080/hello-world/EElias/Morales/7221234567
		@GetMapping(path="hello-world/{name}/{lastName}/{phone}")
		public HelloWorldBean hellWorldPathVariable(@PathVariable String name,
				@PathVariable String lastName, @PathVariable long phone)
		{
			//%s let us to map the strings parameters
			if(name != null && lastName != null && phone != 0)
			{
				return new HelloWorldBean(String.format("Hello world:%s %s %d", name, lastName, phone));	
			}
			//quick test to handle error
			else
			{
				throw new RuntimeException("Some error has happened with the request");	
			}
			
		}
	

}
