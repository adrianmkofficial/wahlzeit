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
 * A class representing a Coordinate in the Cartesian system.
 */
public class CartesianCoordinate extends AbstractCoordinate {

    private final double x;
    private final double y;
    private final double z;

    private static HashMap<Integer, CartesianCoordinate> cartesianCoordinateHashMap = new HashMap<>();

    private CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static CartesianCoordinate getCoordinate(double x, double y, double z) {
        final int hash = createHashCode(x, y, z);
        if (!cartesianCoordinateHashMap.containsKey(hash)) {
            CartesianCoordinate coord = new CartesianCoordinate(x, y, z);
            cartesianCoordinateHashMap.put(hash, coord);
            return coord;
        }
        else
        {
            return cartesianCoordinateHashMap.get(hash);
        }
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() throws IllegalStateException {
        assertClassInvariants();
        return new CartesianCoordinate(this.x, this.y, this.z);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() throws IllegalArgumentException, IllegalStateException {
        assertClassInvariants();
        double radius = Math.sqrt(x * x + y * y + z * z);
        double latitude = Math.toDegrees(Math.acos(z / radius));
        double longitude = Math.toDegrees(Math.atan2(y, x));
        assertValidDouble(radius);
        assertValidDouble(latitude);
        assertValidDouble(longitude);
        return SphericCoordinate.getCoordinate(latitude, longitude, radius);
    }

    public double getX() {
        return x;
    }

    public CartesianCoordinate setX(double x) throws IllegalArgumentException, IllegalStateException {
        assertClassInvariants();
        assertValidDouble(x);
        return new CartesianCoordinate(x,this.y, this.z);
    }

    public double getY() {
        return y;
    }

    public CartesianCoordinate setY(double y) throws IllegalArgumentException, IllegalStateException {
        assertClassInvariants();
        assertValidDouble(y);
        return new CartesianCoordinate(this.x,y, this.z);
    }

    public double getZ() {
        return z;
    }

    public CartesianCoordinate setZ(double z) throws IllegalArgumentException, IllegalStateException {
        assertClassInvariants();
        assertValidDouble(z);
        return new CartesianCoordinate(this.x,this.y, z);
    }

    public static int createHashCode(double x, double y, double z) {
        int hash = 7;
        hash = 37 * hash + (int) (Double.doubleToLongBits(x) ^ (Double.doubleToLongBits(x) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(y) ^ (Double.doubleToLongBits(y) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(z) ^ (Double.doubleToLongBits(z) >>> 32));
        return hash;
    }

    @Override
    public int hashCode() {
        return createHashCode(this.x, this.y, this.z);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }

    @Override
    protected void assertClassInvariants() {
        try {
            assertValidDouble(x);
            assertValidDouble(y);
            assertValidDouble(z);
        } catch(Exception e) {
            throw new IllegalStateException();
        }
    }
}
