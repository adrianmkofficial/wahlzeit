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

public abstract class AbstractCoordinate implements Coordinate {

    // Delta is a constant used in floating point operations.
    public static final double DELTA = 0.000001;

    @Override
    abstract public CartesianCoordinate asCartesianCoordinate();

    @Override
    abstract public SphericCoordinate asSphericCoordinate();

    //getCartDistance & SphericDistance in abstract
    @Override
    public double getCartesianDistance(Coordinate c) {
        CartesianCoordinate this_coord = this.asCartesianCoordinate();
        CartesianCoordinate other_coord = c.asCartesianCoordinate();
        if (c == null) {
            throw new IllegalArgumentException("Coordinate can not be null.");
        }
        double result = Math.sqrt(Math.pow(this_coord.getX() - other_coord.getX() , 2) + Math.pow(this_coord.getY() - other_coord.getY(), 2) + Math.pow(this_coord.getZ() - other_coord.getZ(), 2));
        // check for overflows of result to infinity
        if (Double.isInfinite(result)){
            throw new ArithmeticException("Overflow");
        }
        return result;
    }

    @Override
    public double getSphericDistance(Coordinate c) {
        SphericCoordinate this_coord = this.asSphericCoordinate();
        SphericCoordinate other_coord = c.asSphericCoordinate();
        double latDistance = Math.toRadians(other_coord.getLatitude() - this_coord.getLatitude());
        double lonDistance = Math.toRadians(other_coord.getLongitude() - this_coord.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this_coord.getLatitude())) * Math.cos(Math.toRadians(other_coord.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double tmp = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = this_coord.getRadius() * tmp * 1000; // convert to meters
        return distance;
    }
    // todo add methodtype and shit

    @Override
    public double getDistance(Coordinate c) {
        // always return cartesian distance to have method work consistently
        CartesianCoordinate this_coord = this.asCartesianCoordinate();
        CartesianCoordinate other_coord = ((AbstractCoordinate) c).asCartesianCoordinate();
        return this_coord.getCartesianDistance(other_coord);
    }

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
}
