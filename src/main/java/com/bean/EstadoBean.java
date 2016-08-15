/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.EstadoDao;
import com.dao.impl.DaoFactory;
import com.entidad.EstadoProceso;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import static com.util.Constantes.*;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class EstadoBean {

    List<EstadoProceso> lestado;
    EstadoDao estadoDao;

    public EstadoBean() {
        DaoFactory factory = DaoFactory.getInstance();
        estadoDao = factory.getEstadoDao(ESTADO);
    }

    public List<EstadoProceso> getLestado() {
        lestado = estadoDao.listarEstado();
        return lestado;
    }

    public void setLestado(List<EstadoProceso> lestado) {
        this.lestado = lestado;
    }

}
