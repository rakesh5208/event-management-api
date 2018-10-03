package com.learningwithrakesh.EventManagement.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.learningwithrakesh.EventManagement.dto.EmailTemplate;
import com.learningwithrakesh.EventManagement.dto.MailProperties;

@Component
public class MailUtil {
	@Autowired
	MailProperties mailProperties;
	@Value("${mail.template.from}")
    String from;
	
    @Value("${mail.template.key}")
    String key;
	public boolean send(EmailTemplate emailTemplate) {
		boolean isSent = false;
		try {
			Session mailSession = Session.getDefaultInstance(this.mailProperties.buildJavaPropeties(),
					new GmailAuthenticator(from,key.getBytes()));
			MimeMessage mimeMessage = new MimeMessage(mailSession);
			mimeMessage.setSubject(emailTemplate.getSubject());
			mimeMessage.setContent(emailTemplate.getBody(), "text/html");
			for (String to : emailTemplate.getRecipients()) {
				mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			}
			Transport.send(mimeMessage, mimeMessage.getAllRecipients());
			isSent = true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
			isSent = false;

		} catch (Exception ex) {
			ex.printStackTrace();
			isSent = false;
		}
		return isSent;
	}

	public String getCreateMailTemplate(String fileName, Map<String, String> properties) throws Exception {
		FileReader fr = new FileReader(
				new File(MailUtil.class.getClassLoader().getResource(fileName).getPath()));
		StringBuffer sb = new StringBuffer();
		try (BufferedReader br = new BufferedReader(fr)) {
			String s = "";
			
			while ((s = br.readLine()) != null) {
				boolean isFound = false;
				for(String key : properties.keySet()){
					if(s.contains(key)){
						sb.append(s.replace(key,properties.get(key)));
						isFound = true;
						break;
					}
				}
				if (!isFound) {
					sb.append(s);
				}

			}
		}catch (Exception e) {
			throw new Exception("Error occured while reading user creation template");
		}
		return sb.toString();
	}

	class GmailAuthenticator extends Authenticator {
//		private static final String fromEmail = "uganjee520835795@gmail.com";
//		private static final String key = "rakesh_5208";
		private String fromEmail = null;
		private String key = null;
		GmailAuthenticator(final String fromEmail, final byte[] key){
			this.fromEmail = fromEmail;
			this.key = new String(Base64.decodeBase64(key));
			System.out.println(key);
		}
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(fromEmail, key);
		}
	}

	public static void main(String[] args) throws Exception {
//		 MailUtil mailUtil = new MailUtil();
//		 mailUtil.mailProperties = new MailConfig().mailProperties();
//		 EmailTemplate emailTemplate = new EmailTemplate();
//		 Map <String, String>templateBody = new HashMap<>();
//		 templateBody.put("#name", "Rakesh Kumar Jha");
//		 templateBody.put("#username", "rakeshkumar.jha");
//		 templateBody.put("#password", "rakesh01");
//		 emailTemplate.setBody(mailUtil.getCreateMailTemplate("user-creation.html",templateBody));
//		 emailTemplate.setRecipients(new String[] {
//		 "rakeshkumar.jha@icumed.com" });
//		 emailTemplate.setSubject("Welcome User");
//		 mailUtil.send(emailTemplate);
//		 System.out.println(mailUtil.getCreateMailTemplate("user-creation.html",templateBody));
		String encodeBase64 = Base64.encodeBase64String(new String("keytodecode").getBytes());
		System.out.println(encodeBase64);
		byte[] decodeBase64 = Base64.decodeBase64(encodeBase64);
		System.out.println(new String (decodeBase64));
		
		
	}
}
