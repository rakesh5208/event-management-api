package com.learningwithrakesh.EventManagement.repository;

import com.learningwithrakesh.EventManagement.entity.User;

public interface UserRepository  extends CurdRepository<User>{
	public User findByUsername(String username);
}
