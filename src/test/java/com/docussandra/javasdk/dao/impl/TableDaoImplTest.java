package com.docussandra.javasdk.dao.impl;

import com.docussandra.javasdk.Config;
import static com.docussandra.javasdk.dao.impl.DatabaseDaoImplTest.getTestDb;
import com.docussandra.javasdk.domain.DatabaseResponse;
import com.docussandra.javasdk.domain.TableResponse;
import com.docussandra.javasdk.exceptions.RESTException;
import com.strategicgains.docussandra.domain.objects.Database;
import com.strategicgains.docussandra.domain.objects.Table;
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
        config = new Config("http://localhost:8081/", Config.Format.SHORT);
        dbImplInstance = new DatabaseDaoImpl(config);
        tableImplInstance = new TableDaoImpl(config);
    }

    public static Database getTestDb()
    {
        Database entity = new Database("testdb");
        entity.description("This was a db created by java sdk tests.");
        return entity;
    }

    public static Table getTestTable()
    {
        Table entity = new Table();
        entity.name("testtable");
        entity.description("This was a table created by java sdk tests.");
        entity.database(getTestDb());
        return entity;
    }

    public void cleanupTestDb()
    {
        try
        {
            DatabaseDaoImpl tableImplInstance = new DatabaseDaoImpl(config);
            tableImplInstance.delete(getTestDb());
        } catch (Exception e)
        {
            ;//don't care
        }
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
        cleanupTestDb();
        dbImplInstance.create(getTestDb());
    }

    @After
    public void tearDown()
    {
        cleanupTestDb();
    }

    /**
     * Test of create method, of class DatabaseDaoImpl.
     */
    @Test
    public void testTableCreate() throws Exception
    {
        System.out.println("create table");

        Table expTableResult = getTestTable();
        TableResponse result = tableImplInstance.create(getTestDb(), getTestTable());
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
        tableImplInstance.create(getTestDb(), getTestTable());
        Database dbEntity = getTestDb();
        Table tableEntity = getTestTable();
        tableImplInstance.delete(dbEntity, tableEntity);
        assertFalse(tableImplInstance.exists(getTestDb(), tableEntity.getId()));
    }

    /**
     * Test of delete method, of class TableDaoImpl.
     */
    @Test
    public void testDelete_Identifier() throws Exception
    {
        System.out.println("delete");
        tableImplInstance.create(getTestDb(), getTestTable());
        tableImplInstance.delete(getTestDb(), getTestTable().getId());
        assertFalse(tableImplInstance.exists(getTestDb(), getTestTable().getId()));
    }

    /**
     * Test of exists method, of class TableDaoImpl.
     */
    @Test
    public void testExists() throws Exception
    {
        System.out.println("table exists");
        boolean result = tableImplInstance.exists(getTestDb(), getTestTable().getId());
        assertFalse(result);
        tableImplInstance.create(getTestDb(), getTestTable());
        result = tableImplInstance.exists(getTestDb(), getTestTable().getId());
        assertTrue(result);
    }

    /**
     * Test of read method, of class TableDaoImpl.
     */
    @Test
    public void testRead() throws Exception
    {
        System.out.println("read table");
        tableImplInstance.create(getTestDb(), getTestTable());
        TableResponse result = tableImplInstance.read(getTestDb(), getTestTable().getId());
        assertEquals(getTestTable().getId(), result.getId());
        assertEquals(getTestTable().name(), result.name());
        assertEquals(getTestTable().description(), result.description());
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
        Table test1 = getTestTable();
        tableImplInstance.create(getTestDb(), test1);
        Table test2 = getTestTable();
        test2.name("testdb2");
        test2.description("Second descript");
        tableImplInstance.create(getTestDb(),test2);
        List<Table> expResult = new ArrayList<>();
        expResult.add(test1);
        expResult.add(test2);
        List<TableResponse> results = tableImplInstance.readAll(getTestDb());
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
        tableImplInstance.create(getTestDb(), getTestTable());//create
        Table updated = getTestTable();
        updated.description("This is a new description.");
        Table result = tableImplInstance.update(getTestDb(), updated);
        assertEquals(updated.description(), result.description());
        assertEquals(updated.getId(), result.getId());
        assertNotSame(updated.getUpdatedAt(), result.getUpdatedAt());
        
    }

}
