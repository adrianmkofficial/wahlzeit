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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Test cases for the SphericCoordinate class.
 */
public class SphericCoordinateTest {
    SphericCoordinate nbg_lorenzkirche;
    SphericCoordinate fra_airport;
    SphericCoordinate lax_airport;
    SphericCoordinate john_o_groats;

    @Before
    public void setUp() {
        nbg_lorenzkirche = new SphericCoordinate(49.450893,11.078049);
        fra_airport = new SphericCoordinate(50.0379, 8.5622);
        lax_airport = new SphericCoordinate(33.9416, -118.4085);
        john_o_groats = new SphericCoordinate(58.643889, -3.070000);

    }

    @Test
    public void testIsEqual() {
        Coordinate c = lax_airport;
        SphericCoordinate c_spheric = lax_airport;
        assertTrue(c_spheric.isEqual(c));
    }

    @Test
    public void testIsNotEqual() {
        Coordinate c = lax_airport;
        SphericCoordinate c_spheric = fra_airport;
        assertFalse(c_spheric.isEqual(c));
    }

    @Test
    public void testEquals() {
        Object obj = lax_airport;
        SphericCoordinate c_spheric = lax_airport;
        assertTrue(obj.equals(c_spheric));
    }

    @Test
    public void testGetSphericDistance() {

        double result = nbg_lorenzkirche.getSphericDistance(fra_airport);
        assertEquals(192185.065, result, 0.5);
        result = fra_airport.getSphericDistance(lax_airport);
        assertEquals(9321318.72, result, 0.5);
    }

    @Test
    public void testGetDistance() {
        double result = nbg_lorenzkirche.getDistance(fra_airport);
        assertEquals(223, result, 0.5);
        result = fra_airport.getDistance(lax_airport);
        assertEquals(7669, result, 0.5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetLatitude() {
        SphericCoordinate c_spheric = new SphericCoordinate(1, 1);
        c_spheric.setLatitude(91);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetLongitude() {
        SphericCoordinate c_spheric = new SphericCoordinate(1, 1);
        c_spheric.setLongitude(181);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetRadius() {
        SphericCoordinate c_spheric = new SphericCoordinate(1, 1);
        c_spheric.setRadius(-1);
    }

    @Test
    public void testConversion() {
        CartesianCoordinate c = john_o_groats.asCartesianCoordinate();
        assertEquals(john_o_groats, c.asSphericCoordinate());
        assertTrue(john_o_groats.equals(c.asSphericCoordinate()));
        assertTrue(john_o_groats.isEqual(c.asSphericCoordinate()));
        assertEquals(john_o_groats.getDistance(c.asSphericCoordinate()),0,0.01);

    }
}