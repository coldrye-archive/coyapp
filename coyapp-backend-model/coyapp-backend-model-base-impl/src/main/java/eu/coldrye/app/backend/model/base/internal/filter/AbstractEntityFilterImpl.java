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
import eu.coldrye.app.backend.model.base.EntityFilter;
import eu.coldrye.app.backend.model.base.Filter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 */
public class AbstractEntityFilterImpl<ID extends Serializable, T extends AbstractEntity>
        implements EntityFilter<ID, T>,
                   FilterImplementor<ID, T>
{

    protected List<Filter> children = new ArrayList<>();
    protected EntityFilter<ID, T> parent;

    protected AbstractEntityFilterImpl(final EntityFilter<ID, T> parent,
                                       final Filter... children)
    {
        this.parent = parent;
        this.children.addAll(Arrays.asList(children));
    }

    @Override
    public EntityFilter<ID, T> and(final Filter... filters)
    {
        return new AndFilterImpl<>(this, filters);
    }

    @Override
    public EntityFilter<ID, T> or(final Filter... filters)
    {
        return new OrFilterImpl<>(this, filters);
    }

    @Override
    public EntityFilter<ID, T> not(final Filter... filters)
    {
        return new NotFilterImpl<>(this, filters);
    }

    @Override
    public Filter byId(final ID... ids)
    {
        return new IdFilterImpl<>(ids);
    }

    @Override
    public Filter byAttribute(final String attribute, final Serializable value)
    {
        return new AttributeFilterImpl<ID, T>(attribute, value);
    }
}
