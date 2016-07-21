/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entidad.Personal;
import java.util.List;

/**
 *
 * @author virtual
 */
public interface PersonalDao {
    int insertar(Personal p);
    int actualizar(Personal p);
    List<Personal> listar();
    Personal buscar(Integer codigo);
    List<Object[]> listarCombo();
}
