/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

/**
 *
 * @author David
 */
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestEmail {


        public void main() throws AddressException, MessagingException {

            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
// Get a Properties object
            Properties props = System.getProperties();
            props.setProperty("proxySet", "true");
            props.setProperty("socksProxyHost", "192.168.155.1");
            props.setProperty("socksProxyPort", "1080");
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.store.protocol", "pop3");
            props.put("mail.transport.protocol", "smtp");
            final String username = "correofixedup@gmail.com";
            final String password = "fixedupsena";
            Session session = Session.getDefaultInstance(props);

// -- Create a new message --
            Message msg = new MimeMessage(session);

// -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress("correofixedup@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("correofixedup@gmail.com", false));
            msg.setSubject("Hello");
            msg.setText("How are you");
            msg.setSentDate(new Date());
            
            Transport transport = session.getTransport("smtp");//recibe el protocolo y se crea despues de la sesion que hemos creado previamente
            transport.connect(username, password);//intenta hacer la cioneccion
            transport.sendMessage(msg, msg.getAllRecipients());//aqui se envia el correo como tal y recibe el mensaje que se va a enviar + todos los destinatarios o recipientes
            transport.close();
            System.out.println("Message sent.");
        }
    

}
