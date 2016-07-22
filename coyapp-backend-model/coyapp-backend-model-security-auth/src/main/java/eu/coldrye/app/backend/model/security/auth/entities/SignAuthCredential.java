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
package eu.coldrye.app.backend.model.security.auth.entities;

import eu.coldrye.app.backend.model.base.Named;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 * TBD The class SignAuthCredential models a sign by which users can
 * authenticate themselves with the system. While a single user can have
 * multiple signs, for example it is recommended that a user should have at most
 * two such signs, e.g. a customer id {@link SignType#NID} and a main domain
 * {@link SignType#ID} or an email address {@link SignType#EMAIL}.
 *
 * Normally, a user will have only a single sign, for example an email address
 * or an alias name {@link SignType#ALIAS}, which they use to sign in.
 *
 * Signs need to be associated with {@link TokenAuthCredential}S in order for
 * them to become usable. A sign that does not have a <code>token</code> is
 * considered {@link SignStateType#INVALID}.
 *
 * Multiple signs can be associated with the same credential.
 *
 * Once a new sign becomes associated with a token, it will require validation
 * {@link SignStateType#VALIDATING}. And once the sign was successfully
 * validated, it will become {@link SignStateType#VALID}. If the validation
 * process fails, the sign will become <code>INVALID</code> again.
 *
 * And finally, multiple failed attempts to sign in to the service or
 * application using a specific sign will result in a sign being put into a
 * {@link SignStateType#LOCKED} state.
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@Table(name = "coy_sec_auth_credential", uniqueConstraints =
{
   /*
    * each account can have at most one sign per type and site realm
    */
   @UniqueConstraint(
           name = "UK_COY_SEC_AUTH_CREDENTIAL_REALM_ACCOUNT_SIGN_TYPE",
           columnNames =
           {
               "realm_id", "account_id", "sign_type"
           }),
   /*
    * we do not want multiple user accounts from the same site realm reusing
    * the same sign
    */
   @UniqueConstraint(
           name = "UK_COY_SEC_AUTH_CREDENTIAL_REALM_SIGN",
           columnNames =
           {
               "realm_id", "sign_sign"
           })
})
@DiscriminatorValue("SIGN")
public class SignAuthCredential
        extends AuthCredential
{

    public static final String TOKEN = "token";
    public static final String CURRENT_STATE = "currentState";
    public static final String SIGN = "sign";
    public static final String SIGN_TYPE = "signType";

    /**
     * The assigned credential or <code>null</code>.
     */
    @ManyToOne(optional = true)
    @Column(name = "sign_token_id", nullable = true)
    private TokenAuthCredential token;

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_state", nullable = false)
    private SignStateType currentState;

    @Size(min = Named.MIN, max = Named.MAX)
    @Column(name = "sign_sign", nullable = false)
    private String sign;

    @Enumerated(EnumType.STRING)
    @Column(name = "sign_type", nullable = false, updatable = false)
    private SignType signType;

    /**
     * Gets the assigned credential or <code>null</code>.
     *
     * @return
     */
    public TokenAuthCredential getToken()
    {
        return token;
    }

    /**
     * Gets the current state.
     *
     * @return
     */
    public SignStateType getCurrentState()
    {
        return currentState;
    }

    /**
     * Gets the sign.
     *
     * @return
     */
    public String getSign()
    {
        return sign;
    }

    /**
     * Gets the type.
     *
     * @return
     */
    public SignType getSignType()
    {
        return signType;
    }

    @Override
    public boolean isLeaf()
    {
        return true;
    }

    /**
     * Sets the assigned credential.
     *
     * @param token the credential or <code>null</code>
     */
    public void setToken(final TokenAuthCredential token)
    {
        this.token = token;
    }

    /**
     * Sets the current state.
     *
     * @param currentState
     */
    public void setCurrentState(final SignStateType currentState)
    {
        this.currentState = currentState;
    }

    /**
     * Sets the sign.
     *
     * @param sign
     */
    public void setSign(final String sign)
    {
        this.sign = sign;
    }

    /**
     * Sets the type.
     *
     * @param signType
     */
    public void setSignType(final SignType signType)
    {
        this.signType = signType;
    }

    /**
     *
     * @author Carsten Klein <trancesilken@gmail.com>
     * @since 0.0.1
     */
    public static enum SignType
    {
        /**
         * The sign represents a user defined, alpha numeric alias. Users should
         * free to change their alias.
         */
        ALIAS,
        /**
         * The sign represents an email address. Users are free to change their
         * email address used during the sign in process, however, the system
         * must ensure that the email address is kept in sync with other parts
         * of the system, for example email addresses registered with a given
         * account and so on.
         */
        EMAIL,
        /**
         * The sign represents an alpha numeric identifier, for example a domain
         * name, which is defined by the system and cannot be changed by the
         * user.
         */
        ID,
        /**
         * The sign represents a numerical identifier, for example a customer
         * number, which is defined by the system and cannot be changed by the
         * user.
         */
        NID
    }

    /**
     *
     * @author Carsten Klein <trancesilken@gmail.com>
     * @since 0.0.1
     */
    public static enum SignStateType
    {
        /**
         * The sign is rendered invalid.
         */
        INVALID,
        /**
         * The sign was locked due to multiple failed sign in attempts.
         */
        LOCKED,
        /**
         * The sign is valid.
         */
        VALID,
        /**
         * The sign is being validated.
         */
        VALIDATING
    }
}
