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
package eu.coldrye.backend.model.filter;

import eu.coldrye.app.backend.model.filter.Pager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 */
public class PagerTest
{

    private Pager pager;

    @Before
    public void before()
    {
        pager = new Pager(55);
    }

    @Test
    public void testGetPageCount()
    {
        Assert.assertEquals(6, pager.getPageCount());
    }

    @Test
    public void testGetPageCountLimit20()
    {
        Assert.assertEquals(3, pager.setLimit(20).getPageCount());
    }

    @Test
    public void testGotoOutOfBoundsPage10()
    {
        Assert.assertEquals(50, pager.goToPage(10).getOffset());
    }

    @Test
    public void testGotoPage1()
    {
        Assert.assertEquals(0, pager.goToPage(1).getOffset());
    }

    @Test
    public void testGotoPage3()
    {
        Assert.assertEquals(20, pager.goToPage(3).getOffset());
    }

    @Test
    public void testLastPage()
    {
        Assert.assertEquals(50, pager.lastPage().getOffset());
    }

    @Test
    public void testNextPage()
    {
        Assert.assertEquals(10, pager.nextPage().getOffset());
    }

    @Test
    public void testNextPageAfterLastPage()
    {
        Assert.assertEquals(50, pager.lastPage().nextPage().getOffset());
    }

    @Test
    public void testNextPageLargeSkipOnFirstPage()
    {
        Assert.assertEquals(50, pager.firstPage().skipPages(10).getOffset());
    }

    @Test
    public void testPreviousPageBeforeFirstPage()
    {
        Assert.assertEquals(0, pager.firstPage().previousPage().getOffset());
    }

    @Test
    public void testPreviousPageLargeSkipOnLastPage()
    {
        Assert.assertEquals(0, pager.lastPage().skipPages(-10).getOffset());
    }

    @Test
    public void testSetLimit20Normalization()
    {
        Assert.assertEquals(20, pager.goToPage(4).setLimit(20).getOffset());
    }

    @Test
    public void testSetOffsetNormalization()
    {
        Assert.assertEquals(50, pager.setOffset(52).getOffset());
    }
}
