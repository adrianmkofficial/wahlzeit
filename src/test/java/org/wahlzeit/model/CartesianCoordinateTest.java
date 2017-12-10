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

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test cases for the CartesianCoordinate class.
 */
public class CartesianCoordinateTest {
    CartesianCoordinate c_parametrized;
    CartesianCoordinate c_default;
    CartesianCoordinate c_negative;
    double x;
    double y;
    double z;

    @Before
    public void setUp() {
        x = 1.25;
        y = 3.75;
        z = 8.0;
        c_default = new CartesianCoordinate(0,0,0);
        c_parametrized = new CartesianCoordinate(x,y,z);
        c_negative = new CartesianCoordinate(-x,-y,-z);

    }

    @Test
    public void testConstructor_ParametrizedCartesianCoordinate() {
        assertEquals(x, c_parametrized.getX(), 0.0001);
        assertEquals(y, c_parametrized.getY(), 0.0001);
        assertEquals(z, c_parametrized.getZ(), 0.0001);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, c_default.getX(), 0.0001);
        assertEquals(0, c_default.getY(), 0.0001);
        assertEquals(0, c_default.getZ(), 0.0001);
    }

    @Test
    public void testConstructor_NegativeCartesianCoordinate() {
        assertEquals(-x, c_negative.getX(), 0.0001);
        assertEquals(-y, c_negative.getY(), 0.0001);
        assertEquals(-z, c_negative.getZ(), 0.0001);
    }

    @Test
    public void testSetAndGet() {
        // switch things up for the sake of it
        c_parametrized.setX(z*2);
        c_parametrized.setY(x*2);
        c_parametrized.setZ(y*2);
        assertEquals(z*2, c_parametrized.getX(), 0.0001);
        assertEquals(x*2, c_parametrized.getY(), 0.0001);
        assertEquals(y*2, c_parametrized.getZ(), 0.0001);
    }

    @Test
    public void testGetDistance() {
        // same coordinates
        assertEquals(0.0, c_default.getDistance(c_default), 0.0001);
        assertEquals(0.0, c_parametrized.getDistance(c_parametrized), 0.0001);
        assertEquals(0.0, c_negative.getDistance(c_negative), 0.0001);

        // distance between given test coordinates
        assertEquals(8.9232, c_parametrized.getDistance(c_default), 0.0001);
        assertEquals(8.9232, c_default.getDistance(c_parametrized), 0.0001);
        assertEquals(17.8465, c_parametrized.getDistance(c_negative), 0.0001);
        assertEquals(17.8465, c_negative.getDistance(c_parametrized), 0.0001);
        assertEquals(8.9232, c_negative.getDistance(c_default), 0.0001);
        assertEquals(8.9232, c_default.getDistance(c_negative), 0.0001);
    }

    @Test
    public void testIsEqualNull() {
        assertFalse(c_default.isEqual(null));
        assertFalse(c_negative.isEqual(null));
        assertFalse(c_parametrized.isEqual(null));
    }

    @Test
    public void testIsEqualSelf() {
        assertTrue(c_default.isEqual(c_default));
        assertTrue(c_negative.isEqual(c_negative));
        assertTrue(c_parametrized.isEqual(c_parametrized));
    }

    @Test
    public void testIsEqualOther() {
        assertFalse(c_default.isEqual(c_negative));
        assertFalse(c_default.isEqual(c_parametrized));
        assertFalse(c_negative.isEqual(c_default));
        assertFalse(c_negative.isEqual(c_parametrized));
        assertFalse(c_parametrized.isEqual(c_default));
        assertFalse(c_parametrized.isEqual(c_negative));
    }

    @Test
    public void testConversion() {
        CartesianCoordinate c = new CartesianCoordinate(5433, -291.4, 3315);
        assertEquals(c, c.asSphericCoordinate().asCartesianCoordinate());
        assertTrue(c.equals(c.asSphericCoordinate().asCartesianCoordinate()));
        assertEquals(c.getDistance(c.asSphericCoordinate().asCartesianCoordinate()),0,0.01);
    }
}