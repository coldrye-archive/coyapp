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
package eu.coldrye.app.backend.model.subsystem.entities;

import eu.coldrye.app.backend.model.common.AbstractUnique;
import eu.coldrye.app.backend.model.common.Describable;
import eu.coldrye.app.backend.model.common.FqNamed;
import eu.coldrye.app.backend.model.common.Named;
import eu.coldrye.app.backend.model.common.Node;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@Table(name = "coy_subsystem", uniqueConstraints =
{
   @UniqueConstraint(name = "UK_COY_SUBSYSTEM_PARENT_NAME",
                     columnNames =
                     {
                         "parent_id", "name"
                     })
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "subsystem_type", length = Named.MAX)
public class Subsystem
        extends AbstractUnique
        implements Node<Subsystem>,
                   FqNamed,
                   Describable
{

    @ManyToOne
    @Column(name = "parent_id")
    private Subsystem parent;

    @Column(nullable = false)
    @Size(min = Named.MIN, max = Named.MAX)
    private String name;

    @Column(nullable = true)
    @Size(min = Describable.MIN, max = Describable.MAX)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "node_type", nullable = false)
    private NodeType nodeType;

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public String getFqName()
    {
        return getNodeType() == NodeType.ROOT ? getName() : getParent().
                getFqName().concat(FqNamed.FQNAME_SEPARATOR).concat(getName());
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public NodeType getNodeType()
    {
        return nodeType;
    }

    @Override
    public Subsystem getParent()
    {
        return parent;
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

    @Override
    public void setNodeType(final NodeType nodeType)
    {
        this.nodeType = nodeType;
    }

    @Override
    public void setParent(final Subsystem parent)
    {
        this.parent = parent;
    }
}
