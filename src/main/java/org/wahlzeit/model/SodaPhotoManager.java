/*
 * Copyright (c) 2017 Adrian MK Fürst
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;
import java.util.logging.Logger;

public class SodaPhotoManager extends PhotoManager {

    private static final Logger log = Logger.getLogger(SodaPhotoManager.class.getName());

    protected static final SodaPhotoManager instance = new SodaPhotoManager();
    
    /**
     * @methodtype command
     * @methodproperty primitive
     */
    protected void doAddSodaPhoto(SodaPhoto mySodaPhoto) {
        super.doAddPhoto(mySodaPhoto);
    }

    /**
     * @methodtype command
     * @methodproperty primitive
     */
    @Override
    protected void doAddPhoto(Photo myPhoto) {
        if(!(myPhoto instanceof SodaPhoto)) {
            throw new IllegalArgumentException("Expected SodaPhoto");
        }
        doAddSodaPhoto((SodaPhoto) myPhoto);
    }

    /**
     * @methodtype getter
     */
    public SodaPhoto getSodaPhoto(PhotoId id) {
        return (SodaPhoto) super.getPhoto(id);
    }
    /**
     * Public singleton access method
     *
     * @methodtype getter
     */
    public static final SodaPhotoManager getInstance() {
        return (SodaPhotoManager)instance;
    }
}
