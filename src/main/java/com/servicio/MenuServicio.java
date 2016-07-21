/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio;

import com.entidad.Menu;
import java.util.List;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author virtual
 */
public class MenuServicio {

    public TreeNode checkMenu(List<List<Menu>> lMenu) {
        TreeNode root = new CheckboxTreeNode(null, null);
        
        return root;
    }

}
