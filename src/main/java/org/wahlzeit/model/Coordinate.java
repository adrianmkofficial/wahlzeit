package org.wahlzeit.model;

/**
 * A Coordinate represents a point in a three-dimensional cartesian Coordinate system.
 */
public interface Coordinate {

    // Delta is a constant used in floating point operations.
    public static final double DELTA = 0.000001;

    /**
     * @return new instance of {@link CartesianCoordinate} with attributes of this instance
     * @methodtype conversion
     */
    CartesianCoordinate asCartesianCoordinate();

    /**
     * @return new instance of {@link SphericCoordinate} with attributes of this instance
     * @methodtype conversion
     */
    SphericCoordinate asSphericCoordinate();

    /**
     * @methodtype get
     * Returns cartesian distance of two Coordinates
     */
    double getCartesianDistance(final Coordinate c);

    /**
     * @methodtype get
     * Returns spherical distance of two Coordinates
     */
    double getSphericDistance(final Coordinate c);

    /**
     * Returns the distance between this and the given Coordinate.
     *
     * @param c the Coordinate to measure the distance to
     * @return the distance
     * @methodtype get
     */
    double getDistance(final Coordinate c);

    /**
     * Checks if the given Coordinate is equal in respect to its consisting values.
     *
     * @param c the Coordinate to check for equality
     * @return true if equal, otherwise false
     * @methodtype query
     */
    boolean isEqual(final Coordinate c);
}