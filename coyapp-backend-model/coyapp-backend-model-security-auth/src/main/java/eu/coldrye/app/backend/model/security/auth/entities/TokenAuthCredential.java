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

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

/**
 * The class TokenAuthCredential models an entity that holds credential
 * information for an account to be able to authenticate with a given
 * application.
 *
 * The credential information stored herein is valid only in combination with a
 * given sign.
 *
 * Instances of this can be shared by multiple signs, e.g. scenarios where an
 * account can sign in with alternatively the user name, a customer id or the
 * email address.
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@Table(name = "coy_sec_security_auth_credential", uniqueConstraints =
{
   /*
    * each account can have at most one token per type and site realm
    */
   @UniqueConstraint(
           name = "UK_COY_SEC_AUTH_CREDENTIAL_PARENT_ACCOUNT_TOKEN_TYPE",
           columnNames =
           {
               "parent_id", "account_id", "token_type"
           })
})
@DiscriminatorValue("TOKEN")
public class TokenAuthCredential
        extends AuthCredential
{

    public final static String CONFIG = "config";
    public final static String TOKEN_TYPE = "tokenType";
    public final static String TOKEN = "token";

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type", nullable = false, updatable = false)
    private TokenType tokenType;

    @Size(max = 1000)
    @Column(name = "token_config")
    private String config;

    @Size(max = 100)
    @Column(name = "token_token")
    private String token;

    public TokenType getTokenType()
    {
        return tokenType;
    }

    public String getConfig()
    {
        return config;
    }

    public String getToken()
    {
        return token;
    }

    public void setTokenType(final TokenType tokenType)
    {
        this.tokenType = tokenType;
    }

    public void setConfig(final String config)
    {
        this.config = config;
    }

    public void setToken(final String token)
    {
        this.token = token;
    }

    /**
     * TODO:additional credential types: certificate, password_encrypted:w/
     * salt, oauth, ...
     *
     * @author Carsten Klein <trancesilken@gmail.com>
     * @since 0.0.1
     */
    public static enum TokenType
    {
        /**
         * TBD:encrypted password.
         *
         * <ul>
         * <li>config contains salt and algorithm<li>
         * <li>token contains encrypted password</li>
         * </ul>
         *
         * token is encrypted using the site's secret
         */
        PASS,
        /**
         * TBD:ldap authentication
         *
         * <ul>
         * <li>config contains ldap user mapping, salt and algorithm<li>
         * <li>token contains last used encrypted password</li>
         * </ul>
         *
         * token is kept in order for being able to determine whether the user
         * changed his or her ldap account password in case of a security breach
         * oauth token is encrypted using the site's secret
         */
        LDAP,
        /**
         * TBD:oauth authentication
         *
         * <ul>
         * <li>config contains oauth service and user mapping, salt and
         * algorithm</li>
         * <li>token contains encrypted oauth token</li>
         * </ul>
         *
         * token is encrypted using the site's secret
         */
        OAUT
    }

    /**
     *
     *
     * <h2>State Transition Map</h2>
     *
     * <ul>
     * <li>(PASS|new) -> VALID</li>
     * <li>(LDAP|new) -> VALIDATING</li>
     * <li>(OAUT|new) -> VALIDATING</li>
     * <li>(LDAP,OAUT) VALIDATING -> VALID</li>
     * <li>(LDAP,OAUT) VALIDATING -> INVALID</li>
     * <li>(PASS|security breach, sysadm, appadm, system) VALID ->
     * COMPROMISED</li>
     * <li>(OAUT|security breach, sysadm, appadm, system) VALID ->
     * COMPROMISED</li>
     * <li>(LDAP|security breach, sysadm, system) VALID -> COMPROMISED</li>
     * <li>(PASS|password change) COMPROMISED -> VALID</li>
     * <li>(LDAP,OAUT|sysadm) COMPROMISED -> VALIDATING</li>
     * <li>(LDAP|system retries to correct local configuration data) INVALID ->
     * VALIDATING</li>
     * <li>(LDAP|system retries to correct local configuration data, sysadm
     * corrected global configuration data) INVALID -> VALIDATING</li>
     * <li>(OAUT|user corrects configuration data) INVALID -> VALIDATING</li>
     * </ul>
     */
    public static enum TokenStateType
    {
        /**
         * in case of a minor or major security breach
         *
         * user needs to change the credential
         *
         * <ul>
         * <li>OAUT/PASS: token is set to null</li>
         * <li>LDAP: requires password change in directory</li>
         * </ul>
         *
         * in either case description contains an explanation
         *
         * all using user signs will be locked
         *
         * the user state must be set to BLOCKED regardless of any other user
         * credentials and signs
         */
        COMPROMISED,
        /**
         * The credential is considered valid.
         */
        VALID,
        /**
         * user mapping (LDAP, OAUT) is invalid or that the remote service is no
         * longer available or, in case of OAUT, the token is no longer valid
         *
         * in either case description contains an explanation
         *
         * all referring user signs will be locked
         *
         * if all user signs have been locked, then the user state must be set
         * to BLOCKED
         */
        INVALID,
        /**
         * OAUT/LDAP: the credential is being validated, i.e. the user
         * mapping/token
         */
        VALIDATING
    }
}
