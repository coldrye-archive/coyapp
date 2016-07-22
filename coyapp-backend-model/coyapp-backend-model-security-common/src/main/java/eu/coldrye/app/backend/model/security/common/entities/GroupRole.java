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
 * TBD the role implemented by a specific group.
 *
 * TODO avoid redundant implementations
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@Table(name = "coy_sec_group_role")
@IdClass(GroupRole.GroupRoleId.class)
@SuppressWarnings("ValidAttributes")
public class GroupRole
        extends AbstractEntity<GroupRole.GroupRoleId>
{

    public final static String GROUP = "group";
    public final static String ROLE = "role";

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", nullable = false, updatable = false)
    private Group group;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false, updatable = false)
    private Role role;

    public Group getGroup()
    {
        return group;
    }

    @Override
    public GroupRoleId getId()
    {
        return new GroupRoleId(group, role);
    }

    public Role getRole()
    {
        return role;
    }

    public void setGroup(final Group group)
    {
        this.group = group;
    }

    @Override
    public void setId(final GroupRoleId id)
    {
        setGroup(id.group);
        setRole(id.role);
    }

    public void setRole(final Role role)
    {
        this.role = role;
    }

    public static class GroupRoleId
            implements Serializable
    {

        private final Group group;
        private final Role role;

        public GroupRoleId(final Group group, final Role role)
        {
            this.group = group;
            this.role = role;
        }

        @Override
        public boolean equals(Object o)
        {
            if (!(o instanceof GroupRoleId))
            {
                throw new IllegalArgumentException();
            }
            final GroupRoleId other = (GroupRoleId) o;

            return Objects.equals(group.getId(), other.group.getId())
                   && Objects.equals(role.getId(), other.role.getId());
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(group.getId(), role.getId());
        }
    }
}
