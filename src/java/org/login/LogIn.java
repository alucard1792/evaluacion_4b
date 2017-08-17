/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.login;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.dao.UsuarioFacadeLocal;
import org.dto.Usuario;

/**
 *
 * @author David
 */
@Named(value = "logIn")
@SessionScoped
public class LogIn implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private int documento;
    private String password;
    private int rol;
    private Usuario usuario;

    public LogIn() {
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String iniciarSesion() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if ((documento != 0) && password != null && !password.equals("")) {
            usuario = usuarioFacadeLocal.validarUsuario(getDocumento(), getPassword());
            
            if (usuario != null && usuario.getEstado() == 1) {
                System.out.println("entroooo");
                return "listarUsuarios.xhtml?faces-redirect=true";

            } else {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "contraseña, documento incorrecto o usuario desactivado. ", "Contacte con el administrador");
                fc.addMessage(null, facesMessage);
                System.out.println("erroneooo");

            }

        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Todos los campos son obligatorios", "Diligencie todos los campos");
            fc.addMessage(null, facesMessage);
                System.out.println("datos incorectooosss");

        }
        return "";

    }

    public void cerrarSesion() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.invalidateSession();
        setDocumento(0);
        setPassword("");
        setUsuario(null);
        setRol(0);

        try {
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml?faces-redirect=true");

        } catch (IOException e) {
            Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, e);

        }

    }

    public void validarSesion() {
        if (!isValidado()) {
            cerrarSesion();

        }

    }

    public boolean isValidado() {
        if (usuario != null) {
            return true;

        }
        return false;

    }

}
