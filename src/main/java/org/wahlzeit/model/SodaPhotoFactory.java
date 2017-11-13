package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;

import java.util.logging.Logger;

public class SodaPhotoFactory extends  PhotoFactory{

    private static final Logger log = Logger.getLogger(SodaPhotoFactory.class.getName());

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static SodaPhotoFactory instance = null;

    /**
     * Public singleton access method.
     *
     * @return the instance of the SodaPhotoFactory
     */
    public static synchronized SodaPhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic SodaPhotoFactory").toString());
            setInstance(new SodaPhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of PhotoFactory.
     *
     * @param SodaPhotoFactory the instance to set
     */
    protected static synchronized void setInstance(SodaPhotoFactory SodaPhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize SodaPhotoFactory twice");
        }

        instance = SodaPhotoFactory;
    }

    /**
     * @methodtype factory
     */
    public SodaPhoto createPhoto() {
        return new SodaPhoto();
    }

    /**
     * Creates a new photo with the specified id.
     */
    public SodaPhoto createPhoto(PhotoId id) {
        return new SodaPhoto(id);
    }
}
