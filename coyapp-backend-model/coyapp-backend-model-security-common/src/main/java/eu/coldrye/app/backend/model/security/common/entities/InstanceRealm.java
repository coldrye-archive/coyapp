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

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * TBD models the instance of a specific subsystem realm
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@DiscriminatorValue("INSTANCE")
public class InstanceRealm
        extends Realm
{

    @ManyToOne(optional = false)
    @Column(name = "instance_subsystem_id", nullable = false, updatable = false)
    private SubsystemRealm subsystem;

    public SubsystemRealm getSubsystem()
    {
        return subsystem;
    }

    public void setSubsystem(final SubsystemRealm subsystem)
    {
        this.subsystem = subsystem;
    }
}
