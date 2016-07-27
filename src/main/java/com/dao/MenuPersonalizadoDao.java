package com.dao;

import com.entidad.MenuPersonalizado;

/**
 *
 * @author virtual
 */
public interface MenuPersonalizadoDao {

    int insert(MenuPersonalizado mp);
    int restablecerPerfil(int ID_USUARIO);

}
