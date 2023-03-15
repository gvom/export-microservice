package dev.export.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.export.models.User;
import dev.export.services.UserService;

public class UserController {

	private UserService userService =  new UserService();

	@ResponseBody
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody MultiValueMap<String,String> data)
    {
		User user = new User(data.getFirst("name"), data.getFirst("email"), Long.parseLong(data.getFirst("documentNumber")));
		userService.add(user);
		return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@ResponseBody
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseEntity<?> deleteUser(@RequestBody MultiValueMap<String,String> data)
    {
		if(data.containsKey("id")) {
			if(userService.exists(data.getFirst("id"))) {
				userService.deleteById(data.getFirst("id"));
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@ResponseBody
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody MultiValueMap<String,String> data)
    {
		if(data.containsKey("id")) {
			if(userService.exists(data.getFirst("id"))) {
				User user = userService.findById(data.getFirst("id"));
				
				if(data.containsKey("email"))
					user.setEmail(data.getFirst("email"));
				
				if(data.containsKey("documentNumber"))
					user.setDocumentNumber(Long.parseLong(data.getFirst("documentNumber")));
				
				if(data.containsKey("name"))
					user.setName(data.getFirst("name"));
				
				userService.update(user);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@ResponseBody
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public ResponseEntity<?> getUser(@RequestBody MultiValueMap<String,String> data)
    {
		if(data.containsKey("id")) {
			String id = data.getFirst("id");
			if(userService.exists(id)) {
				return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@ResponseBody
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.POST)
    public ResponseEntity<?> getAllUsers()
    {
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
    }
}
