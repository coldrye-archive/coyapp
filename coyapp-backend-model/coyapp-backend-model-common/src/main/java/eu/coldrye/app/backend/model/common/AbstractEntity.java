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
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 * The abstract class AbstractEntity models the root of a hierarchy of derived
 * mapped super classes and entity classes.
 *
 * <code>AbstractEntity</code>S have both an <em>entity state</em> and an
 * assigned <em>owner role</em>.
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @param <ID> the concrete id type
 * @since 0.0.1
 */
@MappedSuperclass
public abstract class AbstractEntity<ID extends Serializable>
        implements Serializable
{

    public static final String OWNER_ROLE = "ownerRole";

    /**
     * The assigned owner role.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "owner_role", nullable = false, updatable = false)
    private OwnerRoleType ownerRole;

    /**
     * Constructs a new uninitialized instance of this.
     */
    protected AbstractEntity()
    {
    }

    public abstract ID getId();

    /**
     * Gets the assigned owner role.
     *
     * @return
     */
    public OwnerRoleType getOwnerRole()
    {
        return ownerRole;
    }

    public abstract void setId(final ID id);

    /**
     * Sets the assigned owner role.
     *
     * @param ownerRole
     */
    public void setOwnerRole(final OwnerRoleType ownerRole)
    {
        this.ownerRole = ownerRole;
    }

    /**
     * The enumeration OwnerRoleType is used to specify whether an entity is
     * owned by the system or the user.
     *
     * Once that the owner role was established, it cannot be changed.
     *
     * @see AbstractEntity
     * @author Carsten Klein <trancesilken@gmail.com>
     * @since 0.0.1
     */
    public static enum OwnerRoleType
    {
        /**
         * The entity is owned by the system. Only the system (user) is able to
         * modify or delete such entities.
         */
        SYSTEM,
        /**
         * The entity is owned by the user. Users are able to modify and delete
         * such entities.
         */
        USER;

        public boolean isSystemRole()
        {
            return SYSTEM.equals(this);
        }

        public boolean isUserRole()
        {
            return USER.equals(this);
        }
    }
}
