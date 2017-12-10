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
 * A class representing a Coordinate in the Spheric system.
 */
public class SphericCoordinate extends AbstractCoordinate {

    private static final double LONGITUDE_MIN = -180;
    private static final double LONGITUDE_MAX = 180;
    private static final double LATITUDE_MIN = -90;
    private static final double LATITUDE_MAX = 90;
    private static final double EARTH_RADIUS_KM = 6371;

    private double latitude;
    private double longitude;
    private double radius;

    public SphericCoordinate(double latitude, double longitude) {
        this(latitude, longitude, EARTH_RADIUS_KM);
    }

    public SphericCoordinate(double latitude, double longitude, double radius) {
        if (latitude < LATITUDE_MIN || latitude > LATITUDE_MAX) {
            throw new IllegalArgumentException("Latitude is not in the range of [-90, 90]!");
        }
        if (longitude < LONGITUDE_MIN || longitude > LONGITUDE_MAX) {
            throw new IllegalArgumentException("Longitude is not in the range of [-180, 180]!");
        }
        if (radius < 0d) {
            throw new IllegalArgumentException("Radius can not be a negative number!");
        }
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = radius * Math.sin(Math.toRadians(latitude)) * Math.cos(Math.toRadians(longitude));
        double y = radius * Math.sin(Math.toRadians(longitude)) * Math.sin(Math.toRadians(latitude));
        double z = radius * Math.cos(Math.toRadians(latitude));
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        if (latitude < LATITUDE_MIN || latitude > LATITUDE_MAX) {
            throw new IllegalArgumentException("Latitude is not in the range of [-90, 90]!");
        }
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        if (longitude < LONGITUDE_MIN || longitude > LONGITUDE_MAX) {
            throw new IllegalArgumentException("Longitude is not in the range of [-180, 180]!");
        }
        this.longitude = longitude;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius of the coordinate
     */
    public void setRadius(double radius) {
        if (radius < 0d) {
            throw new IllegalArgumentException("Radius can not be a negative number!");
        }
        this.radius = radius;
    }
    @Override
    public int hashCode() {
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

}
