package org.wahlzeit.model;

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
    @Before
    public void setUp() {
        nbg_lorenzkirche = new SphericCoordinate(49.450893,11.078049);
        fra_airport = new SphericCoordinate(50.0379, 8.5622);
        lax_airport = new SphericCoordinate(33.9416, -118.4085);
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
        assertEquals(192185.065, result, 0.5);
        result = fra_airport.getDistance(lax_airport);
        assertEquals(9321318.72, result, 0.5);
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
}
