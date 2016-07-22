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
package eu.coldrye.app.backend.model.security.common.util;

import eu.coldrye.app.backend.model.security.common.entities.GroupRole;
import eu.coldrye.app.backend.model.security.common.spi.GroupRoleDAO;
import java.util.List;
import java.util.Set;

/**
 * TODO move to separate component
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Deprecated
public class GroupRoles
{

    public static Set<GroupRole> determineRedundantRoles(
            final GroupRoleDAO dao, final List<GroupRole> groupRoles)
    {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
