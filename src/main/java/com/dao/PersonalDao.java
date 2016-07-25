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
