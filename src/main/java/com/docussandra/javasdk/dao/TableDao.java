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

import com.docussandra.javasdk.domain.TableResponse;
import com.docussandra.javasdk.exceptions.RESTException;
import com.strategicgains.docussandra.domain.objects.Database;
import com.strategicgains.docussandra.domain.objects.Identifier;
import com.strategicgains.docussandra.domain.objects.Table;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author udeyoje
 */
public interface TableDao
{

    //TODO : long countTableSize(String database, String tableName);

    TableResponse create(Database databaseEntity,Table entity) throws ParseException, RESTException, IOException;

    void delete(Database databaseEntity,Table entity) throws RESTException;

    void delete(Database databaseEntity,Identifier id) throws RESTException;

    boolean exists(Database databaseEntity,Identifier id) throws RESTException;

    TableResponse read(Database databaseEntity,Identifier id) throws RESTException, IOException;

    List<Table> readAll(Identifier id);

    List<Table> readAll();
    
    Table update(Database databaseEntity,Table tableEntity) throws ParseException, RESTException, IOException;
    
}
