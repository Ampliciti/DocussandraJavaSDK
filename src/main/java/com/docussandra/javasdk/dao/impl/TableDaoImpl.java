package com.docussandra.javasdk.dao.impl;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.SDKUtils;
import com.docussandra.javasdk.dao.TableDao;
import com.docussandra.javasdk.dao.impl.parent.DaoParent;
import com.docussandra.javasdk.domain.DatabaseResponse;
import com.docussandra.javasdk.domain.TableResponse;
import com.docussandra.javasdk.exceptions.RESTException;
import com.strategicgains.docussandra.domain.objects.Database;
import com.strategicgains.docussandra.domain.objects.Identifier;
import com.strategicgains.docussandra.domain.objects.Table;
import com.strategicgains.hyperexpress.HyperExpress;
import com.strategicgains.hyperexpress.builder.TokenBinder;
import com.strategicgains.hyperexpress.builder.TokenResolver;
import com.strategicgains.hyperexpress.builder.UrlBuilder;
import org.codehaus.jackson.map.ObjectReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

/**
 * Table dao that accesses Docussandra to manipulate the tables via the REST API.
 *
 * Created by upunych on 8/25/15.
 */
public class TableDaoImpl extends DaoParent implements TableDao{

    private final JSONParser parser = new JSONParser();
    private final ObjectReader r = SDKUtils.getObjectMapper().reader(DatabaseResponse.class);

    public TableDaoImpl(Config config)
    {
        super(config);
    }

    public TableResponse create(Database databaseEntity, Table tableEntity) throws ParseException, RESTException {
        String tableJson = SDKUtils.createJSON(tableEntity);
        JSONObject response = super.doPostCall(super.createFullURL("") + "/" + databaseEntity.name() +
                "/" + tableEntity.name(), (JSONObject) parser.parse(tableJson));
       return TableResponse;
    }

    @Override
    public long countAllTables(String database) {
        return 0;
    }

    @Override
    public long countTableSize(String database, String tableName) {
        return 0;
    }

    @Override
    public Table create(Table entity) {
        return null;
    }

    @Override
    public void delete(Table entity) {

    }

    @Override
    public void delete(Identifier id) {

    }

    @Override
    public boolean exists(Identifier identifier) {
        return false;
    }

    @Override
    public Table read(Identifier identifier) {
        return null;
    }

    @Override
    public List<Table> readAll(Identifier id) {
        return null;
    }

    @Override
    public List<Table> readAll() {
        return null;
    }

    @Override
    public Table update(Table entity) {
        return null;
    }
}
