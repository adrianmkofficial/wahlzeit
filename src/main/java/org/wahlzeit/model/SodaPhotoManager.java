package org.wahlzeit.model;

import java.util.logging.Logger;

public class SodaPhotoManager extends PhotoManager {

    protected static final SodaPhotoManager instance = new SodaPhotoManager();
    private static final Logger log = Logger.getLogger(SodaPhotoManager.class.getName());

    public SodaPhotoManager() {
        super();
    }
}