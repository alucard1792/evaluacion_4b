/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.dto.Usuario;

/**
 *
 * @author David
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "evaluacion_4bPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario validarUsuario(int documento, String password) {
        System.out.println("askljdsakldjklsadkslakld");
        Usuario usuario = null;
        FacesMessage fm;
        FacesContext fc;
        System.out.println("validar usuariooo");
        System.out.println(documento);
        System.out.println(password);
        try {
            TypedQuery<Usuario> q = getEntityManager().createNamedQuery("Usuario.login", Usuario.class);
            q.setParameter("documento", documento);
            q.setParameter("password", password);
            usuario = q.getSingleResult();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return usuario;
        
    }
    
}
