/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author David
 */
@Named(value = "crearEmail2")
@ViewScoped
public class CrearEmail2 implements Serializable {
    public final static String HOST_EMAIL_GMAIL = "smtp.gmail.com";

    private final Properties properties = new Properties();

    private String password;

    private Session session;

    private void init() {

        properties.put("mail.smtp.host", "mail.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.mail.sender", "correofixedup@gmail.com");
        properties.put("mail.smtp.user", "correofixedup@gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "*");//si me puedo autenticar en esta aplicacion
            properties.put("mail.smtp.ssl.trust", HOST_EMAIL_GMAIL);

        session = Session.getDefaultInstance(properties);
    }

    public void sendEmail() {
        init();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress((String) properties.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("edzamora1@misena.edu.co"));
            message.setSubject("Prueba");
            message.setText("Texto");
            System.out.println("1111");
            Transport t = session.getTransport("smtp");
            t.connect("correofixedup@gmail.com", "fixedupsena");//intenta hacer la cioneccion
            //t.connect((String) properties.get("mail.smtp.user"), "fixedupsena");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            System.out.println("2222");
        } catch (MessagingException me) {
            //Aqui se deberia o mostrar un mensaje de error o en lugar
            //de no hacer nada con la excepcion, lanzarla para que el modulo
            //superior la capture y avise al usuario con un popup, por ejemplo.
            me.printStackTrace();
        }

    }
}
