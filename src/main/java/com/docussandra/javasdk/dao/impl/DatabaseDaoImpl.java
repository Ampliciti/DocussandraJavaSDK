package com.docussandra.javasdk.dao.impl;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.SDKUtils;
import com.docussandra.javasdk.dao.DatabaseDao;
import com.docussandra.javasdk.dao.impl.parent.DaoParent;
import com.docussandra.javasdk.domain.DatabaseResponse;
import com.docussandra.javasdk.exceptions.RESTException;
import com.strategicgains.docussandra.domain.objects.Database;
import com.strategicgains.docussandra.domain.objects.Identifier;
import java.io.IOException;
import java.util.List;
import org.codehaus.jackson.map.ObjectReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Database dao that accesses Docussandra via the REST API.
 *
 * @author udeyoje
 */
public class DatabaseDaoImpl extends DaoParent implements DatabaseDao
{

    private final JSONParser parser = new JSONParser();
    private final ObjectReader r = SDKUtils.getObjectMapper().reader(DatabaseResponse.class);

    public DatabaseDaoImpl(Config config)
    {
        super(config);
    }

    @Override
    public DatabaseResponse create(Database entity) throws ParseException, RESTException, IOException
    {
        String entityJson = SDKUtils.createJSON(entity);
        JSONObject response = super.doPostCall(super.createFullURL("") + "/" + entity.name(), (JSONObject) parser.parse(entityJson));
        return r.readValue(response.toJSONString());
    }

    @Override
    public void delete(Database entity) throws RESTException
    {
        super.doDeleteCall(super.createFullURL("") + "/" + entity.name());
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
