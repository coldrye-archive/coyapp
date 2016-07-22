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
package eu.coldrye.app.backend.model.base.internal.filter;

import eu.coldrye.app.backend.model.base.AbstractEntity;
import eu.coldrye.app.backend.model.base.AttributeFilter;
import eu.coldrye.app.backend.model.base.EntityDAO;
import eu.coldrye.app.backend.model.base.Filter;
import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 */
public class AttributeFilterImpl<ID extends Serializable, T extends AbstractEntity>
        implements AttributeFilter<T>,
                   FilterImplementor<ID, T>
{

    private final String attribute;
    private final Serializable[] values;

    AttributeFilterImpl(final String attribute, final Serializable... values)
    {
        this.attribute = attribute;
        this.values = Arrays.copyOf(values, values.length);
    }

    @Override
    public Filter between(T min, T max)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Predicate createPredicate(EntityDAO<ID, T> dao)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filter equal(T value)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filter greaterOrEqual(T value)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filter greaterThan(T value)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filter in(T... values)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filter lessOrEqual(T value)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filter lessThan(T value)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filter notEqual(T value)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filter notIn(T... values)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static class GreaterThanFilterImpl
            implements Filter,
                       FilterImplementor
    {

        @Override
        public Predicate createPredicate(EntityDAO dao)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
