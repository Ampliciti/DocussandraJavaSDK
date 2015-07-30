/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.docussandra.javasdk.dao.impl;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.domain.DatabaseResponse;
import com.strategicgains.docussandra.domain.objects.Database;
import com.strategicgains.docussandra.domain.objects.Identifier;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author udeyoje
 */
public class DatabaseDaoImplTest
{
    private Config config;
    
    public DatabaseDaoImplTest()
    {
        config = new Config("https://docussandra.stg-prsn.com/", Config.Format.LONG);
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of create method, of class DatabaseDaoImpl.
     */
    @Test
    public void testCreate() throws Exception
    {
        System.out.println("create");
        Database entity = new Database("testdb");
        DatabaseDaoImpl instance = new DatabaseDaoImpl(config);
        Database expResult = entity;
        DatabaseResponse result = instance.create(entity);
        assertNotNull(result);
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.name(), result.name());
        assertEquals(expResult.description(), result.description());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().getCollections());
        assertNotNull(result.getLinks().getSelf());
        assertNotNull(result.getLinks().getUp());
        assertNotNull(result.getUpdatedAt());
        assertNotNull(result.getCreatedAt());
    }

    /**
     * Test of delete method, of class DatabaseDaoImpl.
     */
    @Test
    @Ignore
    public void testDelete_Database() throws Exception
    {
        System.out.println("delete");
        Database entity = null;
        DatabaseDaoImpl instance = null;
        instance.delete(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class DatabaseDaoImpl.
     */
    @Test
    @Ignore
    public void testDelete_Identifier()
    {
        System.out.println("delete");
        Identifier identifier = null;
        DatabaseDaoImpl instance = null;
        instance.delete(identifier);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exists method, of class DatabaseDaoImpl.
     */
    @Test
    @Ignore
    public void testExists()
    {
        System.out.println("exists");
        Identifier identifier = null;
        DatabaseDaoImpl instance = null;
        boolean expResult = false;
        boolean result = instance.exists(identifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class DatabaseDaoImpl.
     */
    @Test
    @Ignore
    public void testRead()
    {
        System.out.println("read");
        Identifier identifier = null;
        DatabaseDaoImpl instance = null;
        Database expResult = null;
        Database result = instance.read(identifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAll method, of class DatabaseDaoImpl.
     */
    @Test
    @Ignore
    public void testReadAll_0args()
    {
        System.out.println("readAll");
        DatabaseDaoImpl instance = null;
        List<Database> expResult = null;
        List<Database> result = instance.readAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAll method, of class DatabaseDaoImpl.
     */
    @Test
    @Ignore
    public void testReadAll_Identifier()
    {
        System.out.println("readAll");
        Identifier id = null;
        DatabaseDaoImpl instance = null;
        List<Database> expResult = null;
        List<Database> result = instance.readAll(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class DatabaseDaoImpl.
     */
    @Test
    @Ignore
    public void testUpdate()
    {
        System.out.println("update");
        Database entity = null;
        DatabaseDaoImpl instance = null;
        Database expResult = null;
        Database result = instance.update(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
