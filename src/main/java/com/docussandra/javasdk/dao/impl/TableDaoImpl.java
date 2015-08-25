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

import java.io.IOException;
import java.util.List;

/**
 * Table dao that accesses Docussandra to manipulate the tables via the REST API.
 *
 * Created by upunych on 8/25/15.
 */
public class TableDaoImpl extends DaoParent implements TableDao{

    private final JSONParser parser = new JSONParser();
    private final ObjectReader jsonObjectReader = SDKUtils.getObjectMapper().reader(TableResponse.class);

    public TableDaoImpl(Config config)
    {
        super(config);
    }

    @Override
    public TableResponse create(Database databaseEntity, Table tableEntity) throws ParseException, RESTException, IOException {
        String tableJson = SDKUtils.createJSON(tableEntity);
        JSONObject response = super.doPostCall(super.createFullURL("") + "/" + databaseEntity.name() +
                "/" + tableEntity.name(), (JSONObject) parser.parse(tableJson));
       return jsonObjectReader.readValue(response.toJSONString());
    }

    @Override
    public void delete(Database databaseEntity,Table tableEntity) throws RESTException {
        super.doDeleteCall(super.createFullURL("") + "/" + databaseEntity.name() + "/" + tableEntity.name());
    }

    @Override
    public void delete(Database databaseEntity,Identifier id) throws RESTException {
        super.doDeleteCall(super.createFullURL("") + "/" + databaseEntity.name() + "/" + id.getTableName());
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
