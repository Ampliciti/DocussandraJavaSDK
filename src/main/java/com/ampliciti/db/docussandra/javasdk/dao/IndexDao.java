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

import com.ampliciti.db.docussandra.javasdk.domain.IndexResponse;
import com.ampliciti.db.docussandra.javasdk.exceptions.RESTException;
import com.pearson.docussandra.domain.objects.Identifier;
import com.pearson.docussandra.domain.objects.Index;
import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;

/**
 *
 * @author https://github.com/JeffreyDeYoung
 */
public interface IndexDao {

  public long countAll(Identifier id);

  public IndexResponse create(Index entity) throws ParseException, RESTException, IOException;

  public void delete(Identifier id) throws RESTException;

  public void delete(Index entity) throws RESTException;

  public boolean exists(Identifier identifier) throws RESTException;

  /**
   * Marks an index as "active" meaning that indexing has completed on it.
   *
   * @param entity Index to mark active.
   */
  public void markActive(Index entity);

  public Index read(Identifier identifier) throws RESTException, IOException;

  public List<Index> readAll(Identifier id);

  public List<Index> readAll();

  /**
   * Same as readAll, but will read from the cache if available.
   *
   * @return
   */
  public List<Index> readAllCached(Identifier id);

  public Index update(Index entity);

}
