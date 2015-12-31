package com.docussandra.javasdk.dao.impl;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.testhelper.TestUtils;
import com.strategicgains.docussandra.domain.objects.Document;
import com.strategicgains.docussandra.domain.objects.Identifier;
import com.strategicgains.docussandra.domain.objects.QueryResponseWrapper;
import com.strategicgains.docussandra.domain.objects.Table;
import java.util.Date;
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

    private Config config;
    private DocumentDaoImpl instance;

    public DocumentDaoImplTest()
    {
        config = new Config("http://localhost:8081/", Config.Format.SHORT);
        instance = new DocumentDaoImpl(config);
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
        TestUtils.insertTestDb(config);
        TestUtils.insertTestTable(config);
    }

    @After
    public void tearDown() throws InterruptedException
    {
        TestUtils.cleanupTestDb(config);
        Thread.sleep(1000);
    }

    /**
     * Test of create method, of class DocumentDaoImpl.
     */
    @Test
    @Ignore//bug in cassandra/docussandra?
    public void testCreate() throws Exception
    {
        System.out.println("create");
        Table table = TestUtils.getTestTable();
        Document entity = TestUtils.getTestDocument();
        Document expResult = entity;
        long start = new Date().getTime();
        Document result = instance.create(table, entity);

        assertEquals(expResult.databaseName(), result.databaseName());
        assertEquals(expResult.tableName(), result.tableName());
        assertEquals(expResult.object(), result.object());
        assertNotNull(entity.getCreatedAt());
        assertNotNull(entity.getUpdatedAt());
        long max_time_interval = 2000;
        //long now = new Date().getTime();
        if (entity.getCreatedAt().getTime() - start > max_time_interval)
        {
            fail("Created at time does not appear to be correct.");
        }
        if (entity.getUpdatedAt().getTime() - start > max_time_interval)
        {
            fail("Updated at time does not appear to be correct.");
        }
    }

    /**
     * Test of delete method, of class DocumentDaoImpl.
     */
    @Test
    @Ignore
    public void testDelete_Table_UUID() throws Exception
    {
        System.out.println("delete");
        Table table = TestUtils.getTestTable();
        UUID id = instance.create(TestUtils.getTestTable(), TestUtils.getTestDocument()).getUuid();
        assertTrue(instance.exists(TestUtils.getTestDocument().getId()));
        instance.delete(table, id);
        assertFalse(instance.exists(TestUtils.getTestDocument().getId()));
    }

    /**
     * Test of delete method, of class DocumentDaoImpl.
     */
    @Test
    @Ignore
    public void testDelete_Identifier() throws Exception
    {
        System.out.println("delete");
        Identifier identifier = TestUtils.getTestDocument().getId();
        instance.create(TestUtils.getTestTable(), TestUtils.getTestDocument());
        assertTrue(instance.exists(identifier));
        instance.delete(identifier);
        assertFalse(instance.exists(identifier));
    }

    /**
     * Test of exists method, of class DocumentDaoImpl.
     */
    @Test
    public void testExists() throws Exception
    {
        System.out.println("exists");
        Identifier identifier = TestUtils.getTestDocument().getId();
        boolean expResult = false;
        boolean result = instance.exists(identifier);//this is kinda a nonsense test, as the UUID does not get set until after document creation
        assertEquals(expResult, result);
        Identifier toCheck = instance.create(TestUtils.getTestTable(), TestUtils.getTestDocument()).getId();
        expResult = true;
        result = instance.exists(toCheck);
        assertEquals(expResult, result);
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
