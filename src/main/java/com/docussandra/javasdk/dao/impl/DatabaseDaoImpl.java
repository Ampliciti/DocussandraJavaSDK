package com.docussandra.javasdk.dao.impl;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.dao.DatabaseDao;
import com.docussandra.javasdk.dao.impl.parent.DaoParent;
import com.strategicgains.docussandra.domain.objects.Database;
import com.strategicgains.docussandra.domain.objects.Identifier;
import java.util.List;

/**
 * Database dao that accesses Docussandra via the REST API.
 * @author udeyoje
 */
public class DatabaseDaoImpl extends DaoParent implements DatabaseDao
{

    public DatabaseDaoImpl(Config config)
    {
        super(config);
    }

    @Override
    public Database create(Database entity)
    {
        throw new UnsupportedOperationException("Not done yet");
    }

    @Override
    public void delete(Database entity)
    {
        throw new UnsupportedOperationException("Not done yet");
    }

    @Override
    public void delete(Identifier identifier)
    {
        throw new UnsupportedOperationException("Not done yet");
    }

    @Override
    public boolean exists(Identifier identifier)
    {
        throw new UnsupportedOperationException("Not done yet");
    }

    @Override
    public Database read(Identifier identifier)
    {
        throw new UnsupportedOperationException("Not done yet");
    }

    @Override
    public List<Database> readAll()
    {
        throw new UnsupportedOperationException("Not done yet");
    }

    @Override
    public List<Database> readAll(Identifier id)
    {
        throw new UnsupportedOperationException("Not done yet");
    }

    @Override
    public Database update(Database entity)
    {
        throw new UnsupportedOperationException("Not done yet");
    }

}
