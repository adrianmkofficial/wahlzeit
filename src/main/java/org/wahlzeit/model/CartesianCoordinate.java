package org.wahlzeit.model;

/**
 * A class representing a Coordinate in the Cartesian system.
 */
public class CartesianCoordinate implements Coordinate {

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
        return this;
    }

    @Override
    public double getCartesianDistance(final Coordinate c) {
        CartesianCoordinate tmp_coord = c.asCartesianCoordinate();
        if (c == null) {
            throw new IllegalArgumentException("Coordinate can not be null.");
        }
        double result = Math.sqrt(Math.pow(this.getX() - tmp_coord.getX() , 2) + Math.pow(this.getY() - tmp_coord.getY(), 2) + Math.pow(this.getZ() - tmp_coord.getZ(), 2));
        // check for overflows of result to infinity
        if (Double.isInfinite(result)){
            throw new ArithmeticException("Overflow");
        }
        return result;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        double radius = Math.sqrt(x * x + y * y + z * z);
        double latitude = Math.toDegrees(Math.acos(z / radius));
        double longitude = Math.toDegrees(Math.atan2(y, x));
        return new SphericCoordinate(latitude, longitude, radius);
    }

    @Override
    public double getSphericDistance(final Coordinate c) {
        return this.asSphericCoordinate().getDistance(c);
    }

    /**
     * Returns the Euclidian distance between this and the given CartesianCoordinate.
     *
     * @param c the CartesianCoordinate to measure the distance to
     * @return the Euclidian distance
     */
    @Override
    public double getDistance(final Coordinate c) {
        return getCartesianDistance(c);
    }

    /**
     * Checks if the given Coordinate is equal in respect to its consisting values.
     *
     * @param c the Coordinate to check the equality
     * @return true if equal, otherwise false
     */
    @Override
    public boolean isEqual(final Coordinate c) {
        // check whether given c is null
        if (c == null) {
            return false;
        }
        CartesianCoordinate tmp_coord = c.asCartesianCoordinate();
        // check individual coordinates for equality
        // individual doubles of coordinates are being substracted and then compared to a fixed variable
        // to avoid floating point errors during calculations
        if ((Math.abs(this.getX() - tmp_coord.getX()) < DELTA) && (Math.abs(this.getY() - tmp_coord.getY()) < DELTA) && (Math.abs(this.getZ() - tmp_coord.getZ()) < DELTA)){
            return true;
        }
        // in any other case coordinate is deemed not equal
        else {
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        // check whether given obj is a Coordinate
        if (!(obj instanceof CartesianCoordinate))
            return false;
        // check whether given obj is itself
        if (obj == this)
            return true;
        CartesianCoordinate c = (CartesianCoordinate) obj;
        // in any other case call isEqual method
        return this.isEqual(c);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (Double.doubleToLongBits(x) ^ (Double.doubleToLongBits(x) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(y) ^ (Double.doubleToLongBits(y) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(z) ^ (Double.doubleToLongBits(z) >>> 32));
        return hash;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}