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
                // Recipient's email ID needs to be mentioned.
                String to = recipient;

                // Sender's email ID needs to be mentioned
                String from = USERNAME;

                // Assuming you are sending email from localhost
                String host = "localhost";

                // Get system properties
                Properties properties = System.getProperties();

                // Setup mail server
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                // create session with username and password
                Session session = Session.getInstance(properties,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(USERNAME, PASSWORD);
                            }
                        });

                try{
                    // Create a default MimeMessage object.
                    MimeMessage message = new MimeMessage(session);

                    // Set From: header field of the header.
                    message.setFrom(new InternetAddress(from));

                    // Set To: header field of the header.
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                    // Set Subject: header field
                    message.setSubject(subject);

                    // Now set the actual message
                    message.setText(text);

                    // Send message
                    Transport.send(message);
                    LOGGER.info(String.format("Email send to %s", recipient));
                } catch (MessagingException e) {
                    e.printStackTrace();
                    LOGGER.warning("Could not send mail.");
                }
            }
        }).start();
    }}
