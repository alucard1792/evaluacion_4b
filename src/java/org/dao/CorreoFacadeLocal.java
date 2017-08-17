/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dao;

import java.util.List;
import javax.ejb.Local;
import org.dto.Correo;

/**
 *
 * @author David
 */
@Local
public interface CorreoFacadeLocal {

    void create(Correo correo);

    void edit(Correo correo);

    void remove(Correo correo);

    Correo find(Object id);

    List<Correo> findAll();

    List<Correo> findRange(int[] range);

    int count();
    
}
