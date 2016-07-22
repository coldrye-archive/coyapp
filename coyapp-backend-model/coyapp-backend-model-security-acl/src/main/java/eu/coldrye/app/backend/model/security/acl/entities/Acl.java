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

import eu.coldrye.app.backend.model.security.common.AbstractMappedRealmObject;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@Table(name = "coy_sec_acl", uniqueConstraints =
{
   @UniqueConstraint(
           name = "UK_COY_SEC_ACL_SCHEMA_OBJECT",
           columnNames =
           {
               "schema_id", "domain_object_id"
           })
})
public class Acl
        extends AbstractMappedRealmObject
{
}
