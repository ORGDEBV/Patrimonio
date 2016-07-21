/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entidad.Menu;
import java.util.List;

/**
 *
 * @author virtual
 */
public interface MenuDao {
    List<Menu> listar();
    List<Menu> buscarIdUsuario(int ID_USUARIO);
    List<List<Menu>> buscarIdUsuarioCheckbox(int ID_USUARIO);
}
