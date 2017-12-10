package org.wahlzeit.model;

public abstract class AbstractCoordinate implements Coordinate {

    // Delta is a constant used in floating point operations.
    public static final double DELTA = 0.000001;
    @Override
    abstract public CartesianCoordinate asCartesianCoordinate();

    @Override
    abstract public SphericCoordinate asSphericCoordinate();

    @Override
    abstract public double getCartesianDistance(final Coordinate c);

    @Override
    abstract public double getSphericDistance(final Coordinate c);

    /**
     * Returns the distance between this and the given Coordinate.
     *
     * @param c the Coordinate to measure the distance to
     * @return the distance
     * @methodtype get
     */
    @Override
    public double getDistance(final Coordinate c) {
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

    /**
     * Checks if the given Coordinate is equal in respect to its consisting values.
     *
     * @param c the Coordinate to check for equality
     * @return true if equal, otherwise false
     * @methodtype query
     */
    @Override
    public boolean isEqual(final Coordinate c) {
        // check whether given c is null
        if (c != null) {
            if ((Math.abs(getDistance(c)) < DELTA)){
                return true;
            }
        }
        // in any other case coordinate is deemed not equal
        return false;
    }

}
