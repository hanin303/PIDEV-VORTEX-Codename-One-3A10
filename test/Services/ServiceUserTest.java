/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Services;

import Entity.User;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MSI
 */
public class ServiceUserTest {
    
    public ServiceUserTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getService method, of class ServiceUser.
     */
    @Test
    public void testGetService() {
        System.out.println("getService");
        ServiceUser expResult = null;
        ServiceUser result = ServiceUser.getService();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstance method, of class ServiceUser.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        ServiceUser expResult = null;
        ServiceUser result = ServiceUser.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class ServiceUser.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        int id = 0;
        ServiceUser instance = null;
        boolean expResult = false;
        boolean result = instance.deleteUser(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifUser method, of class ServiceUser.
     */
    @Test
    public void testModifUser() {
        System.out.println("modifUser");
        User u = null;
        ServiceUser instance = null;
        boolean expResult = false;
        boolean result = instance.modifUser(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class ServiceUser.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String username = "";
        String password = "";
        ServiceUser instance = null;
        User expResult = null;
        User result = instance.login(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class ServiceUser.
     */
    @Test
    public void testAddUser() {
        System.out.println("addUser");
        User u = null;
        ServiceUser instance = null;
        boolean expResult = false;
        boolean result = instance.addUser(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseUsers method, of class ServiceUser.
     */
    @Test
    public void testParseUsers() {
        System.out.println("parseUsers");
        String jsonText = "";
        ServiceUser instance = null;
        ArrayList<User> expResult = null;
        ArrayList<User> result = instance.parseUsers(jsonText);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUsers method, of class ServiceUser.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        ServiceUser instance = null;
        ArrayList<User> expResult = null;
        ArrayList<User> result = instance.getAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class ServiceUser.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        int id = 0;
        ServiceUser instance = null;
        User expResult = null;
        User result = instance.getUser(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of emailPass method, of class ServiceUser.
     */
    @Test
    public void testEmailPass() {
        System.out.println("emailPass");
        String email = "";
        ServiceUser instance = null;
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.emailPass(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of $$anonymousClasses method, of class ServiceUser.
     */
    @Test
    public void test$$anonymousClasses() {
        System.out.println("$$anonymousClasses");
        ServiceUser instance = null;
        instance.$$anonymousClasses();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
