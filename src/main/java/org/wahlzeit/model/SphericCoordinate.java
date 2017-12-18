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

import java.util.HashMap;


/**
 * A class representing a Coordinate in the Spheric system.
 */
public class SphericCoordinate extends AbstractCoordinate {

    private static final double LONGITUDE_MIN = -180;
    private static final double LONGITUDE_MAX = 180;
    private static final double LATITUDE_MIN = -90;
    private static final double LATITUDE_MAX = 90;
    private static final double EARTH_RADIUS_KM = 6371;

    private final double latitude;
    private final double longitude;
    private final double radius;

    private static HashMap<Integer, SphericCoordinate> sphericCoordinateHashMap = new HashMap<Integer, SphericCoordinate>();

    private SphericCoordinate(double latitude, double longitude) {
        this(latitude, longitude, EARTH_RADIUS_KM);
    }

    private SphericCoordinate(double latitude, double longitude, double radius) {
        assertValidLatitude(latitude);
        assertValidLongitude(longitude);
        assertValidRadius(radius);
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
        assertClassInvariants();
    }

    public static SphericCoordinate getCoordinate(double latitude, double longitude) {
        return getCoordinate(latitude, longitude, EARTH_RADIUS_KM);
    }

    public static SphericCoordinate getCoordinate(double latitude, double longitude, double radius) {
        final int hash = createHashCode(latitude, longitude, radius);
        if (!sphericCoordinateHashMap.containsKey(hash)) {
            SphericCoordinate coord = new SphericCoordinate(latitude, longitude, radius);
            sphericCoordinateHashMap.put(hash, coord);
            return coord;
        }
        else
        {
            return sphericCoordinateHashMap.get(hash);
        }
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() throws IllegalStateException, IllegalArgumentException {
        assertClassInvariants();
        double x = radius * Math.sin(Math.toRadians(latitude)) * Math.cos(Math.toRadians(longitude));
        double y = radius * Math.sin(Math.toRadians(longitude)) * Math.sin(Math.toRadians(latitude));
        double z = radius * Math.cos(Math.toRadians(latitude));
        assertValidDouble(x);
        assertValidDouble(y);
        assertValidDouble(z);
        return CartesianCoordinate.getCoordinate(x, y, z);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return new SphericCoordinate(this.latitude, this.longitude, this.radius);
    }

    /**
     * @return the latitude
     * @methodtype get
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     * @methodtype set
     */
    public SphericCoordinate setLatitude(double latitude) throws IllegalArgumentException, IllegalStateException{
        assertClassInvariants();
        assertValidLatitude(latitude);
        return new SphericCoordinate(latitude, this.longitude, this.radius);
    }

    /**
     * @return the longitude
     * @methodtype get
     */
    public double getLongitude()  {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     * @methodtype set
     */
    public SphericCoordinate setLongitude(double longitude) throws IllegalArgumentException, IllegalStateException {
        assertClassInvariants();
        assertValidLongitude(longitude);
        return new SphericCoordinate(this.latitude, longitude, this.radius);
    }

    /**
     * @return the radius
     * @methodtype get
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius of the coordinate
     * @methodtype set
     */
    public SphericCoordinate setRadius(double radius) throws IllegalArgumentException, IllegalStateException {
        assertClassInvariants();
        assertValidRadius(radius);
        return new SphericCoordinate(this.latitude, this.longitude, radius);
    }

    @Override
    public int hashCode() {
        return createHashCode(this.latitude, this.longitude, this.radius);

    }

    private static int createHashCode(double latitude, double longitude, double radius) {
        int hash = 2003;
        hash = 37 * hash + (int) (Double.doubleToLongBits(latitude)
                ^ (Double.doubleToLongBits(latitude) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(longitude)
                ^ (Double.doubleToLongBits(longitude) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(radius)
                ^ (Double.doubleToLongBits(radius) >>> 32));
        return hash;
    }

        @Override
    public String toString() {
        return "(" + latitude + ", " + longitude + ")";
    }

    /**
     * @methodtype assertion
     */
    @Override
    protected void assertClassInvariants() throws IllegalStateException{
        try {
            assertValidLongitude(this.longitude);
            assertValidLatitude(this.latitude);
            assertValidRadius(this.radius);
        } catch(IllegalArgumentException e) {
            throw new IllegalStateException();
        }
    }

    /**
     * @methodtype assertion
     */
    private void assertValidLongitude(double longitude) throws IllegalArgumentException {
        if (longitude < LONGITUDE_MIN || longitude > LONGITUDE_MAX) {
            throw new IllegalArgumentException("Longitude is not in the range of [-180, 180]!");
        }
    }

    /**
     * @methodtype assertion
     */
    private void assertValidLatitude(double latitude) throws IllegalArgumentException {
        if (latitude < LATITUDE_MIN || latitude > LATITUDE_MAX) {
            throw new IllegalArgumentException("Latitude is not in the range of [-90, 90]!");
        }
    }

    /**
     * @methodtype assertion
     */
    private void assertValidRadius(double radius) throws IllegalArgumentException {
        if (radius < 0d) {
            throw new IllegalArgumentException("Radius can not be a negative number!");
        }
    }


}
