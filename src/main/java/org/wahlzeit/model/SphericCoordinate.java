package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate {

    private static final double LONGITUDE_MIN = -180;
    private static final double LONGITUDE_MAX = 180;
    private static final double LATITUDE_MIN = -90;
    private static final double LATITUDE_MAX = 90;
    private static final double EARTH_RADIUS_KM = 6371;

    private double latitude;
    private double longitude;
    private double radius;


    public SphericCoordinate(double latitude, double longitude) {
        this(latitude, longitude, EARTH_RADIUS_KM);
    }

    public SphericCoordinate(double latitude, double longitude, double radius) {
        if (latitude < LATITUDE_MIN || latitude > LATITUDE_MAX) {
            throw new IllegalArgumentException("Latitude is not in the range of [-90, 90]!");
        }
        if (longitude < LONGITUDE_MIN || longitude > LONGITUDE_MAX) {
            throw new IllegalArgumentException("Longitude is not in the range of [-180, 180]!");
        }
        if (radius < 0d) {
            throw new IllegalArgumentException("Radius can not be a negative number!");
        }
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        double x = radius * Math.sin(Math.toRadians(longitude)) * Math.cos(Math.toRadians(latitude));
        double y = radius * Math.sin(Math.toRadians(latitude)) * Math.sin(Math.toRadians(longitude));
        double z = radius * Math.cos(Math.toRadians(longitude));
        return new CartesianCoordinate(x, y, z);
    }

    @Override
    public double getCartesianDistance(final Coordinate c) {
        return this.asCartesianCoordinate().getCartesianDistance(c);
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }

    @Override
    public double getSphericDistance(final Coordinate c) {
        SphericCoordinate tmp_coord = c.asSphericCoordinate();
        double latDistance = Math.toRadians(tmp_coord.getLatitude() - this.getLatitude());
        double lonDistance = Math.toRadians(tmp_coord.getLongitude() - this.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.getLatitude())) * Math.cos(Math.toRadians(tmp_coord.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double tmp = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS_KM * tmp * 1000 ; // convert to meters
        return distance;
    }

    /**
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        if (latitude < LATITUDE_MIN || latitude > LATITUDE_MAX) {
            throw new IllegalArgumentException("Latitude is not in the range of [-90, 90]!");
        }
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        if (longitude < LONGITUDE_MIN || longitude > LONGITUDE_MAX) {
            throw new IllegalArgumentException("Longitude is not in the range of [-180, 180]!");
        }
        this.longitude = longitude;
    }

    /**
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param radius the radius of the coordinate
     */
    public void setRadius(double radius) {
        if (radius < 0d) {
            throw new IllegalArgumentException("Radius can not be a negative number!");
        }
        this.radius = radius;
    }

    @Override
    public int hashCode() {
        int hash = 2003;
        hash = 37 * hash + (int) (Double.doubleToLongBits(latitude)
                ^ (Double.doubleToLongBits(latitude) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(longitude)
                ^ (Double.doubleToLongBits(longitude) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(radius)
                ^ (Double.doubleToLongBits(radius) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return "(" + latitude + ", " + longitude + ")";
    }

    @Override
    public boolean equals(Object obj) {
        // check whether given obj is a Coordinate
        if (!(obj instanceof SphericCoordinate))
            return false;
        // check whether given obj is itself
        if (obj == this)
            return true;
        SphericCoordinate c = (SphericCoordinate) obj;
        // in any other case call isEqual method
        return this.isEqual(c);
    }


}