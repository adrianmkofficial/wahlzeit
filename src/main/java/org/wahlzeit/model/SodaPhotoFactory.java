package org.wahlzeit.model;
import java.util.logging.Logger;
import org.wahlzeit.services.LogBuilder;

public class SodaPhotoFactory extends PhotoFactory {
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
     * Method to set the singleton instance of SodaPhotoFactory.
     *
     * @param sodaPhotoFactory the instance to set
     */
    protected static synchronized void setInstance(SodaPhotoFactory sodaPhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize SodaPhotoFactory twice");
        }

        instance = sodaPhotoFactory;
    }

    /**
     * Creates a new photo.
     *
     * @methodtype factory
     */
    public SodaPhoto createPhoto() {
        return new SodaPhoto();
    }

    /**
     * Creates a new soda photo with the specified id.
     *
     * @methodtype factory
     */
    public SodaPhoto createPhoto(PhotoId id) {
        return new SodaPhoto(id);
    }

    public SodaPhoto createSodaPhoto(PhotoId photoId, String sodaName, String manufacturer, Double serving_size_ml, String country) {
        return new SodaPhoto(photoId, sodaName, manufacturer, serving_size_ml, country);
    }

    /**
     *
     */
    protected SodaPhotoFactory() {
        // do nothing
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance();
    }

    protected static Logger getLogger() {
        return log;
    }
}
