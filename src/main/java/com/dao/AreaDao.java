package com.dao;

import com.entidad.Area;
import java.util.List;

/**
 *
 * @author virtual
 */
public interface AreaDao {
    List<Area> listar();
    List<Object[]> listarCombo();
    Area buscar(Integer codigo);
}
