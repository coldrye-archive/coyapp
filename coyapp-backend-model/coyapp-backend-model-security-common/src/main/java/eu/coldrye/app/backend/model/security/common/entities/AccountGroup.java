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
package eu.coldrye.app.backend.model.security.common.entities;

import eu.coldrye.app.backend.model.common.AbstractEntity;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TBD account assigned groups. in a multi site environment with one account per
 * site, the realm of both the group and the account must be the same. in an SSO
 * scenario or multi project management site scenario, the realm of the account
 * and the realm of the group will differ.
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@Table(name = "coy_sec_account_group")
@IdClass(AccountGroup.AccountGroupId.class)
@SuppressWarnings("ValidAttributes")
public class AccountGroup
        extends AbstractEntity<AccountGroup.AccountGroupId>
{

    @Id
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false, updatable = false)
    private Account account;

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false, updatable = false)
    private Group group;

    public Group getGroup()
    {
        return group;
    }

    public Account getAccount()
    {
        return account;
    }

    @Override
    public AccountGroupId getId()
    {
        return new AccountGroupId(group, account);
    }

    public void setAccount(final Account account)
    {
        this.account = account;
    }

    public void setGroup(final Group group)
    {
        this.group = group;
    }

    @Override
    public void setId(AccountGroupId id)
    {
        this.group = id.group;
        this.account = id.account;
    }

    public static class AccountGroupId
            implements Serializable
    {

        private final Group group;
        private final Account account;

        public AccountGroupId(final Group group, final Account account)
        {
            this.group = group;
            this.account = account;
        }

        @Override
        public boolean equals(Object o)
        {
            if (!(o instanceof AccountGroupId))
            {
                throw new IllegalArgumentException();
            }
            final AccountGroupId other = (AccountGroupId) o;
            return Objects.equals(group.getId(), other.group.getId())
                   && Objects.equals(account.getId(), other.account.getId());
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(group.getId(), account.getId());
        }
    }
}
