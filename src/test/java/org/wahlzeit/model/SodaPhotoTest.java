package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SodaPhotoTest {

    SodaPhoto soda;

    @Before
    public void setUp() {
        soda = new SodaPhoto();
    }

    @Test
    public void testGetAndSetName() {
        String tName = "Dr Pepper";
        soda.setName(tName);
        assertTrue(soda.getName().equals(tName));
    }

    @Test
    public void testGetAndSetManufacturer() {
        String tManufacturer = "Dr Pepper Snapple Group Inc.";
        soda.setManufacturer(tManufacturer);
        assertTrue(soda.getManufacturer().equals(tManufacturer));
    }

    @Test
    public void testGetAndSetCountry() {
        String tCountry = "US";
        soda.setCountry(tCountry);
        assertTrue(soda.getCountry().equals(tCountry));

        tCountry = "DE";
        soda.setCountry(tCountry);
        assertTrue(soda.getCountry().equals(tCountry));
    }

    @Test
    public void testGetAndSetServingSize() {
        Double tServing = 355.0;
        soda.setServing_size_ml(tServing);
        assertEquals(tServing, soda.getServing_size_ml(),  0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalName() {
        soda.setName(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalManufacturer() {
        soda.setManufacturer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalServingSize() {
        soda.setServing_size_ml(-12d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalCountry() {
        soda.setCountry("Deutschland"); // ISO2-Letters used
    }
}
