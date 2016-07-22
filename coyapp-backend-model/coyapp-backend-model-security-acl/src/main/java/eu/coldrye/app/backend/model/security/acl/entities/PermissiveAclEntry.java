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
package eu.coldrye.app.backend.model.security.acl.entities;

import eu.coldrye.app.backend.model.security.acl.entities.AclEntry;
import eu.coldrye.app.backend.model.security.common.entities.Role;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@Table(name = "coy_sec_acl_entry", uniqueConstraints =
{
   @UniqueConstraint(name = "UK_COY_SEC_ACL_ENTRY_PARENT_PERMISSION_ROLE",
                     columnNames =
                     {
                         "parent_id", "perm_permission_id", "perm_role_id"
                     })
})
@DiscriminatorValue("PERM")
public class PermissiveAclEntry
        extends AclEntry
{

    @Column(name = "perm_is_denied", nullable = false, updatable = false)
    private boolean isDenied = false;

    @ManyToOne(optional = false)
    @Column(name = "perm_permission_id", nullable = false, updatable = false)
    private Permission permission;

    @ManyToOne(optional = false)
    @Column(name = "perm_role_id", nullable = false, updatable = false)
    private Role role;

    public Permission getPermission()
    {
        return permission;
    }

    public Role getRole()
    {
        return role;
    }

    public boolean isIsDenied()
    {
        return isDenied;
    }

    public void setIsDenied(final boolean isDenied)
    {
        this.isDenied = isDenied;
    }

    public void setPermission(final Permission permission)
    {
        this.permission = permission;
    }

    public void setRole(final Role role)
    {
        this.role = role;
    }
}
