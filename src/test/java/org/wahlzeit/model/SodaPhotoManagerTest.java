package org.wahlzeit.model;
import org.junit.Test;
import static org.junit.Assert.*;


public class SodaPhotoManagerTest {
    @Test
    public void testGetInstance() {
        SodaPhotoManager result = SodaPhotoManager.getInstance();
        assertNotNull(result);
        assertEquals(SodaPhotoManager.class, result.getClass());
    }
}
