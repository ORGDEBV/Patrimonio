/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.PerfilDao;
import com.dao.impl.DaoFactory;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import static com.util.Constantes.*;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class PerfilBean {

    private final PerfilDao pDao;
    private List<Object[]> lstPerfilCombo = new ArrayList<>();
    private List<SelectItem> cboPerfil;

    public PerfilBean() {
        DaoFactory factory = DaoFactory.getInstance();
        pDao = factory.getPerfilDao(PERFIL);
    }

    public List<SelectItem> getCboPerfil() {
        lstPerfilCombo = pDao.listarPerfilCombo();
        cboPerfil = new ArrayList<>();
        if (lstPerfilCombo != null) {
            for (Object[] fila : lstPerfilCombo) {
                cboPerfil.add(new SelectItem(fila[0], fila[1].toString()));
            }
        }
        return cboPerfil;
    }

    public void setCboPerfil(List<SelectItem> cboPerfil) {
        this.cboPerfil = cboPerfil;
    }

}
