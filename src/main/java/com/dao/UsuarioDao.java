/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.entidad.Usuario;
import java.util.List;

/**
 *
 * @author virtual
 */
public interface UsuarioDao {
    int insertar(Usuario u);
    List<Object[]> listarUsuariosObject();
    Usuario buscar(Integer codigo);
    Usuario logIn(Usuario u);
    boolean validaUsuario(String usuario);
}
