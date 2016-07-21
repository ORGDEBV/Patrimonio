/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.MenuDao;
import com.dao.impl.DaoFactory;
import com.dao.impl.MenuDaoImpl;
import com.entidad.Menu;
import com.servicio.MenuServicio;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.TreeNode;
import static com.util.Constantes.*;

/**
 *
 * @author virtual
 */
@ManagedBean
@ViewScoped
public class MenuBean implements Serializable {

    List<Menu> lMenu;
    List<List<Menu>> lCheckBox;
    private TreeNode checkMenu;
    private TreeNode[] checkMenuSeleccionado;
    private final MenuDao mDao;
    private final MenuServicio mServicio;

    public MenuBean() {
        DaoFactory factory = DaoFactory.getInstance();
        mDao = factory.getMenuDao(MENU);
        mServicio = new MenuServicio();
        Object ID_USUARIO = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("USUARIO_ID_USUARIO");
        lCheckBox = mDao.buscarIdUsuarioCheckbox(Integer.parseInt(ID_USUARIO.toString()));
        checkMenu = mServicio.checkMenu(lCheckBox);
    }

    public List<Menu> getlMenu() {
        return lMenu;
    }

    public void setlMenu(List<Menu> lMenu) {
        this.lMenu = lMenu;
    }

    public TreeNode getCheckMenu() {
        return checkMenu;
    }

    public void setCheckMenu(TreeNode checkMenu) {
        this.checkMenu = checkMenu;
    }

    public TreeNode[] getCheckMenuSeleccionado() {
        return checkMenuSeleccionado;
    }

    public void setCheckMenuSeleccionado(TreeNode[] checkMenuSeleccionado) {
        this.checkMenuSeleccionado = checkMenuSeleccionado;
    }
    
    
}
