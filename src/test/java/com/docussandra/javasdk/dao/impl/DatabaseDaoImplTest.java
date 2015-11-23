package com.docussandra.javasdk.dao.impl;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.domain.DatabaseResponse;
import com.strategicgains.docussandra.domain.objects.Database;
import com.strategicgains.docussandra.domain.objects.Identifier;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author udeyoje
 */
public class DatabaseDaoImplTest
{

    private Config config;
    private DatabaseDaoImpl instance;

    public DatabaseDaoImplTest()
    {
        config = new Config("http://localhost:8081/", Config.Format.SHORT);
        instance = new DatabaseDaoImpl(config);
    }

    public static Database getTestDb()
    {
        Database entity = new Database("testdb");
        entity.description("This was a db created by java sdk tests.");
        return entity;
    }

    public void cleanupTestDb()
    {
        try
        {
            DatabaseDaoImpl instance = new DatabaseDaoImpl(config);
            instance.delete(getTestDb());
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
    public void setUp()
    {
        cleanupTestDb();
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
    public void testCreate() throws Exception
    {
        System.out.println("create");

        Database expResult = getTestDb();
        DatabaseResponse result = instance.create(getTestDb());
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
    public void testDelete_Database() throws Exception
    {
        System.out.println("delete");
        instance.create(getTestDb());
        Database entity = getTestDb();
        instance.delete(entity);
        assertFalse(instance.exists(new Identifier(getTestDb().name())));
    }

    /**
     * Test of delete method, of class DatabaseDaoImpl.
     */
    @Test
    public void testDelete_Identifier() throws Exception
    {
        System.out.println("delete");
        instance.create(getTestDb());
        Database entity = getTestDb();
        Identifier id = new Identifier(entity.name());
        instance.delete(id);
        assertFalse(instance.exists(id));
    }

    /**
     * Test of exists method, of class DatabaseDaoImpl.
     */
    @Test
    public void testExists() throws Exception
    {
        System.out.println("exists");
        Database entity = getTestDb();
        Identifier id = new Identifier(entity.name());
        boolean result = instance.exists(id);
        assertFalse(result);
        instance.create(entity);
        result = instance.exists(id);
        assertTrue(result);
    }

    /**
     * Test of read method, of class DatabaseDaoImpl.
     */
    @Test
    public void testRead() throws Exception
    {
        System.out.println("read");
        Database entity = getTestDb();
        instance.create(entity);
        Identifier identifier = entity.getId();
        Database expResult = entity;
        DatabaseResponse result = instance.read(identifier);
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
     * Test of readAll method, of class DatabaseDaoImpl.
     */
    @Test
    public void testReadAll_0args() throws Exception
    {
        System.out.println("readAll");
        //setup
        Database test1 = getTestDb();
        instance.create(test1);
        Database test2 = getTestDb();
        test2.name("testdb2");
        instance.create(test2);
        List<Database> expResult = new ArrayList<>();
        expResult.add(test1);
        expResult.add(test2);
        List<DatabaseResponse> results = instance.readAll();
        //ugly assert because there might be other DBs in the DB
        for (Database expected : expResult)
        {
            boolean found = false;
            Database match = null;
            for (Database result : results)
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
        System.out.println("update");
        Database entity = getTestDb();
        instance.create(entity);//create
        entity.description("This is a new database description. It should be different than before.");
        Database expResult = entity;
        Database result = instance.update(entity);
        assertEquals(expResult.description(), result.description());
        assertEquals(expResult.getId(), result.getId());
    }

}
