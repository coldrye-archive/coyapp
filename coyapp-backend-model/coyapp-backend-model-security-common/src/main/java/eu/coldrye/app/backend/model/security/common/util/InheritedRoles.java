/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.coldrye.app.backend.model.security.common.util;

import eu.coldrye.app.backend.model.security.common.entities.InheritedRole;
import eu.coldrye.app.backend.model.security.common.entities.Role;
import eu.coldrye.app.backend.model.security.common.spi.InheritedRoleDAO;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO move to separate component
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 */
@Deprecated
public final class InheritedRoles
{

    /**
     * Tests whether the specified superRole was already derived from the
     * specified role or whether role and superRole are the same.
     *
     * @param dao
     * @param role
     * @param superRole
     * @return
     */
    public static boolean isCycle(final InheritedRoleDAO dao,
                                  final Role role,
                                  final Role superRole)
    {
        // FIXME: simple case first: role === superRole
        final Set<Role> inheritedRoles = collectAllInheritedRoles(dao,
                                                                  superRole);
        inheritedRoles.add(superRole);

        return inheritedRoles.contains(role);
    }

    /**
     * Tests whether the specified superRole is already part of the inheritance
     * hierarchy of the specified role.
     *
     * @param dao
     * @param role
     * @param superRole
     * @return
     */
    // TODO should return the inherited roles as a path with the first being
    // the one that inherits the specified super role
    public static boolean isRedundant(final InheritedRoleDAO dao,
                                      final Role role,
                                      final Role superRole)
    {
        final Set<Role> inheritedRoles = collectAllInheritedRoles(dao, role);
        return inheritedRoles.contains(superRole);
    }

    // collects all inherited roles regardless of the realm
    public static Set<Role> collectAllInheritedRoles(
            final InheritedRoleDAO dao, final Role role)
    {
        final Set<Role> result = new HashSet();

        final List<InheritedRole> inheritedRoles = dao.findByRole(role);
        for (final InheritedRole ir : inheritedRoles)
        {
            final Role sr = ir.getSuperRole();
            result.add(sr);
            result.addAll(collectAllInheritedRoles(dao, sr));
        }

        return result;
    }

    /**
     * produces a map of SecRole -> InheritedRoles
     */
//    public static Map<SecRole, Set<SecRole>> collectAllInheritedRoles(
//            final SecInheritedRoleDAO dao, final SecRole role)
//    {
//        final Set<SecRole> result = new HashSet();
//
//        List<SecInheritedRole> inheritedRoles = dao.findByRole(role);
//        for (final SecInheritedRole ir : inheritedRoles)
//        {
//            result.add(ir.getSuperRole());
//            result.addAll(collectAllInheritedRoles(dao, ir.getSuperRole()));
//        }
//
//       return result;
//    }
    /**
     * Must not be instantiated.
     */
    private InheritedRoles()
    {
    }
}
