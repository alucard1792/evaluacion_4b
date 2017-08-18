/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.controllers;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.dao.CorreoFacadeLocal;
import org.dto.Correo;

/**
 *
 * @author David
 */
@Named(value = "listarEmail")
@ConversationScoped
public class ListarEmail implements Serializable {

    @EJB
    private CorreoFacadeLocal correoFacadeLocal;
    
    private List<Correo> listaCorreo;
    
    public ListarEmail() {
    }
    
    @PostConstruct
    public void init(){
        listaCorreo = correoFacadeLocal.findAll();
        
    }

    public List<Correo> getListaCorreo() {
        return listaCorreo;
    }
    
    
    
}
