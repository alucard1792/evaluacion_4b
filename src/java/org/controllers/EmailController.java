/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.dao.CorreoFacadeLocal;
import org.dao.UsuarioFacadeLocal;
import org.dto.Correo;
import org.dto.Usuario;

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

    private Correo correoSeleccionado;
    private Usuario usuarioSeleccionado;
    @EJB
    private CorreoFacadeLocal correoFacadeLocal;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;

    public EmailController() {
    }

    @PostConstruct
    public void init() {
        listaUsuarios = usuarioFacadeLocal.findAll();
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

    public Correo getCorreoSeleccionado() {
        return correoSeleccionado;
    }

    public void setCorreoSeleccionado(Correo correoSeleccionado) {
        this.correoSeleccionado = correoSeleccionado;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    private List<Usuario> listaUsuarios;

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public String enviarEmails(Usuario u) {
        try {
            /*  emailSeleccionado  = new Email("correofixedup@gmail.com", "fixedupsena", getDestinatarios());
            System.out.println("emailSeleccionado.destinatario: "+emailSeleccionado.getEmailDestinatario());
            System.out.println("emailSeleccionado.remitente: "+emailSeleccionado.getEmailRemitente());
            System.out.println("emailSeleccionado.pass: "+emailSeleccionado.getPassRemitente());
            System.out.println("asunto: "+getAsunto());
            System.out.println("contenido: "+getContenido());
            System.out.println("destinatios: "+getDestinatarios());
            System.out.println("emailSeleccionado"+getEmail());
            emailSeleccionado.enviarImagen(Asunto, contenido);
             */
            FacesContext fc = FacesContext.getCurrentInstance();

            boolean bandera = false;
            for (Usuario usuarioExiste : listaUsuarios) {
                if (usuarioExiste.getCorreo().equals(destinatarios)) {
                    bandera = true;

                }

            }
            if (bandera) {
                correoSeleccionado = new Correo();
                correoSeleccionado.setDestinos(destinatarios);
                correoSeleccionado.setContenido(contenido);
                correoSeleccionado.setUsuariosid(u);
                TestEmail email = new TestEmail("correofixedup@gmail.com", "fixedupsena", destinatarios);
                email.enviarSimple(Asunto, correoSeleccionado.getContenido());
                correoFacadeLocal.create(correoSeleccionado);
            } else {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "usuario no existe. ", "solo se permite el envio a correos registrados en el sistema");
                fc.addMessage(null, facesMessage);

            }
            /*
            TestEmail2 testEmail2 = new TestEmail2();
            testEmail2.main();
             */
 /*
            TestEmail3 testEmail3 = new TestEmail3();
            testEmail3.main();
             */
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "";
    }

}
