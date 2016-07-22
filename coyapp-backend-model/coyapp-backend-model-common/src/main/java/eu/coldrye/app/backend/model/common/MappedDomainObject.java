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
package eu.coldrye.app.backend.model.common;

import java.util.Objects;

/**
 * The interface MappedDomainObject models an entity that is loosely coupled to
 * an entity from a different application domain and where no referential
 * integrity between the two can be established, which might be due to the fact
 * that both entities reside in different data stores on altogether different
 * service sites.
 *
 * ## Example Use Cases
 *
 * ### Security Model
 *
 * The CoyApp security model is meant to be deployed into a second, more secure
 * database. By that, one cannot establish a direct referential integrity
 * between the entities of the security model and that of the application
 * itself, unless the DBMS in use supports such features, which is, of course,
 * out of the scope of this API.
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
public interface MappedDomainObject
{

    MappedDomainObjectId getMappedDomainObjectId();

    void setMappedDomainObjectId(final MappedDomainObjectId mappedId);

    /**
     * TBD:note that while this might be used as an IdClass, it currently is
     * not. It is provided for convenience only.
     */
    public static class MappedDomainObjectId
    {

        /**
         * The class of the mapped domain object. This is provided for debugging
         * purposes only.
         */
        private final String className;

        /**
         * The id of the mapped domain object.
         */
        private final String id;

        public MappedDomainObjectId(final AbstractUnique entity)
        {
            this(entity.getClass().getCanonicalName(), entity.getId());
        }

        public MappedDomainObjectId(final String className, final String id)
        {
            this.className = className;
            this.id = id;
        }

        public String getId()
        {
            return id;
        }

        public String getClassName()
        {
            return className;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(className, id);
        }

        @Override
        public boolean equals(Object o)
        {
            if (!(o instanceof MappedDomainObjectId))
            {
                throw new IllegalArgumentException(
                        "MappedDomainObjectId required");
            }
            MappedDomainObjectId other = (MappedDomainObjectId) o;
            return Objects.equals(className, other.className)
                   && Objects.equals(id, other.id);
        }
    }
}
