package com.docussandra.javasdk.dao.impl;

import com.strategicgains.docussandra.domain.objects.Document;
import com.strategicgains.docussandra.domain.objects.Identifier;
import com.strategicgains.docussandra.domain.objects.QueryResponseWrapper;
import com.strategicgains.docussandra.domain.objects.Table;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author jeffrey
 */
public class DocumentDaoImplTest
{
    
    public DocumentDaoImplTest()
    {
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
     * Test of create method, of class DocumentDaoImpl.
     */
    @Test
    @Ignore
    public void testCreate() throws Exception
    {
        System.out.println("create");
        Table table = null;
        Document entity = null;
        DocumentDaoImpl instance = null;
        Document expResult = null;
        Document result = instance.create(table, entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class DocumentDaoImpl.
     */
    @Test
    @Ignore
    public void testDelete_Table_UUID() throws Exception
    {
        System.out.println("delete");
        Table table = null;
        UUID id = null;
        DocumentDaoImpl instance = null;
        instance.delete(table, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class DocumentDaoImpl.
     */
    @Test
    @Ignore
    public void testDelete_Identifier() throws Exception
    {
        System.out.println("delete");
        Identifier identifier = null;
        DocumentDaoImpl instance = null;
        instance.delete(identifier);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exists method, of class DocumentDaoImpl.
     */
    @Test
    @Ignore
    public void testExists() throws Exception
    {
        System.out.println("exists");
        Identifier identifier = null;
        DocumentDaoImpl instance = null;
        boolean expResult = false;
        boolean result = instance.exists(identifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class DocumentDaoImpl.
     */
    @Test
    @Ignore
    public void testRead() throws Exception
    {
        System.out.println("read");
        Identifier identifier = null;
        DocumentDaoImpl instance = null;
        Document expResult = null;
        Document result = instance.read(identifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAll method, of class DocumentDaoImpl.
     */
    @Test
    @Ignore
    public void testReadAll() throws Exception
    {
        System.out.println("readAll");
        Identifier identifier = null;
        int limit = 0;
        long offset = 0L;
        DocumentDaoImpl instance = null;
        QueryResponseWrapper expResult = null;
        QueryResponseWrapper result = instance.readAll(identifier, limit, offset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class DocumentDaoImpl.
     */
    @Test
    @Ignore
    public void testUpdate() throws Exception
    {
        System.out.println("update");
        Document entity = null;
        DocumentDaoImpl instance = null;
        Document expResult = null;
        Document result = instance.update(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
