package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
/**
 * A SodaPhoto represents a user-provided (uploaded) photo of a soda / soft drink.
 */
@Subclass
public class SodaPhoto extends Photo {

    /**
     * Name of a soft drink
     */
    protected String sodaName;

    /**
     * Name of the manufacturer of a soft drink
     */
    protected String manufacturer;

    /**
     * Serving size of soda in milliliters
     */
    protected Double serving_size_ml;

    /**
     * Country of origin of a soda.
     * NOTE: Each uploaded drink has its own country of origin,
     * thus we distinguish between e.g. a Coke from USA and Germany
     */
    protected String country;

    /**
     * This is the standard constructor of the SodaPhoto class.
     */
    public SodaPhoto() {
        super();
    }

    /**
     * Constructor of the SodaPhoto class.
     *
     * @param id
     */
    public SodaPhoto(PhotoId id) {
        super(id);
    }

    /**
     * Constructor of the SodaPhoto class.
     *
     * @param sodaName
     * @param manufacturer
     * @param serving_size_ml
     * @param country
     */
    public SodaPhoto(PhotoId photoId, String sodaName, String manufacturer, Double serving_size_ml, String country)
    {
        this.id = photoId;
        this.sodaName = sodaName;
        this.manufacturer = manufacturer;
        this.serving_size_ml = serving_size_ml;
        this.country = country;
    }    /**
     * Gets the name of the Soda in the photo.
     *
     * @return the name as string
     */
    public String getName() {
        return sodaName;
    }

    /**
     * Sets the name of the Soda in the photo.
     *
     * @param name
     */
    public void setName(String name) {
        this.sodaName = name;
    }

    /**
     * Gets the name of the manufacturer.
     *
     * @return the name as string
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the name of the manufacturer.
     *
     * @param manufacturer the name of the manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }


    /**
     * Gets the serving size in milliliters.
     *
     * @methodtype get
     * @return serving_size_ml the serving size in milliliters as double
     */
    public Double getServing_size_ml() {
        return serving_size_ml;
    }

    /**
     * Set the serving size in milliliters.
     *
     * @methodtype set
     * @param serving_size_ml the serving size in milliliters
     */
    public void setServing_size_ml(Double serving_size_ml) {
        this.serving_size_ml = serving_size_ml;
    }

    /**
     * Gets the country of origin of a soda.
     *
     * @methodtype get
     * @return country the country of origin as string
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the country of origin of a soda.
     *
     * @methodtype set
     * @param country the country of origin
     */
    public void setCountry(String country) {
        this.country = country;
    }
}