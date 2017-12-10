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

/**
 * A Coordinate represents a point in a three-dimensional cartesian Coordinate system.
 */
public interface Coordinate {

    /**
     * @return new instance of {@link CartesianCoordinate} with attributes of this instance
     * @methodtype conversion
     */
    CartesianCoordinate asCartesianCoordinate();

    /**
     * @return new instance of {@link SphericCoordinate} with attributes of this instance
     * @methodtype conversion
     */
    SphericCoordinate asSphericCoordinate();

    /**
     * @methodtype get
     * Returns cartesian distance of two Coordinates
     */
    double getCartesianDistance(final Coordinate c);

    /**
     * @methodtype get
     * Returns spherical distance of two Coordinates
     */
    double getSphericDistance(final Coordinate c);

    /**
     * Returns the distance between this and the given Coordinate.
     *
     * @param c the Coordinate to measure the distance to
     * @return the distance
     * @methodtype get
     */
    double getDistance(final Coordinate c);

    /**
     * Checks if the given Coordinate is equal in respect to its consisting values.
     *
     * @param c the Coordinate to check for equality
     * @return true if equal, otherwise false
     * @methodtype query
     */
    boolean isEqual(final Coordinate c);
}