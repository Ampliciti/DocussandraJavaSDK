package com.docussandra.javasdk.dao.impl;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.SDKUtils;
import com.docussandra.javasdk.dao.QueryDao;
import com.docussandra.javasdk.dao.impl.parent.DaoParent;
import com.docussandra.javasdk.exceptions.RESTException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.pearson.docussandra.domain.objects.Query;
import com.pearson.docussandra.domain.objects.QueryResponseWrapper;
import com.pearson.docussandra.exception.IndexParseException;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Dao for querying Docussandra.
 */
public class QueryDaoImpl extends DaoParent implements QueryDao
{

    private final ObjectReader r = SDKUtils.getObjectMapper().reader(QueryResponseWrapper.class);

    public QueryDaoImpl(Config config)
    {
        super(config);
    }

    /**
     * Do a query without limit or offset.
     *
     * @param query ParsedQuery to execute.
     * @param db Db to Query
     * @return A query response.
     * @throws IndexParseException If the query is not on a valid index.
     */
    @Override
    public QueryResponseWrapper query(String db, Query query) throws IndexParseException, IOException, RESTException, ParseException
    {
        JSONParser parser = new JSONParser();
        String queryJSON = SDKUtils.createJSON(query);
        JSONArray response = (JSONArray) super.doPostCall(super.createFullURL("") + db + "/" + query.getTable() + "/queries", (JSONObject) parser.parse(queryJSON));
        return r.readValue(response.toJSONString());

    }

    /**
     * Do a query with limit and offset.
     *
     * @param db Database to query
     * @param query ParsedQuery to execute.
     * @param limit Maximum number of results to return.
     * @param offset Number of records at the beginning of the results to
     * discard.
     * @return A query response.
     * @throws IndexParseException If the query is not on a valid index.
     */
    @Override
    public QueryResponseWrapper query(String db, Query query, int limit, long offset) throws IndexParseException, IOException, RESTException, ParseException
    {
        throw new UnsupportedOperationException("Not done yet.");
    }

}
