/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.dto.FichaEjemplarDto;

/**
 *
 * @author virtual
 */
public interface EjemplarDao {

    FichaEjemplarDto buscarFichaEjemplar(int ID_EJEMPLAR);

    int validarEjemplar(int ID_EJEMPLAR, int ID_USUARIO);
}
