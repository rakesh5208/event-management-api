package com.learningwithrakesh.EventManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learningwithrakesh.EventManagement.Exceptions.UserExistException;
import com.learningwithrakesh.EventManagement.Exceptions.UserNotFoundException;
import com.learningwithrakesh.EventManagement.dto.ErrorResponse;
import com.learningwithrakesh.EventManagement.entity.User;
import com.learningwithrakesh.EventManagement.service.UserService;

@CrossOrigin
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		ErrorResponse er = ErrorResponse.GetInstance().withStatus(HttpStatus.BAD_REQUEST.value());
		try {
			return new ResponseEntity<User>(this.userService.createUser(user), HttpStatus.OK);
		} catch (UserExistException ex) {
			er.withMessage(ex.getMessage());
			return new ResponseEntity<ErrorResponse>(er, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	@ResponseBody
	public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
		ErrorResponse er = ErrorResponse.GetInstance().withStatus(HttpStatus.BAD_REQUEST.value());
		try {
			return new ResponseEntity<User>(this.userService.getUser(id), HttpStatus.OK);
		} catch (UserNotFoundException ex) {
			er.withMessage(ex.getMessage());
			return new ResponseEntity<ErrorResponse>(er, HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}/change-password")
	@ResponseBody
	public ResponseEntity<?> changePassword(@PathVariable("id") Long id) {
		
		ErrorResponse er = ErrorResponse.GetInstance().withStatus(HttpStatus.BAD_REQUEST.value()).withMessage("hitting the change password");
		er.withType(null);
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.BAD_REQUEST);
	}
}
