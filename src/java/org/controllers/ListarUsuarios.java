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
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import org.dao.UsuarioFacadeLocal;
import org.dto.Usuario;

/**
 *
 * @author David
 */
@Named(value = "listarUsuarios")
@ConversationScoped
public class ListarUsuarios implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @Inject
    private Conversation conversacion;
    private List<Usuario> listaUsuarios;
    
    public ListarUsuarios() {
    }
    
    @PostConstruct
    public void init(){
        listaUsuarios = usuarioFacadeLocal.findAll();
        
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    
}
