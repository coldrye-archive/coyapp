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

import eu.coldrye.app.backend.model.common.AbstractEntity.OwnerRoleType;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * The abstract class AbstractEntityDAO models the root of a hierarchy of
 * classes that specialize the DAO pattern for access to individual entity types
 * deriving from {@link AbstractEntity}.
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @param <ID>
 * @param <T>
 * @since 0.0.1
 */
public abstract class AbstractEntityDAO<ID extends Serializable, T extends AbstractEntity<ID>>
        implements EntityDAO<ID, T>
{

    private final Class<T> entityClass;

    private EntityManager entityManager;

    protected AbstractEntityDAO(final Class<T> entityClass)
    {
        this.entityClass = entityClass;
    }

    @Override
    public T persist(final T entity)
    {
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public CriteriaQuery<Long> createNewCountQuery()
    {
        CriteriaQuery<Long> result;
        final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        result = builder.createQuery(Long.class);
        result.select(builder.count(result.from(getEntityClass())));

        return result;
    }

    @Override
    public CriteriaQuery<T> createNewFindQuery()
    {
        CriteriaQuery<T> result;

        final Class<T> klass = getEntityClass();
        result = entityManager.getCriteriaBuilder().createQuery(klass);
        result.from(klass);

        return result;
    }

    @Override
    public final Class<T> getEntityClass()
    {
        return entityClass;
    }

    @Override
    public final EntityManager getEntityManager()
    {
        return entityManager;
    }

    @Override
    public T createNewInstance(final OwnerRoleType ownerRole)
    {
        T result = null;

        try
        {
            result = getEntityClass().newInstance();
            result.setOwnerRole(ownerRole);
        }
        catch (InstantiationException | IllegalAccessException ex)
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, ex.
                                                       getMessage(), ex);
            throw new RuntimeException(ex);
        }

        return result;
    }

    @Override
    public final void setEntityManager(final EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }
}
