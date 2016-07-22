/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.coldrye.app.backend.model.security.common.entities;

import eu.coldrye.app.backend.model.security.common.AbstractMappedRealmObject;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * TBD Accounts can be defined by realms that have the CAP_ACCOUNTS capability.
 * Per mapped domain object and realm there can be only one account.
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@Table(name = "coy_sec_account", uniqueConstraints =
{
   @UniqueConstraint(
           name = "UK_COY_SEC_ACCOUNT_PARENT_OBJECT",
           columnNames =
           {
               "parent_id", "domain_object_id"
           })
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "account_type")
public class Account
        extends AbstractMappedRealmObject
{

    @Enumerated(EnumType.STRING)
    @Column(name = "current_state", nullable = false)
    private AccountStateType currentState;

    public AccountStateType getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(final AccountStateType currentState)
    {
        this.currentState = currentState;
    }

    /**
     *
     * @author Carsten Klein <trancesilken@gmail.com>
     * @since 0.0.1
     */
    public static enum AccountStateType
    {
        /**
         * <ul>
         * <li>(SIGNING ON) NEW -> SIGNED_ON</li>
         * <li>(DEPLOY) NEW -> ACTIVE (SYSTEM DEFINED USERS ONLY)</li>
         * <li>(VERIFICATION) SIGNED_ON -> VERIFIED</li>
         * <li>(INITIAL SIGN IN) VERIFIED -> ACTIVE</li>
         * <li>(INACTIVITY) ACTIVE -> INACTIVE</li>
         * <li>(CONTINUED INACTIVITY) SIGNED_ON -> Account.isDeleted = true,
         * User.isDeleted = true</li>
         * <li>(CONTINUED INACTIVITY) INACTIVE -> Account.isDeleted = true,
         * User.isDeleted = true</li>
         * <li>(CONTINUED INACTIVITY) VERIFIED -> Account.isDeleted = true,
         * User.isDeleted = true</li>
         * <li>(ADMIN, MISBEHAVIOR) ACTIVE -> BANNED</li>
         * <li>(ADMIN) ACTIVE -> DEACTIVATED (SYSTEM DEFINED USERS ONLY)</li>
         * <li>(ADMIN, COOLDOWN) BANNED -> SIGNED_ON</li>
         * <li>(BREAK IN ATTEMPT) ACTIVE -> BLOCKED</li>
         * <li>(BREAK IN ATTEMPT) INACTIVE -> BLOCKED</li>
         * <li>(ADMIN) DEACTIVATED -> ACTIVE (SYSTEM DEFINED USERS ONLY)</li>
         * <li>(SIGN/AUTH CREDENTIAL CHANGE) -> SIGNED_ON</li>
         * <li>(USER SIGNS OFF) ACTIVE -> SIGNED_OFF -> Account.isDeleted =
         * true, User.isDeleted = true</li>
         * <li>(ADMIN) BANNED -> SIGNED_OFF -> Account.isDeleted = true,
         * User.isDeleted = true</li>
         * <li>(SIGN IN) INACTIVE -> ACTIVE</li>
         * </ul>
         */
        ACTIVE, INACTIVE, DEACTIVATED, BANNED,
        BLOCKED, SIGNED_OFF, SIGNED_ON, VERIFIED
    }
}
