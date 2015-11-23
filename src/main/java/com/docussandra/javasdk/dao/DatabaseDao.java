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

import com.docussandra.javasdk.domain.DatabaseResponse;
import com.docussandra.javasdk.exceptions.RESTException;
import com.strategicgains.docussandra.domain.objects.Database;
import com.strategicgains.docussandra.domain.objects.Identifier;
import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;

/**
 *
 * @author udeyoje
 */
public interface DatabaseDao
{

    Database create(Database entity) throws ParseException, RESTException, IOException;

    void delete(Database entity) throws RESTException;

    void delete(Identifier identifier) throws RESTException;

    boolean exists(Identifier identifier) throws RESTException;

    DatabaseResponse read(Identifier identifier) throws RESTException, IOException;

    List<DatabaseResponse> readAll() throws RESTException, IOException;

    DatabaseResponse update(Database entity) throws RESTException, IOException;

}
