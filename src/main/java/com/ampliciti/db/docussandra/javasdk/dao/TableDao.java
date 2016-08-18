/*
 * Copyright 2015 udeyoje.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.ampliciti.db.docussandra.javasdk.dao;

import com.ampliciti.db.docussandra.javasdk.domain.TableResponse;
import com.ampliciti.db.docussandra.javasdk.exceptions.RESTException;
import com.pearson.docussandra.domain.objects.Database;
import com.pearson.docussandra.domain.objects.Identifier;
import com.pearson.docussandra.domain.objects.Table;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author https://github.com/JeffreyDeYoung
 */
public interface TableDao {

  // TODO : long countTableSize(String database, String tableName);

  public TableResponse create(Table entity) throws ParseException, RESTException, IOException;

  public void delete(Table entity) throws RESTException;

  public void delete(Identifier id) throws RESTException;

  public boolean exists(Identifier id) throws RESTException;

  public TableResponse read(Identifier id) throws RESTException, IOException;

  public List<TableResponse> readAll(Database databaseEntity) throws RESTException, IOException;

  public void update(Table tableEntity) throws ParseException, RESTException, IOException;

}
