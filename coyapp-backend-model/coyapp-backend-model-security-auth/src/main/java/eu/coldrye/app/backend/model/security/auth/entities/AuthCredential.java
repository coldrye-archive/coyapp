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

import eu.coldrye.app.backend.model.security.base.AbstractRealmObject;
import eu.coldrye.app.backend.model.security.base.entities.Account;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 */
@Entity
@Table(name = "coy_sec_auth_credential")
@DiscriminatorColumn(name = "credential_type")
public class AuthCredential
        extends AbstractRealmObject
{

    public final static String ACCOUNT = "account";

    @ManyToOne(optional = false)
    @Column(name = "account_id", nullable = false, updatable = false)
    private Account account;

    /**
     * Gets the user account to which this belongs to.
     *
     * @return
     */
    public Account getAccount()
    {
        return account;
    }

    @Override
    public boolean isLeaf()
    {
        return true;
    }

    /**
     * Sets the user account to which this belongs to.
     *
     * @param account
     */
    public void setAccount(final Account account)
    {
        this.account = account;
    }
}
