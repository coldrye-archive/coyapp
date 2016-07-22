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

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

/**
 * The class AbstractUnique models the root of a hierarchy of derived mapped
 * super classes and entity classes.
 *
 * <code>AbstractUnique</code>S
 *
 * <ul>
 * <li>have an assigned, universally unique id as their primary key</li>
 * </ul>
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@MappedSuperclass
public abstract class AbstractUnique
        extends AbstractEntity<String>
{

    public static final String ID = "id";

    /**
     * The generated unique id.
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "CHAR(40)")
    private String id;

    protected AbstractUnique()
    {
        super();
    }

    /**
     * Gets the assigned unique id.
     *
     * @return
     */
    @Override
    public String getId()
    {
        return id;
    }

    /**
     * Sets the assigned unique id.
     *
     * @param id
     */
    @Override
    public void setId(final String id)
    {
        this.id = id;
    }
}
