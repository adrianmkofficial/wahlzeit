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

@PatternInstance(
        patternName = "Value Object",
        participants = {
                "AbstractCoordinate"
        }
)
public abstract class AbstractCoordinate implements Coordinate {

    // Delta is a constant used in floating point operations.
    public static final double DELTA = 0.000001;

    /**
     * @return new instance of {@link CartesianCoordinate} with attributes of this instance
     * @methodtype conversion
     */
    @Override
    abstract public CartesianCoordinate asCartesianCoordinate();

    /**
     * @return new instance of {@link SphericCoordinate} with attributes of this instance
     * @methodtype conversion
     */
    @Override
    abstract public SphericCoordinate asSphericCoordinate();

    /**
     * @methodtype conversion
     * Returns cartesian distance of two Coordinates
     */
    @Override
    public double getCartesianDistance(Coordinate c) throws IllegalArgumentException, IllegalStateException {
        assertClassInvariants();
        assertNotNull(c);
        CartesianCoordinate this_coord = this.asCartesianCoordinate();
        CartesianCoordinate other_coord = c.asCartesianCoordinate();
        this_coord.assertClassInvariants();
        other_coord.assertClassInvariants();
        double distance = Math.sqrt(Math.pow(this_coord.getX() - other_coord.getX() , 2) + Math.pow(this_coord.getY() - other_coord.getY(), 2) + Math.pow(this_coord.getZ() - other_coord.getZ(), 2));
        assertValidDouble(distance);
        return distance;
    }

    /**
     * @methodtype conversion
     * Returns spherical distance of two Coordinates
     */
    @Override
    public double getSphericDistance(Coordinate c) throws IllegalArgumentException, IllegalStateException {
        assertClassInvariants();
        assertNotNull(c);
        SphericCoordinate this_coord = this.asSphericCoordinate();
        SphericCoordinate other_coord = c.asSphericCoordinate();
        this_coord.assertClassInvariants();
        other_coord.assertClassInvariants();
        double latDistance = Math.toRadians(other_coord.getLatitude() - this_coord.getLatitude());
        double lonDistance = Math.toRadians(other_coord.getLongitude() - this_coord.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this_coord.getLatitude())) * Math.cos(Math.toRadians(other_coord.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double tmp = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = this_coord.getRadius() * tmp * 1000; // convert to meters
        assertValidDouble(distance);
        return distance;
    }

    /**
     * Returns the (cartesian) distance between this and the given Coordinate.
     *
     * @param c the Coordinate to measure the distance to
     * @return the distance
     * @methodtype query-method
     */
    @Override
    public double getDistance(Coordinate c) throws IllegalArgumentException, IllegalStateException {
        assertClassInvariants();
        assertNotNull(c);
        // always return cartesian distance to have method work consistently
        CartesianCoordinate this_coord = this.asCartesianCoordinate();
        CartesianCoordinate other_coord = ((AbstractCoordinate) c).asCartesianCoordinate();
        return this_coord.getCartesianDistance(other_coord);
    }

    /**
     * Checks if the given Coordinate is equal in respect to its consisting values.
     *
     * @param c the Coordinate to check for equality
     * @return true if equal, otherwise false
     * @methodtype boolean-query
     */
    @Override
    public boolean isEqual(Coordinate c) {
        if(c == this) {
            return true;
        }
        else if(c != null) {
            CartesianCoordinate this_coord = this.asCartesianCoordinate();
            CartesianCoordinate other_coord = ((AbstractCoordinate) c).asCartesianCoordinate();
            return Math.abs(this_coord.getCartesianDistance(other_coord)) < DELTA;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        // check whether given obj is a Coordinate
        if (!(obj instanceof Coordinate))
            return false;
        // check whether given obj is itself
        if (obj == this)
            return true;
        // in any other case call isEqual method
        Coordinate c = (Coordinate) obj;
        return this.isEqual(c);
    }

    /**
     * @methodtype assertion
     */
    protected void assertNotNull(Object o) throws IllegalArgumentException {
        if(o == null) {
            throw new IllegalArgumentException("Illegal null object!");
        }
    }

    /**
     * @methodtype assertion
     */
    protected void assertValidDouble(double d) throws IllegalArgumentException {
        if(Double.isInfinite(d)) {
            throw new IllegalArgumentException("Overflow!");
        }
        if(Double.isNaN(d)) {
            throw new IllegalArgumentException("NaN!");
        }
    }

    /**
     * @methodtype assertion
     */
    protected abstract void assertClassInvariants();
}
