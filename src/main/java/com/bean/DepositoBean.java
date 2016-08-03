/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.DepositoDao;
import com.dao.impl.DaoFactory;
import com.entidad.Deposito;
import static com.util.Constantes.*;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class DepositoBean {
    
    List<Deposito> ldeposito;
    DepositoDao dDao;

    public DepositoBean() {
        DaoFactory factory = DaoFactory.getInstance();
        dDao = factory.getDepositoDao(CAJA);
    }

    public List<Deposito> getLdeposito() {
        ldeposito = dDao.listarDeposito();
        return ldeposito;
    }

    public void setLdeposito(List<Deposito> ldeposito) {
        this.ldeposito = ldeposito;
    }
    
}
