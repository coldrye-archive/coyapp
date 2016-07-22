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

import eu.coldrye.app.backend.model.security.common.AbstractNamedRealmObject;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * TBD Roles can be defined by realms that have the CAP_GROUPS capability.
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@Table(name = "coy_sec_group", uniqueConstraints =
{
   @UniqueConstraint(name = "UK_COY_SEC_GROUP_PARENT_NAME",
                     columnNames =
                     {
                         "parent_id", "name"
                     })
})
@SuppressWarnings("ValidPrimaryTableName")
public class Group
        extends AbstractNamedRealmObject
{
}
