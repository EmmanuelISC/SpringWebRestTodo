package com.eeliasISC.restfulwebservices.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eeliasISC.restfulwebservices.todo.Todo;


@CrossOrigin(origins="http://localhost:4200")
@RestController
public class TodoJpaResource {

	@Autowired
	private TodoHarcodedService todoService;
	
	@Autowired
	private TodoJpaRepository todoJpaRepository;
	
	// GET http://localhost:8080/users/{user_name}/todos
	@GetMapping("/jpa/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){
		
		return todoJpaRepository.findByUsername(username);
		//return todoService.findAll();
		
	}
	
	// GET http://localhost:8080/users/{user_name}/todos/{id}
		@GetMapping("/jpa/users/{username}/todos/{id}")
		public Todo getTodo(@PathVariable String username, @PathVariable long id){
			return todoJpaRepository.findById(id).get();
			//return todoService.findById(id);
			
		}
	
	//DELETE http://localhost:8080/users/{user_name}/todos/{id}
	@DeleteMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,@PathVariable Long id){
		
		//Todo todo = todoService.deleteById(id);
		
		todoJpaRepository.deleteById(id);
		
		return ResponseEntity.notFound().build();
	} 
	
	//Update put methos rest verb from backend side to can be cosume by front-end side
	@PutMapping("/jpa/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username, @PathVariable long id, @RequestBody Todo todo){
		
		//Todo todoUpdated = todoService.save(todo);
		
		Todo todoUpdated = todoJpaRepository.save(todo);
		
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/users/{username}/todos")
	public ResponseEntity<Void> saveTodo(@PathVariable String username, @RequestBody Todo todo){
		
		todo.setUsername(username);
		//Todo createdTodo = todoService.save(todo);
		Todo createdTodo = todoJpaRepository.save(todo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(createdTodo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
