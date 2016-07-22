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

/**
 * TBD:refactor to security-base-schema
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 */
public interface Schema
{

    public interface Group
    {

        public static final String SCHEMA = "TBD:WELL-KNOWN-UUID";

        public static final String CREATE = "TBD:WELL-KNOWN-UUID";
        public static final String DELETE = "TBD:WELL-KNOWN-UUID";
        public static final String RENAME = "TBD:WELL-KNOWN-UUID";
        public static final String VIEW = "TBD:WELL-KNOWN-UUID";
        public static final String MANAGE_ROLES = "TBD:WELL-KNOWN-UUID";
        public static final String VIEW_ROLES = "TBD:WELL-KNOWN-UUID";
    }

    public interface Realm
    {

        public static final String SCHEMA = "TBD:WELL-KNOWN-UUID";

        public static final String CREATE = "TBD:WELL-KNOWN-UUID";
        public static final String DELETE = "TBD:WELL-KNOWN-UUID";
        public static final String RENAME = "TBD:WELL-KNOWN-UUID";
        public static final String VIEW = "TBD:WELL-KNOWN-UUID";
        public static final String MANAGE_GROUPS = "TBD:WELL-KNOWN-UUID";
        public static final String VIEW_GROUPS = "TBD:WELL-KNOWN-UUID";
        public static final String MANAGE_ROLES = "TBD:WELL-KNOWN-UUID";
        public static final String VIEW_ROLES = "TBD:WELL-KNOWN-UUID";
        public static final String MANAGE_SUBREALMS = "TBD:WELL-KNOWN-UUID";
        public static final String VIEW_SUBREALMS = "TBD:WELL-KNOWN-UUID";
        public static final String MANAGE_ACCOUNTS = "TBD:WELL-KNOWN-UUID";
        public static final String VIEW_ACCOUNTS = "TBD:WELL-KNOWN-UUID";
    }

    public interface Role
    {

        public static final String SCHEMA = "TBD:WELL-KNOWN-UUID";

        public static final String CREATE = "TBD:WELL-KNOWN-UUID";
        public static final String DELETE = "TBD:WELL-KNOWN-UUID";
        public static final String RENAME = "TBD:WELL-KNOWN-UUID";
        public static final String VIEW = "TBD:WELL-KNOWN-UUID";
        public static final String MANAGE_INHERITANCE = "TBD:WELL-KNOWN-UUID";
        public static final String VIEW_INHERITANCE = "TBD:WELL-KNOWN-UUID";
    }

    public interface UserAccount
    {

        public static final String SCHEMA = "TBD:WELL-KNOWN-UUID";

        public static final String CREATE = "TBD:WELL-KNOWN-UUID";
        public static final String DELETE = "TBD:WELL-KNOWN-UUID";
        public static final String RENAME = "TBD:WELL-KNOWN-UUID";
        public static final String VIEW = "TBD:WELL-KNOWN-UUID";
        public static final String MANAGE_GROUPS = "TBD:WELL-KNOWN-UUID";
        public static final String VIEW_GROUPS = "TBD:WELL-KNOWN-UUID";
    }
}
