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

import com.ampliciti.db.docussandra.javasdk.domain.DatabaseResponse;
import com.ampliciti.db.docussandra.javasdk.exceptions.RESTException;
import com.pearson.docussandra.domain.objects.Database;
import com.pearson.docussandra.domain.objects.Identifier;
import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;

/**
 * Database dao that accesses Docussandra.
 *
 * @author https://github.com/JeffreyDeYoung
 */
public interface DatabaseDao
{

    /**
     * Creates a new database.
     *
     * @param entity Database to create.
     * @return The database that was created.
     * @throws ParseException If there was a problem understanding the response.
     * @throws RESTException If there was a problem making the call.
     * @throws IOException If there was a problem de-serializing the JSON.
     */
    public Database create(Database entity) throws ParseException, RESTException, IOException;

    /**
     * Deletes a database. This should delete all tables and documents in the
     * database.
     *
     * @param entity Database to delete.
     * @throws RESTException If there was a problem making the call.
     */
    public void delete(Database entity) throws RESTException;

    /**
     * Deletes a database. This should delete all tables and documents in the
     * database.
     *
     * @param identifier Database to delete.
     * @throws RESTException If there was a problem making the call.
     */
    public void delete(Identifier identifier) throws RESTException;

    /**
     * Determines the specified database exists.
     *
     * @param identifier The identifier of the database to determine.
     * @return True if it exists, false if it does not.
     * @throws RESTException If there was a problem making the call.
     */
    public boolean exists(Identifier identifier) throws RESTException;

    /**
     * Fetches a database.
     *
     * @param identifier The identifier of the database to fetch.
     * @return The database requested (if it exists).
     * @throws RESTException If there was a problem making the call.
     * @throws IOException If there was a problem de-serializing the JSON.
     */
    public DatabaseResponse read(Identifier identifier) throws RESTException, IOException;

    /**
     * Fetches all the databases that you have permission to see in this
     * Docussandra instance.
     *
     * @return A list of all the databases you have permission to see.
     * @throws RESTException If there was a problem making the call.
     * @throws IOException If there was a problem de-serializing the JSON.
     */
    public List<DatabaseResponse> readAll() throws RESTException, IOException;

    /**
     * Updates a database.
     *
     * @param entity Database to update.
     * @return The updated database.
     * @throws RESTException If there was a problem making the call.
     * @throws IOException If there was a problem de-serializing the JSON
     * response.
     * @throws ParseException If the passed in database couldn't be serialized.
     */
    public void update(Database entity) throws RESTException, IOException, ParseException;

}
