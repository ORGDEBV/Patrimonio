
package com.dao;

import com.entidad.Menu;
import java.util.List;

/**
 *
 * @author virtual
 */
public interface MenuDao {
    List<Menu> buscarIdUsuario(int ID_USUARIO);
    List<Menu> buscarMenuPadre(int ID_USUARIO);
    List<Menu> buscarMenuHijo(int ID_USUARIO, int ID_MENU_PADRE);
}
