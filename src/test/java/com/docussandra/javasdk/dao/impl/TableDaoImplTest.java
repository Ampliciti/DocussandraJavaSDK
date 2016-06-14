package com.docussandra.javasdk.dao.impl;

import com.ampliciti.db.docussandra.javasdk.dao.impl.DatabaseDaoImpl;
import com.ampliciti.db.docussandra.javasdk.dao.impl.TableDaoImpl;
import com.ampliciti.db.docussandra.javasdk.Config;
import com.ampliciti.db.docussandra.javasdk.domain.TableResponse;
import com.ampliciti.db.docussandra.javasdk.exceptions.RESTException;
import com.docussandra.javasdk.testhelper.TestUtils;
import com.pearson.docussandra.domain.objects.Table;
import org.json.simple.parser.ParseException;
import org.junit.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by upunych on 8/25/15.
 */
public class TableDaoImplTest
{

    private Config config;
    private DatabaseDaoImpl dbImplInstance;
    private TableDaoImpl tableImplInstance;

    public TableDaoImplTest()
    {
        config = TestUtils.establishTestServer();
        dbImplInstance = new DatabaseDaoImpl(config);
        tableImplInstance = new TableDaoImpl(config);
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
    public void setUp() throws ParseException, IOException, RESTException
    {
        TestUtils.cleanupTestDb(config);
        dbImplInstance.create(TestUtils.getTestDb());
    }

    @After
    public void tearDown()
    {
        TestUtils.cleanupTestDb(config);
    }

    /**
     * Test of create method, of class DatabaseDaoImpl.
     */
    @Test
    public void testTableCreate() throws Exception
    {
        System.out.println("create table");

        Table expTableResult = TestUtils.getTestTable();
        TableResponse result = tableImplInstance.create(TestUtils.getTestTable());
        assertNotNull(result);
        assertEquals(expTableResult.name(), result.name());
        assertEquals(expTableResult.description(), result.description());
        assertEquals(expTableResult.getId(), result.getId());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().getSelf());
        assertNotNull(result.getLinks().getUp());
        assertNotNull(result.getUpdatedAt());
        assertNotNull(result.getCreatedAt());
    }

    /**
     * Test of delete method, of class TableDaoImpl.
     */
    @Test
    public void testDelete_Database() throws Exception
    {
        System.out.println("delete table");
        tableImplInstance.create(TestUtils.getTestTable());
        Table tableEntity = TestUtils.getTestTable();
        tableImplInstance.delete(tableEntity);
        assertFalse(tableImplInstance.exists(tableEntity.getId()));
    }

    /**
     * Test of delete method, of class TableDaoImpl.
     */
    @Test
    public void testDelete_Identifier() throws Exception
    {
        System.out.println("delete");
        tableImplInstance.create(TestUtils.getTestTable());
        tableImplInstance.delete(TestUtils.getTestTable().getId());
        assertFalse(tableImplInstance.exists(TestUtils.getTestTable().getId()));
    }

    /**
     * Test of exists method, of class TableDaoImpl.
     */
    @Test
    public void testExists() throws Exception
    {
        System.out.println("table exists");
        boolean result = tableImplInstance.exists(TestUtils.getTestTable().getId());
        assertFalse(result);
        tableImplInstance.create(TestUtils.getTestTable());
        result = tableImplInstance.exists(TestUtils.getTestTable().getId());
        assertTrue(result);
    }

    /**
     * Test of read method, of class TableDaoImpl.
     */
    @Test
    public void testRead() throws Exception
    {
        System.out.println("read table");
        tableImplInstance.create(TestUtils.getTestTable());
        TableResponse result = tableImplInstance.read(TestUtils.getTestTable().getId());
        assertEquals(TestUtils.getTestTable().getId(), result.getId());
        assertEquals(TestUtils.getTestTable().name(), result.name());
        assertEquals(TestUtils.getTestTable().description(), result.description());
        assertNotNull(result.getLinks());
        assertNotNull(result.getLinks().getSelf());
        assertNotNull(result.getLinks().getUp());
        assertNotNull(result.getUpdatedAt());
        assertNotNull(result.getCreatedAt());
    }

    /**
     * Test of readAll method, of class DatabaseDaoImpl.
     */
    @Test
    public void testReadAll() throws Exception
    {
        System.out.println("readAll");
        //setup
        Table test1 = TestUtils.getTestTable();
        tableImplInstance.create(test1);
        Table test2 = TestUtils.getTestTable();
        test2.name("testdb2");
        test2.description("Second descript");
        tableImplInstance.create(test2);
        List<Table> expResult = new ArrayList<>();
        expResult.add(test1);
        expResult.add(test2);
        List<TableResponse> results = tableImplInstance.readAll(TestUtils.getTestDb());
        //ugly assert because there might be other DBs in the DB
        for (Table expected : expResult)
        {
            boolean found = false;
            Table match = null;
            for (Table result : results)
            {
                if (expected.getId().equals(result.getId()))
                {
                    found = true;
                    match = result;
                    break;
                }
            }
            if (found)
            {
                assertEquals(expected.description(), match.description());
            } else
            {
                fail("Expected response: " + expResult.toString() + " was not found.");
            }

        }

    }

    /**
     * Test of update method, of class DatabaseDaoImpl.
     */
    @Test
    public void testUpdate() throws Exception
    {
        System.out.println("update table");
        tableImplInstance.create(TestUtils.getTestTable());//create
        Table updated = TestUtils.getTestTable();
        updated.description("This is a new description.");
        tableImplInstance.update(updated);
        Table result = tableImplInstance.read(updated.getId());
        assertEquals(updated.description(), result.description());
        assertEquals(updated.getId(), result.getId());
        assertNotSame(updated.getUpdatedAt(), result.getUpdatedAt());

    }

}
