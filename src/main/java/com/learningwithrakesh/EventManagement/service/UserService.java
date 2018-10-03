package com.learningwithrakesh.EventManagement.service;

import java.util.List;

import com.learningwithrakesh.EventManagement.Exceptions.UserExistException;
import com.learningwithrakesh.EventManagement.Exceptions.UserNotFoundException;
import com.learningwithrakesh.EventManagement.entity.User;

public interface UserService {
	/**
	 * get list of all events
	 * 
	 * @return
	 */
	public List<User> getAllUsers();

	/**
	 * create an event
	 * 
	 * @param event
	 * @return
	 * @throws UserExistException 
	 */
	public User createUser(User user) throws UserExistException;

	/**
	 * Update an event
	 * 
	 * @param event
	 * @return
	 */
	public User updateUser(Long id, User user);

	/**
	 * get an event
	 * 
	 * @param id
	 * @return
	 * @throws UserNotFoundException 
	 */
	public User getUser(Long id) throws UserNotFoundException;

	public User getUserbyUsername(String username);
	/**
	 * delete an event
	 * 
	 * @param id
	 * @return
	 */
	public void deleteUser(Long id);
}
