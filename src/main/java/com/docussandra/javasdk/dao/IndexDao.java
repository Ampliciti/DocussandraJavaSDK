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

import com.strategicgains.docussandra.domain.objects.Identifier;
import com.strategicgains.docussandra.domain.objects.Index;
import java.util.List;

/**
 *
 * @author udeyoje
 */
public interface IndexDao
{

    public long countAll(Identifier id);

    public Index create(Index entity);

    public void delete(Identifier id);

    public void delete(Index entity);

    public boolean exists(Identifier identifier);

    /**
     * Marks an index as "active" meaning that indexing has completed on it.
     *
     * @param entity Index to mark active.
     */
    public void markActive(Index entity);

    public Index read(Identifier identifier);

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
