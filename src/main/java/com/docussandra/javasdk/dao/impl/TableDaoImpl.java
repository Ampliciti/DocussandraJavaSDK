package com.docussandra.javasdk.dao.impl;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.SDKUtils;
import com.docussandra.javasdk.dao.TableDao;
import com.docussandra.javasdk.dao.impl.parent.DaoParent;
import com.docussandra.javasdk.domain.TableResponse;
import com.docussandra.javasdk.exceptions.RESTException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.strategicgains.docussandra.domain.objects.Database;
import com.strategicgains.docussandra.domain.objects.Identifier;
import com.strategicgains.docussandra.domain.objects.Table;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * Table dao that accesses Docussandra to manipulate the tables via the REST
 * API.
 *
 * Created by upunych on 8/25/15.
 */
public class TableDaoImpl extends DaoParent implements TableDao
{

    private final JSONParser parser = new JSONParser();
    private final ObjectReader jsonObjectReader = SDKUtils.getObjectMapper().reader(TableResponse.class);

    public TableDaoImpl(Config config)
    {
        super(config);
    }

    @Override
    public TableResponse create(Database databaseEntity, Table tableEntity) throws ParseException, RESTException, IOException
    {
        String tableJson = SDKUtils.createJSON(tableEntity);
        JSONObject response = super.doPostCall(super.createFullURL("") + databaseEntity.name()
                + "/" + tableEntity.name(), (JSONObject) parser.parse(tableJson));
        return jsonObjectReader.readValue(response.toJSONString());
    }

    @Override
    public void delete(Database databaseEntity, Table tableEntity) throws RESTException
    {
        super.doDeleteCall(super.createFullURL("") + "/" + databaseEntity.name() + "/" + tableEntity.name());
    }

    @Override
    public void delete(Database databaseEntity, Identifier id) throws RESTException
    {
        super.doDeleteCall(super.createFullURL("") + "/" + databaseEntity.name() + "/" + id.getTableName());
    }

    @Override
    public boolean exists(Database databaseEntity, Identifier id) throws RESTException
    {
        try
        {
            super.doGetCall(super.createFullURL("") + "/" + databaseEntity.name() + "/" + id.getTableName());
            return true;
        } catch (RESTException e)
        {
            if (e.getErrorCode() == 404)
            {
                return false;
            } else
            {
                throw e;
            }
        }
    }

    @Override
    public TableResponse read(Database databaseEntity, Identifier id) throws RESTException, IOException
    {
        JSONObject response = super.doGetCall(super.createFullURL("") + "/" + databaseEntity.name() + "/" + id.getTableName());
        return jsonObjectReader.readValue(response.toJSONString());
    }

    //TODO: to convert jsons into lists
    @Override
    public List<Table> readAll(Identifier id)
    {
        throw new UnsupportedOperationException("Not done yet");
    }

    @Override
    public List<Table> readAll()
    {
        throw new UnsupportedOperationException("Not done yet");
    }

    @Override
    public Table update(Database databaseEntity, Table tableEntity) throws ParseException, RESTException, IOException
    {
        // running the put route
        String tableJson = SDKUtils.createJSON(tableEntity);
        JSONObject putResponse = super.doPostCall(super.createFullURL("") + "/" + databaseEntity.name()
                + "/" + tableEntity.name(), (JSONObject) parser.parse(tableJson));

        // run the get route on the updated table
        JSONObject getResponse = super.doGetCall(super.createFullURL("") + "/" + databaseEntity.name() + "/" + tableEntity.name());
        return jsonObjectReader.readValue(getResponse.toJSONString());
    }
}
