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
package eu.coldrye.app.backend.model.filter;

/**
 * The class Pager models an immutable class that allows one to paginate the
 * result list returned by an entity query by both limiting and offsetting that
 * list.
 *
 * This is also meant for use in the view controller logic, where one has an
 * items per page limit and a page number. In order to apply this in a view
 * controller's context, see below use cases.
 *
 * <h2>CAVEATS</h2>
 *
 * Since pagination requires read only operations that do not lock the current
 * state of the underlying data store, extensive changes to that store, i.e.
 * creations, deletions and purges, will have a negative impact on the results
 * returned at the time the pager is applied to the query, since the number of
 * items is determined before hand, even when it is being executed within a
 * transactional context. However, and since the number of logic operations
 * involved is rather low, such race conditions might never occur with low
 * profile applications. High profile applications, however, should take extra
 * care. These race conditions may also arise on highly congested systems, so
 * beware.
 *
 * <h2>Use case: User makes changes to the filter</h2>
 *
 * Here the user must lose the current page context and will start over,
 * beginning at the first page.
 *
 * <code>
 * // define filter;
 * int count = DAO.setFilter(...).count[By...]();
 * Pager pager = new Pager(count, currentUserDefinedItemsPerPageLimit);
 * // this will adjust the current page to the first page
 * DAO.setPager(pager);
 * List results = DAO.find[By...](...);
 * int currentPage = pager.getCurrentPage();
 * </code>
 *
 * <h2>Use case: User changes the items per page limit</h2>
 *
 * In effect, the current page will be adjusted so that the user can see (part
 * of) the same results as from a previous query, since changing the items per
 * page limit naturally will either increase or decrease the number of available
 * pages.
 *
 * Not to mention the effect of concurrent other sessions or even the same user
 * adding entities to or removing entities from the underlying data store and
 * thus moving the window represented by the currently selected page and the
 * items per page limit in effect.
 *
 * <code>
 * // define filter;
 * int count = DAO.setFilter(...).count[By...]();
 * Pager pager = new Pager(count, previousUserDefinedItemsPerPageLimit);
 * // this will adjust the current page to the newly defined limit
 * DAO.setPager(pager.goToPage(previousUserDefinedCurrentPage)
 *                   .setLimit(newUserDefinedItemsPerPageLimit));
 * List results = DAO.find[By...](...);
 * int currentPage = pager.getCurrentPage();
 * </code>
 *
 * <h2>Use case: User navigates to the first page</h2>
 *
 * <code>
 * // define filter;
 * int count = DAO.setFilter(...).count[By...]();
 * Pager pager = new Pager(count, currentUserDefinedItemsPerPageLimit);
 * DAO.setPager(pager[.firstPage()]);
 * List results = DAO.find[By...](...);
 * int currentPage = pager.getCurrentPage();
 * </code>
 *
 * <h2>Use case: User navigates to the last page</h2>
 *
 * <code>
 * // define filter;
 * int count = DAO.setFilter(...).count[By...]();
 * Pager pager = new Pager(count, currentUserDefinedItemsPerPageLimit);
 * DAO.setPager(pager.lastPage());
 * List results = DAO.find[By...](...);
 * int currentPage = pager.getCurrentPage();
 * </code>
 *
 * <h2>Use case: User navigates to the previous page</h2>
 *
 * <code>
 * // define filter;
 * int count = DAO.setFilter(...).count[By...]();
 * Pager pager = new Pager(count, currentUserDefinedItemsPerPageLimit);
 * DAO.setPager(pager.goToPage(currentUserDefinedCurrentPage).previousPage());
 * List results = DAO.find[By...](...);
 * int currentPage = pager.getCurrentPage();
 * </code>
 *
 * <h2>Use case: User navigates to the next page</h2>
 *
 * <code>
 * // define filter;
 * int count = DAO.setFilter(...).count[By...]();
 * Pager pager = new Pager(count, currentUserDefinedItemsPerPageLimit);
 * DAO.setPager(pager.goToPage(currentUserDefinedCurrentPage).nextPage());
 * List results = DAO.find[By...](...);
 * int currentPage = pager.getCurrentPage();
 * </code>
 *
 * <h2>Use case: User navigates to a specific page</h2>
 *
 * <code>
 * // define filter;
 * int count = DAO.setFilter(...).count[By...]();
 * Pager pager = new Pager(count, currentUserDefinedItemsPerPageLimit);
 * DAO.setPager(pager.goToPage(userDefinedPage));
 * List results = DAO.find[By...](...);
 * int currentPage = pager.getCurrentPage();
 * </code>
 *
 * <h2>Use case: User navigates to a specific offset</h2>
 *
 * The user defined offset will be normalized so that it will be based on a
 * window defined by the currentUserDefinedItemsPerPageLimit and where the user
 * defined offset is part of that window.
 *
 * <code>
 * // define filter;
 * int count = DAO.setFilter(...).count[By...]();
 * Pager pager = new Pager(count, currentUserDefinedItemsPerPageLimit);
 * DAO.setPager(pager.setOffset(userDefinedOffset));
 * List results = DAO.find[By...](...);
 * int currentPage = pager.getCurrentPage();
 * </code>
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
// TODO:refactor to filter
public final class Pager
{

    public static int DEFAULT_LIMIT = 10;
    public static int DEFAULT_OFFSET = 0;

    private int count;
    private int limit = DEFAULT_LIMIT;
    private int offset = DEFAULT_OFFSET;

    public Pager(final int count)
    {
        this(count, DEFAULT_LIMIT);
    }

    public Pager(final int count, final int limit)
    {
        setCount(count);
        this.limit = limit;
    }

    private Pager(final Pager pager)
    {
        this(pager.count, pager.limit);
        this.offset = pager.offset;
    }

    public Pager firstPage()
    {
        return new Pager(this);
    }

    public int getCount()
    {
        return count;
    }

    public int getCurrentPage()
    {
        return (int) Math.ceil((float) offset / limit);
    }

    /**
     * Gets the limit or <code>0</code>.
     *
     * @return
     */
    public int getLimit()
    {
        return limit;
    }

    /**
     * Gets the offset.
     *
     * @return
     */
    public int getOffset()
    {
        return offset;
    }

    public int getPageCount()
    {
        return (int) Math.ceil((float) count / limit);
    }

    public Pager goToPage(final int page)
    {
        if (page < 1)
        {
            throw new IllegalArgumentException(
                    "page must be a positive integer > 0.");
        }
        return setOffset((page - 1) * limit);
    }

    public Pager lastPage()
    {
        return setOffset(Math.max((getPageCount() - 1) * limit, DEFAULT_OFFSET));
    }

    /**
     * Returns a pager for the next page to be used in subsequent entity
     * queries.
     *
     * @return
     */
    public Pager nextPage()
    {
        return skipPages(1);
    }

    /**
     * Returns a pager for the previous page to be used in subsequent entity
     * queries.
     *
     * @return
     */
    public Pager previousPage()
    {
        return skipPages(-1);
    }

    /**
     * Sets the limit of maximum number of entities returned by a given entity
     * query.
     *
     * @param limit the limit or <code>0</code>
     * @return
     */
    public Pager setLimit(final int limit)
    {
        if (limit <= 0)
        {
            throw new IllegalArgumentException(
                    "limit must be a positive integer > 0");
        }
        final Pager result = new Pager(this);
        result.limit = limit;

        return normalize(result);
    }

    /**
     * Sets the offset.
     *
     * @param offset
     * @return
     */
    public Pager setOffset(final int offset)
    {
        if (offset < 0)
        {
            throw new IllegalArgumentException(
                    "offset must be a positive integer >= 0.");
        }

        final Pager result = new Pager(this);
        result.offset = offset;

        return normalize(result);
    }

    public Pager skipPages(final int skip)
    {
        Pager result;

        if (skip < 0)
        {
            result = setOffset(Math.max(offset + limit * skip, DEFAULT_OFFSET));
        }
        else
        {
            result = setOffset(Math.min(offset + limit * skip,
                                        (getPageCount() - 1) * limit));
        }

        return result;
    }

    private Pager normalize(final Pager pager)
    {
        // normalize offset on page boundary
        pager.offset = ((int) Math.floor(pager.offset / pager.limit)) * pager.limit;
        pager.offset = Math.min(pager.offset,
                                (pager.getPageCount() - 1) * pager.limit);

        return pager;
    }

    private void setCount(int count)
    {
        if (count < 0)
        {
            throw new IllegalArgumentException(
                    "count must be a positive integer >= 0.");
        }
        this.count = count;
    }
}
