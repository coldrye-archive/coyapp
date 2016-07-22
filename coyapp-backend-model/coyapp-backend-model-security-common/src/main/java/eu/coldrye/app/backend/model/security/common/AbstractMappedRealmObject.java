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
package eu.coldrye.app.backend.model.security.common;

import eu.coldrye.app.backend.model.common.MappedDomainObject;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@MappedSuperclass
public abstract class AbstractMappedRealmObject
        extends AbstractRealmObject
        implements MappedDomainObject
{

    @Column(name = "domain_object_class_name", nullable = false,
            updatable = false)
    @Size(max = 2048)
    private String domainObjectClassName;

    @Column(name = "domain_object_id", nullable = false, updatable = false,
            columnDefinition = "CHAR(40)")
    private String domainObjectId;

    @Override
    public MappedDomainObjectId getMappedDomainObjectId()
    {
        return new MappedDomainObjectId(domainObjectClassName, domainObjectId);
    }

    @Override
    public void setMappedDomainObjectId(final MappedDomainObjectId mappedId)
    {
        this.domainObjectClassName = mappedId.getClassName();
        this.domainObjectId = mappedId.getId();
    }
}
