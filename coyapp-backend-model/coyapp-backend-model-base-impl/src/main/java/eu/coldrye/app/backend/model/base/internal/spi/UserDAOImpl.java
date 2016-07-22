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
import eu.coldrye.app.backend.model.base.AbstractUserDAO;
import java.util.List;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
public final class UserDAOImpl
        extends AbstractUserDAO<User>
{

    public UserDAOImpl()
    {
        super(User.class);
    }

    @Override
    public int countByUserType(User.UserType type)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findByUserType(User.UserType type)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User newInstance(final String name, final User.UserType type,
                            final Realm realm, final AbstractEntity.OwnerRoleType ownerRole,
                            final User user)
    {
        final User result = super.newInstance(name, ownerRole, realm, user);
        result.setUserType(type);
        return result;
    }

    @Override
    public void purge(User entity)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
