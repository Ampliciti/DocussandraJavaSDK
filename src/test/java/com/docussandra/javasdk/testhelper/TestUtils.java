package com.docussandra.javasdk.testhelper;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.dao.impl.DatabaseDaoImpl;
import com.docussandra.javasdk.dao.impl.TableDaoImpl;
import com.strategicgains.docussandra.domain.objects.Database;
import com.strategicgains.docussandra.domain.objects.Table;

/**
 * Test utility class.
 * @author jeffrey
 */
public class TestUtils
{

    /**
     * Gets a basic Database object for testing.
     * @return 
     */
    public static Database getTestDb()
    {
        Database entity = new Database("testdb");
        entity.description("This was a db created by java sdk tests.");
        return entity;
    }

    /**
     * Cleans up the test Database object that may or may not have been created.
     * @param config Config object with information on how to connect to the database.
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

    public static Table getTestTable()
    {
        Table entity = new Table();
        entity.name("testtable");
        entity.description("This was a table created by java sdk tests.");
        entity.database(TestUtils.getTestDb());
        return entity;
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
