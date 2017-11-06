package org.wahlzeit.model;

/**
 * A coordinate represents a point in a three-dimensional cartesian coordinate system.
 */
public class Coordinate {

    // Delta is a constant used in floating point operations.
    private static final double DELTA = 0.000001;

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
        double result = Math.sqrt(Math.pow(this.getX() - c.getX() , 2) + Math.pow(this.getY() - c.getY(), 2) + Math.pow(this.getZ() - c.getZ(), 2));
        // check for overflows of result to infinity
        if (Double.isInfinite(result)){
            throw new ArithmeticException("Overflow");
        }
        return result;
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
        // individual doubles of coordinates are being substracted and then compared to a fixed variable
        // to avoid floating point errors during calculations
        if ((Math.abs(this.getX() - c.getX()) < DELTA) && (Math.abs(this.getY() - c.getY()) < DELTA) && (Math.abs(this.getZ() - c.getZ()) < DELTA)){
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