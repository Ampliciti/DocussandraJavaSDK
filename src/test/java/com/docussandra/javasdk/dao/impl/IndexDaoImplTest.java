package com.docussandra.javasdk.dao.impl;

import com.ampliciti.db.docussandra.javasdk.dao.impl.IndexDaoImpl;
import com.ampliciti.db.docussandra.javasdk.Config;
import com.ampliciti.db.docussandra.javasdk.dao.IndexDao;
import com.ampliciti.db.docussandra.javasdk.domain.IndexResponse;
import com.docussandra.javasdk.testhelper.TestUtils;
import com.pearson.docussandra.domain.objects.Identifier;
import com.pearson.docussandra.domain.objects.Index;
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
 * @author https://github.com/JeffreyDeYoung
 */
public class IndexDaoImplTest
{

    private Config config;
    private IndexDao instance;

    public IndexDaoImplTest()
    {
        config = TestUtils.establishTestServer();
        instance = new IndexDaoImpl(config);
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
     * Test of countAll method, of class IndexDaoImpl.
     */
    @Test
    @Ignore
    public void testCountAll()
    {
        System.out.println("countAll");
        Identifier id = null;
        IndexDaoImpl instance = null;
        long expResult = 0L;
        long result = instance.countAll(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class IndexDaoImpl.
     */
    @Test
    public void testCreate() throws Exception
    {
        System.out.println("create");
        Index entity = TestUtils.getTestIndex();
        IndexResponse result = instance.create(entity);
        assertNotNull(result);
        assertEquals(entity.getDatabaseName(), result.getIndex().getDatabaseName());
        assertEquals(entity.getName(), result.getIndex().getName());
        assertEquals(entity.getTableName(), result.getIndex().getTableName());
        assertEquals(entity.getFields(), result.getIndex().getFields());
    }

    /**
     * Test of delete method, of class IndexDaoImpl.
     */
    @Test
    @Ignore
    public void testDelete_Identifier() throws Exception
    {
        System.out.println("delete");
        Index entity = TestUtils.getTestIndex();
        Identifier identifier = entity.getId();
        instance.create(entity);
        assertTrue(instance.exists(identifier));
        instance.delete(identifier);
        assertFalse(instance.exists(identifier));
    }

    /**
     * Test of delete method, of class IndexDaoImpl.
     */
    @Test
    @Ignore
    public void testDelete_Index() throws Exception
    {
        System.out.println("delete");
        Index entity = TestUtils.getTestIndex();
        Identifier identifier = entity.getId();
        instance.create(entity);
        assertTrue(instance.exists(identifier));
        instance.delete(entity);
        assertFalse(instance.exists(identifier));
    }

    /**
     * Test of exists method, of class IndexDaoImpl.
     */
    @Test
    @Ignore
    public void testExists() throws Exception
    {
        System.out.println("exists");
        Identifier identifier = null;
        IndexDaoImpl instance = null;
        boolean expResult = false;
        boolean result = instance.exists(identifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of markActive method, of class IndexDaoImpl.
     */
    @Test
    @Ignore
    public void testMarkActive()
    {
        System.out.println("markActive");
        Index entity = null;
        IndexDaoImpl instance = null;
        instance.markActive(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of read method, of class IndexDaoImpl.
     */
    @Test
    @Ignore
    public void testRead() throws Exception
    {
        System.out.println("read");
        Identifier identifier = null;
        IndexDaoImpl instance = null;
        Index expResult = null;
        Index result = instance.read(identifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAll method, of class IndexDaoImpl.
     */
    @Test
    @Ignore
    public void testReadAll_Identifier()
    {
        System.out.println("readAll");
        Identifier id = null;
        IndexDaoImpl instance = null;
        List<Index> expResult = null;
        List<Index> result = instance.readAll(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAll method, of class IndexDaoImpl.
     */
    @Test
    @Ignore
    public void testReadAll_0args()
    {
        System.out.println("readAll");
        IndexDaoImpl instance = null;
        List<Index> expResult = null;
        List<Index> result = instance.readAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAllCached method, of class IndexDaoImpl.
     */
    @Test
    @Ignore
    public void testReadAllCached()
    {
        System.out.println("readAllCached");
        Identifier id = null;
        IndexDaoImpl instance = null;
        List<Index> expResult = null;
        List<Index> result = instance.readAllCached(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class IndexDaoImpl.
     */
    @Test
    @Ignore
    public void testUpdate()
    {
        System.out.println("update");
        Index entity = null;
        IndexDaoImpl instance = null;
        Index expResult = null;
        Index result = instance.update(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
