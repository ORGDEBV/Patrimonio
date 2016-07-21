/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.AreaDao;
import com.dao.impl.DaoFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import static com.util.Constantes.*;

@ManagedBean
@ViewScoped
public class AreaBean implements Serializable {

    private List<Object[]> lstArea = new ArrayList<>();
    private List<SelectItem> cboArea;
    private final AreaDao aDao;

    public AreaBean() {
        DaoFactory factory = DaoFactory.getInstance();
        aDao = factory.getAreaDao(AREA);
    }

    public List<SelectItem> getCboArea() {
        lstArea = aDao.listarCombo();
        cboArea = new ArrayList<>();
        if (lstArea != null) {
            for (Object[] fila : lstArea) {
                cboArea.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return cboArea;
    }

    public void setCboArea(List<SelectItem> cboArea) {
        this.cboArea = cboArea;
    }

    public List<Object[]> getLstArea() {
        return lstArea;
    }

    public void setLstArea(List<Object[]> lstArea) {
        this.lstArea = lstArea;
    }

}
