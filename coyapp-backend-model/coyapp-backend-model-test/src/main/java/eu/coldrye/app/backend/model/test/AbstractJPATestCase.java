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
package eu.coldrye.app.backend.model.test;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;

/**
 *
 * This is based on the test case templates provided by the hibernate team.
 *
 * @see https://github.com/hibernate/hibernate-test-case-templates
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
public class AbstractJPATestCase
{

    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;
    private final String puname;

    protected AbstractJPATestCase(final String puname)
    {
        this.puname = puname;
    }

    @Before
    public void init()
            throws IOException
    {
        entityManagerFactory = Persistence.createEntityManagerFactory(puname);
        entityManager = entityManagerFactory.createEntityManager();
        loadFixtures();
    }

    @After
    public void destroy()
    {
        entityManager.close();
        entityManager = null;
        entityManagerFactory.close();
        entityManagerFactory = null;
    }

    protected String[] getFixturePaths()
    {
        return new String[]
        {
        };
    }

    protected void loadFixtures()
            throws IOException
    {
        for (String fp : getFixturePaths())
        {
            FixtureLoader.loadFixture(entityManager, fp);
        }
    }
}
