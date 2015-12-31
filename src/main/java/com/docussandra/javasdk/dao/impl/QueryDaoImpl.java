package com.docussandra.javasdk.dao.impl;

import com.docussandra.javasdk.Config;
import com.docussandra.javasdk.dao.QueryDao;
import com.docussandra.javasdk.dao.impl.parent.DaoParent;
import com.strategicgains.docussandra.domain.objects.Query;
import com.strategicgains.docussandra.domain.objects.QueryResponseWrapper;
import com.strategicgains.docussandra.exception.IndexParseException;

/**
 * Dao for querying Docussandra.
 */
public class QueryDaoImpl extends DaoParent implements QueryDao
{

    public QueryDaoImpl(Config config)
    {
        super(config);
    }

    /**
     * Do a query without limit or offset.
     *
     * @param query ParsedQuery to execute.
     * @return A query response.
     * @throws IndexParseException If the query is not on a valid index.
     */
    public QueryResponseWrapper query(Query query) throws IndexParseException
    {
        throw new UnsupportedOperationException("Not done yet.");
    }

    /**
     * Do a query with limit and offset.
     *
     * @param query ParsedQuery to execute.
     * @param limit Maximum number of results to return.
     * @param offset Number of records at the beginning of the results to
     * discard.
     * @return A query response.
     * @throws IndexParseException If the query is not on a valid index.
     */
    public QueryResponseWrapper query(Query query, int limit, long offset) throws IndexParseException
    {
        throw new UnsupportedOperationException("Not done yet.");
    }

}
