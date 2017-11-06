package org.wahlzeit.model;

/**
 * A location consists of a coordinate.
 */

public class Location {

    /**
     *
     */
    public Coordinate coordinate;

    /**
     * @methodtype constructor
     */
    public Location() {
        this.coordinate = new Coordinate();
    }

    /**
     * @methodtype constructor
     */
    public Location(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate can not be null.");
        }
        this.coordinate = coordinate;
    }

    /**
     * @methodtype get
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @methodtype set
     */
    public void setCoordinate(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate can not be null.");
        }
        this.coordinate = coordinate;
    }
}
