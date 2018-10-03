package com.learningwithrakesh.EventManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.learningwithrakesh.EventManagement.dto.ErrorResponse;
import com.learningwithrakesh.EventManagement.dto.ErrorType;
import com.learningwithrakesh.EventManagement.entity.LoginInfo;
import com.learningwithrakesh.EventManagement.entity.User;
import com.learningwithrakesh.EventManagement.service.UserService;
import com.learningwithrakesh.EventManagement.util.JwtHelper;
import com.learningwithrakesh.EventManagement.util.PasswordEncoding;

@CrossOrigin
@Controller
@RequestMapping("/auth")
public class LoginContoller {

	@Autowired
	UserService userService;

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> authincateUser(@RequestBody LoginInfo loginInfo) {
		User user = this.userService.getUserbyUsername(loginInfo.getUsername());
		ErrorResponse er = ErrorResponse.GetInstance().withMessage("username or password is incorrect")
				.withStatus(HttpStatus.BAD_REQUEST.value());
		if (user != null) {
			if (!user.isEnabled()) {

				er = er.withMessage("Your Account has been disabled!!. Please Contact adminstrator")
						.withType(ErrorType.ACCOUNT_DISABLED);
				return new ResponseEntity<ErrorResponse>(er, HttpStatus.UNAUTHORIZED);
			}
			if (user.isLocked()) {
				er = er.withMessage("Your Account has been Locked!!. Please Contact adminstrator")
						.withType(ErrorType.ACCOUNT_LOCKED);
				return new ResponseEntity<ErrorResponse>(er, HttpStatus.UNAUTHORIZED);
			}

			String encoded = PasswordEncoding.encodeWithExisting(loginInfo.getPassword(), user.getPassword());
			if (user.getPassword().equals(encoded)) {
				return new ResponseEntity<String>(JwtHelper.createToken(user), HttpStatus.OK);
			} else {
				return new ResponseEntity<ErrorResponse>(er, HttpStatus.UNAUTHORIZED);
			}

		} else {
			return new ResponseEntity<ErrorResponse>(er, HttpStatus.UNAUTHORIZED);
		}
	}
}
