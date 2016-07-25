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
