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
package eu.coldrye.app.backend.model.security.common;

import eu.coldrye.app.backend.model.common.Describable;
import eu.coldrye.app.backend.model.common.Named;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@MappedSuperclass
public abstract class AbstractNamedRealmObject
        extends AbstractRealmObject
        implements Named,
                   Describable
{

    /**
     * The optional description.
     */
    @Size(min = Describable.MIN, max = Describable.MAX)
    String description;

    /**
     * The assigned name that is unique within the assigned realm.
     */
    @Size(min = Named.MIN, max = Named.MAX)
    @Column(nullable = false)
    String name;

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setDescription(final String description)
    {
        this.description = description;
    }

    @Override
    public void setName(final String name)
    {
        this.name = name;
    }
}
