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
 * A class representing a Coordinate in the Cartesian system.
 */
public class CartesianCoordinate extends AbstractCoordinate {

    private double x;
    private double y;
    private double z;

    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();
        return this;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariants();
        double radius = Math.sqrt(x * x + y * y + z * z);
        double latitude = Math.toDegrees(Math.acos(z / radius));
        double longitude = Math.toDegrees(Math.atan2(y, x));
        assertValidDouble(radius);
        assertValidDouble(latitude);
        assertValidDouble(longitude);
        return new SphericCoordinate(latitude, longitude, radius);
    }

    public double getX() {
        assertClassInvariants();
        return x;
    }

    public void setX(double x) {
        assertClassInvariants();
        assertValidDouble(x);
        this.x = x;
        assertClassInvariants();
    }

    public double getY() {
        assertClassInvariants();
        return y;
    }

    public void setY(double y) {
        assertClassInvariants();
        assertValidDouble(y);
        this.y = y;
        assertClassInvariants();
    }

    public double getZ() {
        assertClassInvariants();
        return z;
    }

    public void setZ(double z) {
        assertClassInvariants();
        assertValidDouble(z);
        this.z = z;
        assertClassInvariants();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (Double.doubleToLongBits(x) ^ (Double.doubleToLongBits(x) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(y) ^ (Double.doubleToLongBits(y) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(z) ^ (Double.doubleToLongBits(z) >>> 32));
        return hash;
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
