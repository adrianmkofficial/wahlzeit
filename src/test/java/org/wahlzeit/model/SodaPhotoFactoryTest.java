package org.wahlzeit.model;
import org.junit.Test;
import static org.junit.Assert.*;

public class SodaPhotoFactoryTest {
    @Test
    public void testGetInstance() {
        SodaPhotoFactory instance = SodaPhotoFactory.getInstance();
        assertNotNull(instance);
        assertEquals(SodaPhotoFactory.class, instance.getClass());
    }

    @Test
    public void testCreateNotNull() {
        SodaPhoto photo = SodaPhotoFactory.getInstance().createPhoto();
        assertNotNull(photo);
    }


    @Test
    public void testCreateId_Parametrized() {
        int id = 22;
        SodaPhoto photo = SodaPhotoFactory.getInstance().createPhoto(new PhotoId(id));
        assertNotNull(photo);
        assertEquals(id, photo.getId().asInt());
    }
}
