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

import eu.coldrye.app.backend.model.security.common.AbstractRealmObject;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
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
   @UniqueConstraint(name = "UK_COY_SEC_ACL_ENTRY_ACL_OWNER",
                     columnNames =
                     {
                         "parent_id", "owner_id"
                     }),
   @UniqueConstraint(name = "UK_COY_SEC_ACL_ENTRY_ACL_ROLE_PERMISSION",
                     columnNames =
                     {
                         "parent_id", "role_id", "permission_id"
                     })
})
@DiscriminatorColumn(name = "entry_class")
public class AclEntry
        extends AbstractRealmObject
{
}
