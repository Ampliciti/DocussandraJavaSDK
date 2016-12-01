package com.ampliciti.db.docussandra.javasdk.dao.impl;

import com.ampliciti.db.docussandra.javasdk.Config;
import com.ampliciti.db.docussandra.javasdk.SDKUtils;
import com.ampliciti.db.docussandra.javasdk.dao.TableDao;
import com.ampliciti.db.docussandra.javasdk.dao.impl.parent.DaoParent;
import com.ampliciti.db.docussandra.javasdk.domain.TableListResponse;
import com.ampliciti.db.docussandra.javasdk.domain.TableResponse;
import com.ampliciti.db.docussandra.javasdk.exceptions.RESTException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.pearson.docussandra.domain.objects.Database;
import com.pearson.docussandra.domain.objects.Identifier;
import com.pearson.docussandra.domain.objects.Table;
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
public class TableDaoImpl extends DaoParent implements TableDao {

  private final ObjectReader jsonObjectReader =
      SDKUtils.getObjectMapper().reader(TableResponse.class);
  private final ObjectReader rList = SDKUtils.getObjectMapper().reader(TableListResponse.class);

  public TableDaoImpl(Config config) {
    super(config);
  }

  @Override
  public TableResponse create(Table tableEntity) throws ParseException, RESTException, IOException {
    JSONParser parser = new JSONParser();
    String tableJson = SDKUtils.createJSON(tableEntity);
    JSONObject response = (JSONObject) super.doPostCall(super.createFullURL(tableEntity),
        (JSONObject) parser.parse(tableJson));
    System.out.println("Response: " + response.toJSONString());
    return getJsonObjectReader().readValue(response.toJSONString());
  }

  @Override
  public void delete(Table tableEntity) throws RESTException {
    super.doDeleteCall(super.createFullURL(tableEntity));
  }

  @Override
  public void delete(Identifier id) throws RESTException {
    super.doDeleteCall(super.createFullURL(id));
  }

  @Override
  public boolean exists(Identifier id) throws RESTException {
    try {
      super.doGetCall(super.createFullURL(id));
      return true;
    } catch (RESTException e) {
      if (e.getErrorCode() == 404) {
        return false;
      } else {
        throw e;
      }
    }
  }

  @Override
  public TableResponse read(Identifier id) throws RESTException, IOException {
    JSONObject response = super.doGetCall(super.createFullURL(id));
    return getJsonObjectReader().readValue(response.toJSONString());
  }

  @Override
  public List<TableResponse> readAll(Database db) throws RESTException, IOException {
    JSONObject response =
        super.doGetCall(super.createFullURL(db.getName(), null, null) + "/tables");
    TableListResponse objectResponse = rList.readValue(response.toJSONString());
    return objectResponse.getEmbedded().getTables();
  }

  @Override
  public void update(Table tableEntity) throws ParseException, RESTException, IOException {
    JSONParser parser = new JSONParser();
    // running the put route
    String tableJson = SDKUtils.createJSON(tableEntity);
    /* JSONObject putResponse = */
    super.doPutCall(super.createFullURL(tableEntity), (JSONObject) parser.parse(tableJson));

    // // run the get route on the updated table
    // JSONObject getResponse = super.doGetCall(super.createFullURL("") + "/" +
    // tableEntity.databaseName() + "/" + tableEntity.name());
    // return jsonObjectReader.readValue(getResponse.toJSONString());
  }

  /**
   * @return the jsonObjectReader
   */
  public ObjectReader getJsonObjectReader() {
    return jsonObjectReader;
  }
}
