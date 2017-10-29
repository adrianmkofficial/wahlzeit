package org.wahlzeit.model;

/**
 * A coordinate represents a point in a three-dimensional cartesian coordinate system.
 */
public class Coordinate {

    /**
     *
     */
    private double x;
    private double y;
    private double z;

    /**
     * @methodtype constructor
     */
    public Coordinate() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    /**
     * @methodtype constructor
     */
    public Coordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     * @methodtype set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    /**
     * @methodtype set
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * @methodtype get
     * Returns euclidean distance between two coordinates
     */
    public double getDistance(Coordinate c) {
        if (c == null) {
            throw new IllegalArgumentException("Coordinate can not be null.");
        }
        return Math.sqrt(Math.pow(this.getX() - c.getX() , 2) + Math.pow(this.getY() - c.getY(), 2) + Math.pow(this.getZ() - c.getZ(), 2));
    }

    /**
     * @methodtype get
     * Returns true for two equal coordinates
     */
    public boolean isEqual(Coordinate c) {
        // check whether given c is null
        if (c == null) {
            return false;
        }
        // check individual coordinates for equality
        if ((this.getX() == c.getX()) && (this.getY() == c.getY()) && (this.getZ() == c.getZ())) {
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
        if (!(obj instanceof Coordinate))
            return false;
        // check whether given obj is itself
        if (obj == this)
            return true;
        Coordinate c = (Coordinate) obj;
        // in any other case call isEqual method
        return this.isEqual(c);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}