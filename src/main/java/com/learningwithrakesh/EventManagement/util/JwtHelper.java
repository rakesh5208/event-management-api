package com.learningwithrakesh.EventManagement.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.learningwithrakesh.EventManagement.entity.User;

@Component
public class JwtHelper {
	
	
	
	private static  Algorithm hmac256 = Algorithm.HMAC256("NotOne!1");
	private static final String issuer = "org.learningwithrakesh.com";
	private static final int tokenExpiresInMin = 30;
	
	public JwtHelper() {
	}

	public static String createToken(User user) {
		long creationTime = System.currentTimeMillis();
		long expireTime = creationTime + tokenExpiresInMin * 60 * 1000;
		String token = createUserClaims(JWT.create().withIssuer(issuer), user).withIssuedAt(new Date(creationTime))
				.withExpiresAt(new Date(expireTime)).sign(hmac256);
		return token;
	}

	private static Builder createUserClaims(Builder creator, User user) {
		Builder claims = creator.withClaim("username", user.getUsername())
				.withArrayClaim("roles", new String[] { "Biomed", "IT", "DLE Manger" })
				.withClaim("name", user.getName()).withClaim("email", user.getEmail());

		return claims;
	}

	public static boolean verfyToken(String token) throws JWTDecodeException, AlgorithmMismatchException,
			SignatureVerificationException, TokenExpiredException, InvalidClaimException {
		DecodedJWT verify = null;
		DecodedJWT decodedJWT = JWT.decode(token);
		String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
		String username = decodedJWT.getClaim("username").asString();
		String name = decodedJWT.getClaim("name").asString();
		String email = decodedJWT.getClaim("email").asString();

		JWTVerifier jwtVerifier = JWT.require(hmac256).withIssuer(issuer).withClaim("username", username)
				.withArrayClaim("roles", roles).withClaim("name", name).withClaim("email", email).build();
		verify = jwtVerifier.verify(token);

		return (verify != null);
	}
	private static DecodedJWT decodeToken(String token){
		return JWT.decode(token);
	}
	public static void main(String[] args) {
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJCaW9tZWQiLCJJVCIsIkRMRSBNYW5nZXIiXSwiaXNzIjoib3JnLmxlYXJuaW5nd2l0aHJha2VzaC5jb20iLCJuYW1lIjoiQWRtaW5pc3RyYW9yIiwiZXhwIjoxNTM4MDY0NTgwLCJpYXQiOjE1MzgwNjQyODAsImVtYWlsIjoiYWRtaW5Abm8tcmVwbHkuY29tIiwidXNlcm5hbWUiOiJhZG1pbiJ9.S0OQbh7CUP1Uez24TZdBGlp_E8bpRGJxAR6VRO85t9A";
		System.out.println(verfyToken(token));
	}

}
