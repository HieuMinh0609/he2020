package com.hieuminh.service.impl;

import com.hieuminh.constant.ConfigurationConstant;
import com.hieuminh.dto.MailDTO;
import com.hieuminh.entity.ConfigurationEntity;
import com.hieuminh.repository.ConfigurationDao;
import com.hieuminh.service.MailService;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

	private final Logger LOG = Logger.getLogger(MailServiceImpl.class);

	private String userName;
	private String password;
	private String host;
	private String port;
	private String subject;

	@Autowired
	private ConfigurationDao configurationDao;

	@Override
	public void sendEmail(MailDTO mail) {
		Map<String,Object> map = new HashMap<>();
		map.put("type",ConfigurationConstant.EMAIL_CONFIGURATION);
		List<ConfigurationEntity> configurations = (List<ConfigurationEntity>) configurationDao.findByProperty(map,null,null,null,null)[1];
		for (ConfigurationEntity item: configurations) {
			if (item.getKey().equals(ConfigurationConstant.MAIL_USERNAME)) {
				userName = item.getValue();
			} else if (item.getKey().equals(ConfigurationConstant.MAIL_PASSWORD)) {
				password = item.getValue();
			} else if (item.getKey().equals(ConfigurationConstant.MAIL_HOST)) {
				host = item.getValue();
			} else if (item.getKey().equals(ConfigurationConstant.MAIL_PORT)) {
				port = item.getValue();
			} else if (item.getKey().equals(ConfigurationConstant.SUBJECT_USER_CREATE)) {
				subject = item.getValue();
			}
 		}
		Properties properties = initProperties(host, port);
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mail.getMailFrom()));
			if (mail.getMailTo() != null && mail.getMailTo().length > 0) {
				InternetAddress[] toAddresses = new InternetAddress[mail.getMailTo().length];
				for (int i = 0; i < mail.getMailTo().length; i++) {
					toAddresses[i] = new InternetAddress(mail.getMailTo()[i]);
				}
				message.setRecipients(Message.RecipientType.TO, toAddresses);
			}
			if (mail.getMailCc() != null && mail.getMailCc().length > 0) {
				InternetAddress[] ccAddresses = new InternetAddress[mail.getMailCc().length];
				for (int i = 0; i < mail.getMailCc().length; i++) {
					ccAddresses[i] = new InternetAddress(mail.getMailCc()[i]);
				}
			}
			if (mail.getMailBcc() != null && mail.getMailBcc().length > 0) {
				InternetAddress[] bccAddresses = new InternetAddress[mail.getMailBcc().length];
				for (int i = 0; i < mail.getMailBcc().length; i++) {
					bccAddresses[i] = new InternetAddress(mail.getMailBcc()[i]);
				}
			}
			message.setSubject(subject, "UTF-8");
			message.setContent(getTemplate(mail.getTemplate(), mail), "text/html; charset=utf-8");
			Transport.send(message);
		} catch (MessagingException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private Properties initProperties(String host, String port) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.socketFactory.fallBack", "false");
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.debug", "true");
		return properties;
	}

	private String getTemplate(String template, MailDTO mail) {

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(template+" ").append("\n");


		mail.getModel().forEach((key,value) -> {
			 stringBuffer.append(" "+key+":").append(value+"\n");
		});

		 return stringBuffer.toString();
	}
}
