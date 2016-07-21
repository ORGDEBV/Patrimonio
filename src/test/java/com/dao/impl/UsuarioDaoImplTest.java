/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao.impl;

import com.entidad.Usuario;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author virtual
 */
public class UsuarioDaoImplTest {
    
    public UsuarioDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insertar method, of class UsuarioDaoImpl.
     */
    @org.junit.Test
    public void testInsertar() {
        System.out.println("insertar");
        Usuario u = null;
        UsuarioDaoImpl instance = new UsuarioDaoImpl();
        int expResult = 0;
        int result = instance.insertar(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class UsuarioDaoImpl.
     */
    @org.junit.Test
    public void testBuscar() {
        System.out.println("buscar");
        Integer codigo = null;
        UsuarioDaoImpl instance = new UsuarioDaoImpl();
        Usuario expResult = null;
        Usuario result = instance.buscar(codigo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarUsuariosObject method, of class UsuarioDaoImpl.
     */
    @org.junit.Test
    public void testListarUsuariosObject() {
        System.out.println("listarUsuariosObject");
        UsuarioDaoImpl instance = new UsuarioDaoImpl();
        List expResult = null;
        List result = instance.listarUsuariosObject();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logIn method, of class UsuarioDaoImpl.
     */
    @org.junit.Test
    public void testLogIn() {
        System.out.println("logIn");
        Usuario u = null;
        UsuarioDaoImpl instance = new UsuarioDaoImpl();
        Usuario expResult = null;
        Usuario result = instance.logIn(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validaUsuario method, of class UsuarioDaoImpl.
     */
    @org.junit.Test
    public void testValidaUsuario() {
        System.out.println("validaUsuario");
        String usuario = "";
        UsuarioDaoImpl instance = new UsuarioDaoImpl();
        boolean expResult = false;
        boolean result = instance.validaUsuario(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
