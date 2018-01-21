/*
 * Copyright (c) 2017 Adrian MK Fürst
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Soda class.
 */
public class SodaTest {

    SodaType st1;
    SodaType st2;
    Soda s1;
    Soda s2;
    Soda s3;


    @Before
    public void setUp() {
        st1 = new SodaType("Dr Pepper", "Dr Pepper Snapple Group", "US");
        st2 = new SodaType("Dr Pepper", "Schweppes Deutschland GmbH", "DE");
        s1 = new Soda("Original", 355d, st1);
        s2 = new Soda("Original", 333d, st2);
        s3 = new Soda("Cherry", 333d, st2); // german dr pepper cherry variant

    }

    @Test
    public void testConstructors() {
        assertNotNull(s1);
        assertNotNull(s2);
        assertNotNull(s3);
        assertNotNull(st1);
        assertNotNull(st2);
    }

    @Test
    public void testGetter() {
        assertEquals("Original", s1.getVariation());
        assertEquals(355d, s1.getServing_size_ml(), 0.0001);
        assertEquals(st1, s1.getSodaType());

        assertEquals("Original", s2.getVariation());
        assertEquals(333d, s2.getServing_size_ml(), 0.0001);
        assertEquals(st2, s2.getSodaType());

        assertEquals("Cherry", s3.getVariation());
        assertEquals(333d, s3.getServing_size_ml(), 0.0001);
        assertEquals(st2, s3.getSodaType());
    }

    @Test
    public void testSetter() {
        s1.setSodaType(st1);
        s1.setVariation("Cherry Vanilla");
        s1.setServing_size_ml(500d);

        assertEquals("Cherry Vanilla", s1.getVariation());
        assertEquals(500d, s1.getServing_size_ml(), 0.0001);
        assertEquals(st1, s1.getSodaType());
    }

    @Test
    public void testEquals() {
        assertNotEquals(s1, s2);
        assertEquals(s1, s1);
        assertEquals(s2.getSodaType(), s3.getSodaType());
        assertNotEquals(s1.hashCode(), s3.hashCode());
        assertEquals(s2.hashCode(), s2.hashCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalServingSize() {
        s3.setServing_size_ml(Double.NaN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalVariation() {
        s2.setVariation(null);
    }

}