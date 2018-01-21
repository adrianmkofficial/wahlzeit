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
 * Test cases for the SodaType class.
 */
public class SodaTypeTest {

    SodaType st1;
    SodaType st2;
    SodaType st3;

    @Before
    public void setUp() {
        st1 = new SodaType("Dr Pepper", "Dr Pepper Snapple Group", "US");
        st2 = new SodaType("Dr Pepper", "Schweppes Deutschland GmbH", "DE");
        st3 = new SodaType("Coca-Cola", "The Coca-Cola Company", "US");
    }

    @Test
    public void testConstructors() {
        assertNotNull(st1);
        assertNotNull(st2);
        assertNotNull(st3);
    }

    @Test
    public void testGetter() {
        assertTrue(st1.getName().equals("Dr Pepper"));
        assertTrue(st2.getCountry().equals("DE"));
        assertTrue(st3.getManufacturer().equals("The Coca-Cola Company"));
        assertEquals(st3.getSuperType(), st3.getSuperType());
    }


    @Test
    public void testSetter() {
        st2.setCountry("DE");
        assertTrue(st2.getCountry().equals("DE"));
        st1.setManufacturer("Schweppes Deutschland GmbH");
        assertTrue(st1.getManufacturer().equals("Schweppes Deutschland GmbH"));
        st3.setName("Coke");
        assertTrue(st3.getName().equals("Coke"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalCountry() {
        st2.setCountry("Deutschland"); // ISO2-Letters used
    }


    @Test(expected = IllegalArgumentException.class)
    public void testIllegalName() {
        st1.setName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalManufacturer() {
        st2.setManufacturer(null);
    }

}