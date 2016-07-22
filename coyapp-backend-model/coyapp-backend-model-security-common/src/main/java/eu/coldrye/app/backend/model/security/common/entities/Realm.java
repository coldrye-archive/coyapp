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

import eu.coldrye.app.backend.model.common.Node;
import eu.coldrye.app.backend.model.security.common.AbstractMappedRealmObject;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * TBD realms are mapped to individual subsystems or instances thereof, for
 * example a site instance in a multi site environment or, considering a project
 * management solution, a subproject thereof. realms, based on their assigned
 * capabilities, can provide for accounts, groups and roles. Subrealms can be
 * defined by realms that have the CAP_SUBREALMS capability. Per mapped domain
 * object there can be only one realm.
 *
 * TBD realm use cases and provable hierarchies
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@Table(name = "coy_sec_realm", uniqueConstraints =
{
   @UniqueConstraint(name = "UK_COY_SEC_REALM_OBJECT",
                     columnNames =
                     {
                         "domain_object_id"
                     })
})
@DiscriminatorColumn(name = "realm_type")
public class Realm
        extends AbstractMappedRealmObject
        implements Node<Realm>
{

    @Column(nullable = false)
    private int capabilities;

    @Enumerated(EnumType.STRING)
    @Column(name = "node_type", nullable = false)
    private NodeType nodeType;

    public int getCapabilities()
    {
        return capabilities;
    }

    @Override
    public NodeType getNodeType()
    {
        return nodeType;
    }

    public boolean hasCapability(final int capability)
    {
        return (getCapabilities() & capability) == capability;
    }

    public boolean isInstance()
    {
        return this instanceof InstanceRealm;
    }

    public void setCapabilities(final int capabilities)
    {
        this.capabilities = capabilities;
    }

    @Override
    public void setNodeType(final NodeType nodeType)
    {
        this.nodeType = nodeType;
    }

    public static interface RealmCapabilities
    {

        /**
         * Defines whether a realm supports subrealms.
         */
        public static final int SUBREALMS = 1;

        /**
         * Defines whether a realm can be reassigned to a different account.
         */
        public static final int REASSIGN = 2;

        /**
         * Defines whether a realm can define accounts.
         */
        public static final int ACCOUNTS = 4;

        /**
         * Defines whether a realm can define roles.
         */
        public static final int ROLES = 8;

        /**
         * Defines whether a realm can define groups.
         */
        public static final int GROUPS = 16;

        /**
         * Defines whether a realm can define permissions.
         */
        public static final int PERMISSIONS = 32;
    }
}
