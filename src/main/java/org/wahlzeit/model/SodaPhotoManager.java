package org.wahlzeit.model;
import java.util.logging.Logger;

public class SodaPhotoManager extends PhotoManager {
    private static final Logger log = Logger.getLogger(SodaPhotoManager.class.getName());
    /**
     *
     */
    protected static final SodaPhotoManager instance = new SodaPhotoManager();

    /**
     *
     */
    public SodaPhotoManager() {
        photoTagCollector = SodaPhotoFactory.getInstance().createPhotoTagCollector();
    }

    /**
     *
     */
    @Override
    public final Photo getPhoto(PhotoId id) {
        return getInstance().getPhotoFromId(id);
    }

    /**
     *
     */
    @Override
    public Photo getPhotoFromId(PhotoId id) {
        if (id == null) {
            return null;
        }

        Photo result = doGetPhotoFromId(id);

        if (result == null) {
            result = SodaPhotoFactory.getInstance().loadPhoto(id);
            if (result != null) {
                doAddPhoto(result);
            }
        }

        return result;
    }

    public final SodaPhoto getSodaPhoto(PhotoId id) {
        return getInstance().getSodaPhotoFromId(id);
    }

    public SodaPhoto getSodaPhotoFromId(PhotoId id) {
        Photo SodaPhoto = getInstance().getPhoto(id);

        return SodaPhoto instanceof SodaPhoto ? (SodaPhoto) SodaPhoto : null;
    }

    /**
     *
     */
    public static final SodaPhotoManager getInstance() {
        return instance;
    }
}
