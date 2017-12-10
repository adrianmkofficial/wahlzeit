package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test cases for the Location class.
 */
public class LocationTest {
    Location l_default;
    Location l_parameterized;
    double x;
    double y;
    double z;
    @Before
    public void setUp() {
        x = 1.25;
        y = 3.75;
        z = 8.0;
        l_parameterized = new Location(new CartesianCoordinate(x,y,z));
    }

    @Test
    public void testConstructor() {
        assertNotEquals(new CartesianCoordinate(x,y,z), l_default.getCoordinate());

    }

    @Test
    public void testConstructor_Parametrized() {
        assertEquals(new CartesianCoordinate(x,y,z), l_parameterized.getCoordinate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_Null() {
        Location tmp = new Location(null);
    }

    @Test
    public void testSetAndGet() {
        l_default.setCoordinate(new CartesianCoordinate(x,y,z));
        assertEquals(new CartesianCoordinate(x,y,z), l_default.getCoordinate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAndGet_Null() {
        l_default.setCoordinate(null);
    }
}
