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
package com.docussandra.javasdk.dao;

import com.docussandra.javasdk.exceptions.RESTException;
import com.pearson.docussandra.domain.objects.Document;
import com.pearson.docussandra.domain.objects.Identifier;
import com.pearson.docussandra.domain.objects.QueryResponseWrapper;
import com.pearson.docussandra.domain.objects.Table;
import java.io.IOException;
import java.util.UUID;
import org.json.simple.parser.ParseException;

/**
 * Document dao that accesses Docussandra to manipulate the documents.
 * @author https://github.com/JeffreyDeYoung
 */
public interface DocumentDao
{

    /**
     * Creates a document.
     * @param table Table in which to create the document in.
     * @param entity Document to put into the Database.
     * @return The created Document including a UUID.
     * @throws RESTException If there was a problem making the call.
     * @throws IOException If there was a problem de-serializing the JSON
     * response.
     * @throws ParseException If the passed in database couldn't be serialized.
     */
    public Document create(Table table, Document entity) throws RESTException, ParseException, IOException;

    /**
     * Deletes a document.
     * @param table Table in which to delete the document from.
     * @param id Document UUID to delete
     * @throws RESTException If there was a problem making the call. 
     */
    public void delete(Table table, UUID id) throws RESTException;
    
    /**
     * Deletes a document.
     * @param identifier Identifier to delete.
     * @throws RESTException If there was a problem making the call. 
     */
    public void delete(Identifier identifier) throws RESTException;
            
    /**
     * Determines if the document exists or not.
     * @param identifier Identifier to check for existance.
     * @return True if it exists, false if it doesn't.
     * @throws RESTException If there was a problem making the call. 
     */
    public boolean exists(Identifier identifier) throws RESTException;

    /**
     * Reads a document.
     * @param identifier Identifier to read.
     * @return The requested document.
     * @throws RESTException If there was a problem making the call. 
     * @throws IOException If there was a problem de-serializing the JSON
     * response.
     */
    public Document read(Identifier identifier) throws RESTException, IOException;

    /**
     * 
     * @param identifier
     * @param limit
     * @param offset
     * @return
     * @throws RESTException If there was a problem making the call. 
     * @throws IOException If there was a problem de-serializing the JSON
     * response.
     */
    public QueryResponseWrapper readAll(Identifier identifier, int limit, long offset) throws RESTException, IOException;

    /**
     * Updates the document.
     * @param entity Updates the Document. (Determines the document to update based on the UUID.)
     * @throws RESTException If there was a problem making the call. 
     * @throws ParseException If the passed in database couldn't be serialized.
     * @throws IOException If there was a problem de-serializing the JSON
     * response.
     */
    public void update(Document entity) throws RESTException, ParseException, IOException;

}
