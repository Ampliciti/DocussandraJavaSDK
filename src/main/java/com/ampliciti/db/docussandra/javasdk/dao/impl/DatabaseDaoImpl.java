package com.ampliciti.db.docussandra.javasdk.dao.impl;

import com.ampliciti.db.docussandra.javasdk.SDKConfig;
import com.ampliciti.db.docussandra.javasdk.RestUtils;
import com.ampliciti.db.docussandra.javasdk.SDKUtils;
import com.ampliciti.db.docussandra.javasdk.dao.DatabaseDao;
import com.ampliciti.db.docussandra.javasdk.dao.impl.parent.DaoParent;
import com.ampliciti.db.docussandra.javasdk.domain.DatabaseListResponse;
import com.ampliciti.db.docussandra.javasdk.domain.DatabaseResponse;
import com.ampliciti.db.docussandra.javasdk.exceptions.RESTException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.pearson.docussandra.domain.objects.Database;
import com.pearson.docussandra.domain.objects.Identifier;
import java.io.IOException;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Database dao that accesses Docussandra via the REST API.
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class DatabaseDaoImpl extends DaoParent implements DatabaseDao {

  private final ObjectReader r = SDKUtils.getObjectMapper().reader(DatabaseResponse.class);
  private final ObjectReader rList = SDKUtils.getObjectMapper().reader(DatabaseListResponse.class);

  /**
   * Constructor.
   *
   * @param config Config object that determines your connection to the REST API.
   */
  public DatabaseDaoImpl(SDKConfig config) {
    super(config);
  }

  /**
   * Creates a new database.
   *
   * @param entity Database to create.
   * @return The database that was created.
   * @throws ParseException If there was a problem understanding the response.
   * @throws RESTException If there was a problem making the call.
   * @throws IOException If there was a problem de-serializing the JSON.
   */
  @Override
  public DatabaseResponse create(Database entity)
      throws ParseException, RESTException, IOException {
    JSONParser parser = new JSONParser();
    String entityJson = SDKUtils.createJSON(entity);
    JSONObject response = (JSONObject) restDao.doPostCall(
        RestUtils.createFullURL(getBaseURL(), entity.getName(), null, null),
        (JSONObject) parser.parse(entityJson));
    return r.readValue(response.toJSONString());
  }

  /**
   * Deletes a database. This should delete all tables and documents in the database.
   *
   * @param entity Database to delete.
   * @throws RESTException If there was a problem making the call.
   */
  @Override
  public void delete(Database entity) throws RESTException {
    restDao.doDeleteCall(RestUtils.createFullURL(getBaseURL(), entity.getName(), null, null));
  }

  /**
   * Deletes a database. This should delete all tables and documents in the database.
   *
   * @param identifier Database to delete.
   * @throws RESTException If there was a problem making the call.
   */
  @Override
  public void delete(Identifier identifier) throws RESTException {
    restDao.doDeleteCall(RestUtils.createFullURL(getBaseURL(), identifier));
  }

  /**
   * Determines the specified database exists.
   *
   * @param identifier The identifier of the database to determine.
   * @return True if it exists, false if it does not.
   * @throws RESTException If there was a problem making the call.
   */
  @Override
  public boolean exists(Identifier identifier) throws RESTException {
    try {
      restDao.doGetCall(RestUtils.createFullURL(getBaseURL(), identifier));
      return true;
    } catch (RESTException e) {
      if (e.getErrorCode() == 404) {
        return false;
      } else {
        throw e;
      }
    }
  }

  /**
   * Fetches a database.
   *
   * @param identifier The identifier of the database to fetch.
   * @return The database requested (if it exists).
   * @throws RESTException If there was a problem making the call.
   * @throws IOException If there was a problem de-serializing the JSON.
   */
  @Override
  public DatabaseResponse read(Identifier identifier) throws RESTException, IOException {
    JSONObject response = restDao.doGetCall(RestUtils.createFullURL(getBaseURL(), identifier));
    return r.readValue(response.toJSONString());
  }

  /**
   * Fetches all the databases that you have permission to see in this Docussandra instance.
   *
   * @return A list of all the databases you have permission to see.
   * @throws RESTException If there was a problem making the call.
   * @throws IOException If there was a problem de-serializing the JSON.
   */
  @Override
  public List<DatabaseResponse> readAll() throws RESTException, IOException {
    String url = RestUtils.createFullURL(getBaseURL(), "", null);
    url = url.substring(0, url.length() - 1);
    JSONObject response = restDao.doGetCall(url);
    DatabaseListResponse objectResponse = rList.readValue(response.toJSONString());
    return objectResponse.getEmbedded().getDatabases();
  }

  /**
   * Updates a database.
   *
   * @param entity Database to update.
   * @throws RESTException If there was a problem making the call.
   * @throws IOException If there was a problem de-serializing the JSON response.
   * @throws ParseException If the passed in database couldn't be serialized.
   */
  @Override
  public void update(Database entity) throws RESTException, IOException, ParseException {
    JSONParser parser = new JSONParser();
    String entityJson = SDKUtils.createJSON(entity);
    restDao.doPutCall(
        RestUtils.createFullURL(getBaseURL(), entity.getId().getDatabaseName(), null, null),
        (JSONObject) parser.parse(entityJson));
  }

}
