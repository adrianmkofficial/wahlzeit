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
     *
     */
    protected SodaPhotoFactory() {
        // do nothing
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     * Public singleton access method.
     */
    public static synchronized SodaPhotoFactory getInstance() {
        if (instance == null) {
            getLogger().config(LogBuilder.createSystemMessage().addAction("setting generic SodaPhotoFactory").toString());
            setInstance(new SodaPhotoFactory());
        }

        return instance;
    }

    /**
     * @methodtype getMgmtActions
     */
    @Override
    public SodaPhoto createPhoto() {
        return new SodaPhoto();
    }

    /**
     * Creates a new photo with the specified id
     */
    @Override
    public SodaPhoto createPhoto(PhotoId id) {
        return new SodaPhoto(id);
    }

    public SodaPhoto createSodaPhoto(PhotoId photoId, String sodaName, String manufacturer, Double serving_size_ml, String country) {
        return new SodaPhoto(photoId, sodaName, manufacturer, serving_size_ml, country);
    }

    /**
     * Method to set the singleton instance of SodaPhotoFactory.
     */
    protected static synchronized void setInstance(SodaPhotoFactory SodaPhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize SodaPhotoFactory twice");
        }

        instance = SodaPhotoFactory;
    }

    protected static Logger getLogger() {
        return log;
    }
}
