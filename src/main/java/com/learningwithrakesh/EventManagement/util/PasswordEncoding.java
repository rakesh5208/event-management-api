package com.learningwithrakesh.EventManagement.util;

import org.apache.commons.codec.digest.Sha2Crypt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.crypto.*;

public class PasswordEncoding {
	private static final List<Character> smallAlpha = Arrays.asList(new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' });
	private static final List<Character> capsAlbha = Arrays.asList(new Character[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' });
	private static final List<Character> nums = Arrays
			.asList(new Character[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' });
	private static final List<Character> specialChars = Arrays
			.asList(new Character[] { '!', '#', '@', '$', '_'});
	
	
	public static String encode(String password) {
		return Sha2Crypt.sha256Crypt(password.getBytes());
	}
	
	public static String encodeWithExisting(String password, String originalHash) {
		return Sha2Crypt.sha256Crypt(password.getBytes(), originalHash);
	}

	public static char[] generatePassword(int len) {
		Random random = new Random();
		char[] randmPass = new char[len];
		int i = 0;
		
		
		while(i<len){
			int randomCharType  = random.nextInt(4) + 1;
			switch (randomCharType) {
			case 1:
				randmPass[i] = smallAlpha.get(random.nextInt(smallAlpha.size())) ;
				//small alphabets
				break;
			case 2:
				//caps alphabets
				randmPass[i] = capsAlbha.get(random.nextInt(capsAlbha.size()));
				break;
			case 3:
				//nums 
				randmPass[i] = nums.get(random.nextInt(nums.size()));
				break;
			case 4:
				//Special characters 
				randmPass[i] = specialChars.get(random.nextInt(specialChars.size()));
				break;
			}
			i++;
		}
		
		return randmPass;
	}

	public static void main(String[] args) {
//		char[] generatePassword = generatePassword(16);
//		System.out.println(new String(generatePassword));
		 String pass = "NotOne!1";
		 String encode = encode(pass);
//		 String encode = "$5$kxHS4G16$s81o5FmkRrBn.FxN824SxrD5nfZo2PLEKA/ZbLOeP8B";
		 System.out.println(encode);
//		 String existing = encodeWithExisting(pass,"$5$kxHS4G16$s81o5FmkRrBn.FxN824SxrD5nfZo2PLEKA/ZbLOeP8B");
//		 System.out.println(encode + existing + encode.equals(existing));

	}
}
