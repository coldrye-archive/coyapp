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
package eu.coldrye.app.backend.model.user.entities;

import eu.coldrye.app.backend.model.subsystem.entities.Instance;
import eu.coldrye.app.backend.model.user.entities.UserSubsystem.UserType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@DiscriminatorValue("USER")
public class UserInstance
        extends Instance
{

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public UserType getUserType()
    {
        return userType;
    }

    public boolean isIsDeleted()
    {
        return isDeleted;
    }

    public void setIsDeleted(final boolean isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public void setUserType(UserType userType)
    {
        this.userType = userType;
    }
}
