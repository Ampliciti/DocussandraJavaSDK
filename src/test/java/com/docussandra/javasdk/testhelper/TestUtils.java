package com.docussandra.javasdk.testhelper;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.dao.TableDao;
import com.docussandra.javasdk.dao.impl.DatabaseDaoImpl;
import com.docussandra.javasdk.dao.impl.TableDaoImpl;
import com.strategicgains.docussandra.domain.objects.Database;
import com.strategicgains.docussandra.domain.objects.Document;
import com.strategicgains.docussandra.domain.objects.Table;
import java.util.Date;
import java.util.UUID;

/**
 * Test utility class.
 *
 * @author jeffrey
 */
public class TestUtils
{

    /**
     * Gets a basic Database object for testing.
     *
     * @return
     */
    public static Database getTestDb()
    {
        Database entity = new Database("testdb");
        entity.description("This was a db created by java sdk tests.");
        return entity;
    }

    /**
     * Gets a basic Document object for testing.
     *
     * @return
     */
    public static Document getTestDocument()
    {
        Document entity = new Document();
        entity.table(getTestTable());
        entity.objectAsString("{\"greeting\":\"hello\", \"myindexedfield\": \"thisismyfield\", \"myindexedfield1\":\"my second field\", \"myindexedfield2\":\"my third field\"}");
        entity.setUuid(new UUID(0L, 1L));
        entity.setCreatedAt(new Date());
        entity.setUpdatedAt(new Date());
        return entity;
    }

    /**
     * Cleans up the test Database object that may or may not have been created.
     *
     * @param config Config object with information on how to connect to the
     * database.
     */
    public static void cleanupTestDb(Config config)
    {
        try
        {
            DatabaseDaoImpl instance = new DatabaseDaoImpl(config);
            instance.delete(TestUtils.getTestDb());
        } catch (Exception e)
        {
            ; //don't care
        }
    }

    /**
     * Creates and inserts a test db.
     *
     * @param config Config object with information on how to connect to the
     * database.
     */
    public static void insertTestDb(Config config)
    {
        try
        {
            DatabaseDaoImpl instance = new DatabaseDaoImpl(config);
            instance.create(TestUtils.getTestDb());
        } catch (Exception e)
        {
            throw new RuntimeException(e); //it's just a test
        }
    }

    public static Table getTestTable()
    {
        Table entity = new Table();
        entity.name("testtable");
        entity.description("This was a table created by java sdk tests.");
        entity.database(TestUtils.getTestDb());
        return entity;
    }

    /**
     * Creates and inserts a test table.
     *
     * @param config Config object with information on how to connect to the
     * database.
     */
    public static void insertTestTable(Config config)
    {
        try
        {
            TableDao instance = new TableDaoImpl(config);
            instance.create(TestUtils.getTestDb(), TestUtils.getTestTable());
        } catch (Exception e)
        {
            throw new RuntimeException(e); //it's just a test
        }
    }

    public static void cleanupTestTable(Config config)
    {
        try
        {
            TableDaoImpl tableImplInstance = new TableDaoImpl(config);
            tableImplInstance.delete(TestUtils.getTestDb(), TestUtils.getTestTable());
        } catch (Exception e)
        {
            ; //don't care
        }
    }

}
