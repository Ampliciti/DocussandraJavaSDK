package com.ampliciti.db.docussandra.javasdk.dao.impl;

import com.ampliciti.db.docussandra.javasdk.Config;
import com.ampliciti.db.docussandra.javasdk.SDKUtils;
import com.ampliciti.db.docussandra.javasdk.dao.QueryDao;
import com.ampliciti.db.docussandra.javasdk.dao.impl.parent.DaoParent;
import com.ampliciti.db.docussandra.javasdk.exceptions.RESTException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.pearson.docussandra.domain.objects.Query;
import com.pearson.docussandra.domain.objects.QueryResponseWrapper;
import com.pearson.docussandra.exception.IndexParseException;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Dao for querying Docussandra.
 */
public class QueryDaoImpl extends DaoParent implements QueryDao {

  private final ObjectReader r = SDKUtils.getObjectMapper().reader(QueryResponseWrapper.class);

  public QueryDaoImpl(Config config) {
    super(config);
  }

  /**
   * Do a query without limit or offset.
   *
   * @param query Query to execute.
   * @return A query response.
   * @throws IndexParseException If the query is not on a valid index.
   */
  @Override
  public QueryResponseWrapper query(Query query)
      throws IndexParseException, IOException, RESTException, ParseException {
    JSONParser parser = new JSONParser();
    String queryJSON = SDKUtils.createJSON(query);
    HashMap<String, String> headers = new HashMap();
    headers.put("limit", String.valueOf(query.getLimit()));
    JSONArray response =
        (JSONArray) super.doPostCall(super.createFullURL(query.getTableAsObject()) + "/queries",
            (JSONObject) parser.parse(queryJSON), headers);
    return r.readValue(response.toJSONString());

  }

  /**
   * Do a query with limit and offset.
   *
   * @param query Query to execute.
   * @param limit Maximum number of results to return.
   * @param offset Number of records at the beginning of the results to discard.
   * @return A query response.
   * @throws IndexParseException If the query is not on a valid index.
   */
  @Override
  public QueryResponseWrapper query(Query query, int limit, long offset)
      throws IndexParseException, IOException, RESTException, ParseException {
    throw new UnsupportedOperationException("Not done yet.");
  }

}
