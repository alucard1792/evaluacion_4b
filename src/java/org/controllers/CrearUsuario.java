/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import java.io.Serializable;
import java.util.Random;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.dao.UsuarioFacadeLocal;
import org.dto.Usuario;

/**
 *
 * @author David
 */
@Named(value = "crearUsuario")
@ViewScoped
public class CrearUsuario implements Serializable{

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    private Usuario usuario;
    
    private int id, documento, telefono, estado, rol;
    private String nombre, correo, password;
    
    public CrearUsuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String crear(){
        Random random = new Random();
        password = String.valueOf(999999 + random.nextInt(99999999));
        usuario = new Usuario(id, nombre, documento, telefono, estado, correo, password, rol);
        usuarioFacadeLocal.create(usuario);
        return "listarUsuarios.xhtml?faces-redirect=true";
    }
    
}
