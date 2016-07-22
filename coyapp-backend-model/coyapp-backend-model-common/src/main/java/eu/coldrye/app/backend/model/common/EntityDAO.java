/*
 * Copyright 2016 coldrye.
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
package eu.coldrye.app.backend.model.common;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * The interface EntityDAO models a data access object for standard CRUD
 * operations on individual entity types that derive from
 * {@link AbstractEntity}.
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @param <ID>
 * @param <T>
 * @since 0.0.1
 */
public interface EntityDAO<ID extends Serializable, T extends AbstractEntity<ID>>
{

    /**
     * Returns the number of all entities in the data store that match the
     * optionally specified query.
     *
     * @param query
     * @return
     */
    int count(final CriteriaQuery<Long> query);

    /**
     * Builds a query that can be used for counting entities in the underlying
     * data store.
     *
     * @return
     */
    CriteriaQuery<Long> createNewCountQuery();

    /**
     * Creates a new find query.
     *
     * @return
     */
    CriteriaQuery<T> createNewFindQuery();

    /**
     * Creates a new instance of the entity.
     *
     * @param ownerRole
     * @return
     */
    T createNewInstance(final AbstractEntity.OwnerRoleType ownerRole);

    /**
     * Creates a new instance of the entity.
     *
     * @param id
     * @param ownerRole
     * @return
     */
    T createNewInstance(final ID id, AbstractEntity.OwnerRoleType ownerRole);

    /**
     * Deletes the entity.
     *
     * @param entity
     */
    void delete(final T entity);

    void delete(final CriteriaQuery<T> query);

    /**
     * Returns a list of entities that match the optionally specified filter,
     * offset and limited by the optionally specified pager.
     *
     * By default, this will only consider entities with state
     * {@link EntityStateType#ACTIVE}.
     *
     * @param query
     * @return
     */
    List<T> find(final CriteriaQuery<T> query);

    T findOne(final ID id);

    T findOne(final CriteriaQuery<T> query);

    /**
     * Returns the assigned entity class.
     *
     * @return
     */
    Class<T> getEntityClass();

    /**
     * Returns the assigned entity manager or <code>null</code>.
     *
     * @return
     */
    EntityManager getEntityManager();

    /**
     * Persists the entity.
     *
     * @param entity
     * @return
     */
    T persist(final T entity);

    /**
     * Sets the entity manager.
     *
     * @param entityManager the entity manager or <code>null</code>
     */
    void setEntityManager(final EntityManager entityManager);
}
