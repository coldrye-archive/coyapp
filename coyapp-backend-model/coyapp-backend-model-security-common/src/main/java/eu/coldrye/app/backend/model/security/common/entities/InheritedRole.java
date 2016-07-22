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
package eu.coldrye.app.backend.model.security.common.entities;

import eu.coldrye.app.backend.model.common.AbstractEntity;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TBD the role inherited by a specific other role.
 *
 * TODO avoid inheritance cycles and redundant inheritances
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@Table(name = "coy_sec_inherited_role")
@IdClass(InheritedRole.InheritedRoleId.class)
@SuppressWarnings("ValidAttributes")
public class InheritedRole
        extends AbstractEntity<InheritedRole.InheritedRoleId>
{

    public final static String ROLE = "role";
    public final static String SUPER_ROLE = "superRole";

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false, updatable = false)
    private Role role;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "super_role_id", nullable = false, updatable = false)
    private Role superRole;

    @Override
    public InheritedRoleId getId()
    {
        return new InheritedRoleId(role, superRole);
    }

    public Role getRole()
    {
        return role;
    }

    public Role getSuperRole()
    {
        return superRole;
    }

    @Override
    public void setId(final InheritedRoleId id)
    {
        role = id.role;
        superRole = id.superRole;
    }

    public void setRole(final Role role)
    {
        this.role = role;
    }

    public void setSuperRole(final Role role)
    {
        this.superRole = role;
    }

    public static class InheritedRoleId
            implements Serializable
    {

        private final Role role;
        private final Role superRole;

        public InheritedRoleId(final Role role, final Role superRole)
        {
            this.role = role;
            this.superRole = superRole;
        }

        @Override
        public boolean equals(Object o)
        {
            if (!(o instanceof InheritedRoleId))
            {
                throw new IllegalArgumentException();
            }
            final InheritedRoleId other = (InheritedRoleId) o;

            return Objects.equals(role.getId(), other.role.getId())
                   && Objects.equals(superRole.getId(), other.superRole.getId());
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(role.getId(), superRole.getId());
        }
    }
}
