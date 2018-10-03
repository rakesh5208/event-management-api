package com.learningwithrakesh.EventManagement.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.Sha2Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningwithrakesh.EventManagement.Exceptions.UserExistException;
import com.learningwithrakesh.EventManagement.Exceptions.UserNotFoundException;
import com.learningwithrakesh.EventManagement.dto.EmailTemplate;
import com.learningwithrakesh.EventManagement.entity.User;
import com.learningwithrakesh.EventManagement.repository.UserRepository;
import com.learningwithrakesh.EventManagement.util.MailUtil;
import com.learningwithrakesh.EventManagement.util.PasswordEncoding;

@Service()
public class UserServiceImpl implements UserService {
	private static final int passwordLen = 8;
	@Autowired()
	UserRepository userRepo;
	@Autowired
	MailUtil mailUtil;

	@Override
	public List<User> getAllUsers() {

		return this.userRepo.findAll();
	}

	@Override
	public User createUser(User user) throws UserExistException {
		User createdUser = null;
		if (this.getUserbyUsername(user.getUsername()) != null)
			throw new UserExistException("Username is alreday exist!!");
		Long currentTimeInMillsec = System.currentTimeMillis();
		user.setWhenCreated(currentTimeInMillsec);
		user.setWhenLastUpdated(currentTimeInMillsec);
		user.setEnabled(true);
		user.setExpired(false);
		user.setLocked(false);
		user.setHasToResetPassword(true);
		char[] generatePassword = PasswordEncoding.generatePassword(passwordLen);
		user.setPassword(PasswordEncoding.encode(new String(generatePassword)));
		try {
			createdUser = this.userRepo.save(user);
			// sending mail
			if (this.sendMail(createdUser, generatePassword)) {
				System.out.println("Mail Has been sent");
			} else {
				System.out.println("Mail Could not be sent !!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Mail Could not be sent !!");
		}
		Arrays.fill(generatePassword, '\0');
		return createdUser;
	}

	@Override
	public User updateUser(Long id, User user) {
		return this.userRepo.save(user);
	}

	@Override
	public User getUser(Long id) throws UserNotFoundException {
		User user = this.userRepo.getOne(id);
		if(user == null){
			throw new UserNotFoundException("Could not find the user with id:"+id);
		}
		return user;
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUserbyUsername(String username) {
		// TODO Auto-generated method stub
		return this.userRepo.findByUsername(username);
	}

	private boolean sendMail(User user, char[] password) throws Exception {
		System.out.println("password" + new String(password));
		EmailTemplate emailTemplate = new EmailTemplate();
		
		Map<String, String> templateBody = new HashMap<>();
		templateBody.put("#name", user.getName());
		templateBody.put("#username", user.getUsername());
		templateBody.put("#password", new String(password));
		emailTemplate.setBody(this.mailUtil.getCreateMailTemplate("user-creation.html", templateBody));
		
		emailTemplate.setRecipients(new String[] { user.getEmail() });
		emailTemplate.setSubject("Welcome User:" + user.getName());
		return this.mailUtil.send(emailTemplate);
	}
}
