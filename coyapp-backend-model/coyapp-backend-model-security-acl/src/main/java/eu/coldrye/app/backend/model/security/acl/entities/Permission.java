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

import eu.coldrye.app.backend.model.security.common.AbstractNamedRealmObject;
import eu.coldrye.app.backend.model.security.common.entities.Realm;
import javax.persistence.Entity;

/**
 * TBD defined by security schema.
 *
 * in order to be more resilient to refactoring, all permissions have a well
 * known UUID.
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 */
@Entity
public class Permission
        extends AbstractNamedRealmObject
{

    @Override
    public void setParent(final Realm parent)
    {
        if (parent.isInstance())
        {
            throw new IllegalArgumentException("SubsystemRealm required");
        }
        if (!parent.hasCapability(Realm.RealmCapabilities.PERMISSIONS))
        {
            throw new IllegalArgumentException(
                    "realm does not have the PERMISSIONS capability");
        }
        super.setParent(parent);
    }
}
