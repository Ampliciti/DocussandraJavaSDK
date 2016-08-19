package com.ampliciti.db.docussandra.javasdk.dao.impl;

import com.ampliciti.db.docussandra.javasdk.Config;
import com.ampliciti.db.docussandra.javasdk.RestUtils;
import com.ampliciti.db.docussandra.javasdk.SDKUtils;
import com.ampliciti.db.docussandra.javasdk.dao.IndexDao;
import com.ampliciti.db.docussandra.javasdk.dao.impl.parent.DaoParent;
import com.ampliciti.db.docussandra.javasdk.domain.IndexResponse;
import com.ampliciti.db.docussandra.javasdk.exceptions.RESTException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.pearson.docussandra.domain.objects.Identifier;
import com.pearson.docussandra.domain.objects.Index;
import java.io.IOException;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Dao for creating and retrieving indexes
 *
 * @author https://github.com/JeffreyDeYoung
 */
public class IndexDaoImpl extends DaoParent implements IndexDao {

  private final ObjectReader indexCreatedReader =
      SDKUtils.getObjectMapper().reader(IndexResponse.class);


  public IndexDaoImpl(Config config) {
    super(config);
  }

  @Override
  public long countAll(Identifier id) {
    throw new UnsupportedOperationException("Not done yet.");
  }

  @Override
  public IndexResponse create(Index entity) throws ParseException, RESTException, IOException {
    JSONParser parser = new JSONParser();
    String indexJSON = SDKUtils.createJSON(entity);
    JSONObject response = (JSONObject) super.doPostCall(
        RestUtils.createFullURL(getBaseURL(), entity.getTable()) + "/indexes/" + entity.getName(),
        (JSONObject) parser.parse(indexJSON));
    return indexCreatedReader.readValue(response.toJSONString());
    // return ir.getObject();
  }

  @Override
  public void delete(Identifier id) throws RESTException {
    if (id.size() < 3) {
      throw new IllegalArgumentException(
          "Identifier not precise enough. Needs ID as well. " + id.toString());
    }
    super.doDeleteCall(RestUtils.createFullURL(getBaseURL(), id.getDatabaseName(), id.getTableName()) + "/indexes/"
        + id.components().get(2));
  }

  @Override
  public void delete(Index entity) throws RESTException {
    super.doDeleteCall(RestUtils.createFullURL(getBaseURL(), entity.getTable()) + "/indexes/" + entity.getName());
  }

  @Override
  public boolean exists(Identifier identifier) throws RESTException {
    if (identifier.size() < 3) {
      throw new IllegalArgumentException(
          "Identifier not precise enough. Needs ID as well. " + identifier.toString());
    }
    try {
      super.doGetCall(RestUtils.createFullURL(getBaseURL(), identifier.getDatabaseName(), identifier.getTableName())
          + "/indexes/" + identifier.components().get(2));
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
   * Marks an index as "active" meaning that indexing has completed on it.
   *
   * @param entity Index to mark active.
   */
  @Override
  public void markActive(Index entity) {
    throw new UnsupportedOperationException("Not done yet.");
  }

  @Override
  public Index read(Identifier identifier) throws RESTException, IOException {
    if (identifier.size() < 3) {
      throw new IllegalArgumentException(
          "Identifier not precise enough. Needs ID as well. " + identifier.toString());
    }
    JSONObject response =
        super.doGetCall(RestUtils.createFullURL(getBaseURL(), identifier.getDatabaseName(), identifier.getTableName())
            + "/indexes/" + identifier.components().get(2));
    return indexCreatedReader.readValue(response.toJSONString());
  }

  @Override
  public List<Index> readAll(Identifier id) {
    throw new UnsupportedOperationException("Not done yet.");
  }

  @Override
  public List<Index> readAll() {
    throw new UnsupportedOperationException("Not done yet.");
  }

  /**
   * Same as readAll, but will read from the cache if available.
   *
   * @return
   */
  @Override
  public List<Index> readAllCached(Identifier id) {
    throw new UnsupportedOperationException("Not done yet.");
  }

  @Override
  public Index update(Index entity) {
    throw new UnsupportedOperationException("Not done yet.");
  }
}
