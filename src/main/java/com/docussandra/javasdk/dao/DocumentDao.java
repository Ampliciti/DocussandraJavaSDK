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
import com.strategicgains.docussandra.domain.objects.Document;
import com.strategicgains.docussandra.domain.objects.Identifier;
import com.strategicgains.docussandra.domain.objects.QueryResponseWrapper;
import com.strategicgains.docussandra.domain.objects.Table;
import java.io.IOException;
import java.util.UUID;
import org.json.simple.parser.ParseException;

/**
 * Document dao that accesses Docussandra to manipulate the documents.
 * @author udeyoje
 */
public interface DocumentDao
{

    public Document create(Table table, Document entity) throws RESTException, ParseException, IOException;

    public void delete(Table table, UUID id) throws RESTException;

    public void delete(Identifier identifier) throws RESTException;
            
    public boolean exists(Identifier identifier) throws RESTException;

    public Document read(Identifier identifier) throws RESTException, IOException;

    public QueryResponseWrapper readAll(Identifier identifier, int limit, long offset) throws RESTException, IOException;

    public Document update(Document entity) throws RESTException;

}
