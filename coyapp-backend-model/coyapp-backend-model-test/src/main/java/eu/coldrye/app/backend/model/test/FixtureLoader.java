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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
public class FixtureLoader
{

    public static void loadFixture(final Session session, final String path)
            throws IOException
    {
        NativeQuery query = session.createNativeQuery(loadFixtureFromPath(path));
        session.beginTransaction();
        query.executeUpdate();
        session.getTransaction().commit();
    }

    public static void loadFixture(final EntityManager em, final String path)
            throws IOException
    {
        Query query = em.createNativeQuery(loadFixtureFromPath(path));
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
    }

    private static String loadFixtureFromPath(final String path)
            throws IOException
    {
        InputStream stream = ClassLoader.getSystemResourceAsStream(path);
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
                stream)))
        {
            return buffer.lines().collect(Collectors.joining("\n"));
        }
    }
}
