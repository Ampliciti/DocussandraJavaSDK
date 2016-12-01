/*
 * Copyright 2015 udeyoje.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ampliciti.db.docussandra.javasdk.dao;

import com.ampliciti.db.docussandra.javasdk.exceptions.RESTException;
import com.pearson.docussandra.domain.objects.Query;
import com.pearson.docussandra.domain.objects.QueryResponseWrapper;
import com.pearson.docussandra.exception.IndexParseException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

/**
 * Repository for querying for records.
 *
 * @author https://github.com/JeffreyDeYoung
 */
public interface QueryDao
{
    /**
     * Do a query without limit or offset.
     *
     * @param query Query to execute.
     * @return A query response.
     * @throws IndexParseException If the query is not on a valid index.
     */
    public QueryResponseWrapper query(Query query) throws IndexParseException, IOException, RESTException, ParseException;

    /**
     * Do a query with limit and offset.
     * @param query Query to execute.
     * @param limit Maximum number of results to return.
     * @param offset Number of records at the beginning of the results to
     * discard.
     * @return A query response.
     * @throws IndexParseException If the query is not on a valid index.
     */
    public QueryResponseWrapper query(Query query, int limit, long offset) throws IndexParseException, IOException, RESTException, ParseException;

}
