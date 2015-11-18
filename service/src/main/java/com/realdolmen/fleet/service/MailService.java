package com.realdolmen.fleet.service;

import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Created on 3/11/2015.
 *
 * @author Robin D'Haese
 */
@Service
public class MailService {
    private static final Logger LOGGER = Logger.getLogger(MailService.class.toString());
    private static final String USERNAME = "fleettest.realdolmen@gmail.com";
    private static final String PASSWORD = "fleettest";

    public void sendMail(String recipient, String subject, String text){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String to = recipient;
                String from = USERNAME;

                Properties properties = System.getProperties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                Session session = Session.getInstance(properties,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(USERNAME, PASSWORD);
                            }
                        });

                try{
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(from));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                    message.setSubject(subject);
                    message.setText(text);

                    Transport.send(message);
                    LOGGER.info(String.format("Email send to %s", recipient));
                } catch (MessagingException e) {
                    LOGGER.warning(String.format("Could not send mail to %s.", recipient));
                }
            }
        }).start();
    }}
