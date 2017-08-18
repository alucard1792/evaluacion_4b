/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author David
 */
@Named(value = "emailController")
@SessionScoped
public class EmailController implements Serializable {

    private String Asunto,
            contenido,
            destinatarios;
    private Email email;

    public EmailController() {
    }

    public String getAsunto() {
        return Asunto;
    }

    public void setAsunto(String Asunto) {
        this.Asunto = Asunto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String enviarEmails() {
        try {
            /*  email  = new Email("correofixedup@gmail.com", "fixedupsena", getDestinatarios());
            System.out.println("email.destinatario: "+email.getEmailDestinatario());
            System.out.println("email.remitente: "+email.getEmailRemitente());
            System.out.println("email.pass: "+email.getPassRemitente());
            System.out.println("asunto: "+getAsunto());
            System.out.println("contenido: "+getContenido());
            System.out.println("destinatios: "+getDestinatarios());
            System.out.println("email"+getEmail());
            email.enviarImagen(Asunto, contenido);
             */
            /*
          TestEmail email = new TestEmail();
          email.main();
             */
            /*
            TestEmail2 testEmail2 = new TestEmail2();
            testEmail2.main();
            */
            TestEmail3 testEmail3 = new TestEmail3();
            testEmail3.main();
            
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "";
    }

}
