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
    public String tareasPorUsuario(String usuario);
}
