package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test cases for the Coordinate class.
 */
public class CoordinateTest {
    Coordinate c_parametrized;
    Coordinate c_default;
    Coordinate c_negative;
    double x;
    double y;
    double z;

    @Before
    public void setUp() {
        x = 1.25;
        y = 3.75;
        z = 8.0;
        c_default = new Coordinate();
        c_parametrized = new Coordinate(x,y,z);
        c_negative = new Coordinate(-x,-y,-z);

    }

    @Test
    public void testConstructor_ParametrizedCoords() {
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
    public void testConstructor_NegativeCoords() {
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
}
