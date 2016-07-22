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

/**
 * The interface FqNamed models a uniquely named entity.
 *
 * The assigned names of named entities are considered to be of a technical
 * nature rather than for example the given name of an Actress or similar such
 * non technical entities, which by definition can never be considered unique.
 *
 * Once assigned, the name should not be changed, as it may be used or
 * referenced by code that is external to the application.
 *
 * The name of a fully qualified named entity is considered unique.
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
public interface FqNamed
        extends Named
{

    public static final String FQNAME_SEPARATOR = "::";

    /**
     * Gets the fully qualified name.
     *
     * @return
     */
    String getFqName();
}
