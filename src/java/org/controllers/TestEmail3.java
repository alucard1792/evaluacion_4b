/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author David
 */
public class TestEmail3 {

    public void main() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("correofixedup@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("edzamora1@misena.edu.co"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport transport = session.getTransport("smtp");//recibe el protocolo y se crea despues de la sesion que hemos creado previamente
            transport.connect("correofixedup@gmail.com", "fixedupsena");//intenta hacer la cioneccion
            transport.sendMessage(message, message.getAllRecipients());//aqui se envia el correo como tal y recibe el mensaje que se va a enviar + todos los destinatarios o recipientes
            transport.close();

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
