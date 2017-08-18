/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.faces.bean.RequestScoped;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author David
 */
@Named(value = "crearEmail")
@RequestScoped
public class CrearEmail implements Serializable{

    public final static String HOST_EMAIL_GMAIL = "smtp.gmail.com";
    private String emailRemitente,
            passRemitente,
            emailDestinatario,
            asunto,
            mensaje;

    private Session session;

    private MimeMessage mimeMessage;

    public CrearEmail() {
    }
    
    
    public CrearEmail(String emailRemitente, String passRemitente, String emailDestinatario) {
        this.emailRemitente = emailRemitente;
        this.passRemitente = passRemitente;
        this.emailDestinatario = emailDestinatario;

    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    

    public String getEmailRemitente() {
        return emailRemitente;
    }

    public void setEmailRemitente(String emailRemitente) {
        this.emailRemitente = emailRemitente;
    }

    public String getPassRemitente() {
        return passRemitente;
    }

    public void setPassRemitente(String passRemitente) {
        this.passRemitente = passRemitente;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public MimeMessage getMimeMessage() {
        return mimeMessage;
    }

    public void setMimeMessage(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }
    
    private void init() {
        try {
            Properties propiedades = new Properties();
            propiedades.setProperty("mail.smtp.host", HOST_EMAIL_GMAIL);// host de la cuenta
            propiedades.setProperty("mail.smtp.starttls.enable", "true");//activar el protocolo de envio de correos
            propiedades.setProperty("mail.smtp.port", "587");//puertos disponibles: 587, 25 
            propiedades.setProperty("mail.smtp.user", this.emailRemitente);//email del remitente
            propiedades.setProperty("mail.smtp.auth", "true");//si me puedo autenticar en esta aplicacion
            propiedades.setProperty("mail.smtp.ssl.trust", HOST_EMAIL_GMAIL);
            
            session = Session.getDefaultInstance(propiedades);//parametro son las configuraciones que yo le he hecho
            /*apartir de aca empieso a configurar el mensaje*/
            mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(emailRemitente));//internetAddress recibe el correo + dominio
            mimeMessage.setRecipients(Message.RecipientType.TO, emailDestinatario);

        } catch (MessagingException ex) {
            Logger.getLogger(CrearEmail.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public boolean enviarSimple(String asunto, String contenido) {
        try {
            init();
            
            setEmailRemitente("correofixedup@gmail.com");
            setPassRemitente("fixedupsena");
            
            Multipart contenidoMensaje = new MimeMultipart();
            BodyPart texto = new MimeBodyPart();
            //texto.setText(contenido);
            texto.setContent(contenido, "text/html");
            contenidoMensaje.addBodyPart(texto);

            mimeMessage.setSubject(asunto);//continuacion de las lineas 96-98, esas lineas tambien se pueden copiar aca...
            mimeMessage.setContent(contenidoMensaje);
            
            /*HACEMOS EL ENVIO DEL EMAIL*/
            
            Transport transport = session.getTransport("smtp");//recibe el protocolo y se crea despues de la sesion que hemos creado previamente
            transport.connect(emailRemitente, passRemitente);//intenta hacer la cioneccion
            
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());//aqui se envia el correo como tal y recibe el mensaje que se va a enviar + todos los destinatarios o recipientes
            transport.close();
            return true;
            
        } catch (MessagingException messagingException) {
            Logger.getLogger(CrearEmail.class.getName()).log(Level.SEVERE, null, messagingException);
            return false;
        }

    }

    public boolean enviarMasivo(String asunto, String contenido) {
        try {
            init();
            
            Multipart contenidoMensaje = new MimeMultipart();
            BodyPart texto = new MimeBodyPart();
            texto.setText(contenido);
            contenidoMensaje.addBodyPart(texto);

            mimeMessage.setSubject(asunto);//continuacion de las lineas 96-98, esas lineas tambien se pueden copiar aca...
            mimeMessage.setContent(contenidoMensaje);
            
            /*HACEMOS EL ENVIO DEL EMAIL*/
            
            Transport transport = session.getTransport("smtp");//recibe el protocolo y se crea despues de la sesion que hemos creado previamente
            transport.connect(emailRemitente, passRemitente);//intenta hacer la cioneccion
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());//aqui se envia el correo como tal y recibe el mensaje que se va a enviar + todos los destinatarios o recipientes
            transport.close();
            return true;
            
        } catch (MessagingException messagingException) {
            Logger.getLogger(CrearEmail.class.getName()).log(Level.SEVERE, null, messagingException);
            return false;
        }

    }
    


}
