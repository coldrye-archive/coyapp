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
package eu.coldrye.app.backend.model.base.internal.spi;

import eu.coldrye.app.backend.model.base.AbstractEntity;
import eu.coldrye.app.backend.model.base.entities.Realm;
import eu.coldrye.app.backend.model.base.entities.User;
import eu.coldrye.app.backend.model.base.AbstractRealmDAO;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
public final class RealmDAOImpl
        extends AbstractRealmDAO<Realm>
{

    public RealmDAOImpl()
    {
        super(Realm.class);
    }

    @Override
    public Realm newInstance(final String name, final Realm realm,
                             final AbstractEntity.OwnerRoleType ownerRole, final User user)
    {
        final Realm result = super.newInstance(ownerRole, realm, user);
        result.setName(name);
        return result;
    }

    @Override
    public void purge(Realm entity)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
