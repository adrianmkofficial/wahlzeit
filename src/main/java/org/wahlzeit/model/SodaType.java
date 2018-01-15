package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.CountryUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class SodaType extends DataObject {

    protected enum Category {
        COLA,
        PEPPER,
        CITRUS_SODA,
        MINERAL_WATER,
        LOW_CALORIE,
        ALCOHOLIC_MIX,
        MIXED,
        OTHER
    }

    // Name of a soda
    protected String name;

    // Name of the manufacturer of a soda
    protected String manufacturer;

    // Core properties of a soda
    protected Set<Category> categories;

    /**
     * Country of origin of a soda.
     * NOTE: Sodas can be attributed with a country, so we can distinguish between e.g. a Coke from USA and Germany
     * since the ingredients differ slightly as well (e.g. use of high glucose fructose syrup instead of sugar)
     */
    protected String country;

    protected SodaType superType = null;
    protected Set<SodaType> subTypes = new HashSet<>();

    public SodaType getSuperType() {
        return superType;
    }

    public Iterator<SodaType> getSubTypeIterator() {
        return subTypes.iterator();
    }

    public void addSubType(SodaType st) {
        if(name == null) {
            throw new IllegalArgumentException( "Sub-type can not be null!");
        }
        st.setSuperType(this);
        subTypes.add(st);
    }

    public void setSuperType(SodaType sodaType) {
        if(sodaType == null) {
            throw new IllegalArgumentException( "Super-type can not be null!");
        }
        this.superType = sodaType;
        superType.subTypes.add(sodaType);
    }

    public boolean hasInstance(Soda s) {
        if(s == null) {
            throw new IllegalArgumentException( "Soda can not be null!");
        }
        if (s.getSodaType() == this) {
            return true;
        }
        for (SodaType type : subTypes) {
            if (type.hasInstance(s)) {
                return true;
            }
        }
        return false;
    }


    public Soda createSoda(String variation, Double serving_size_ml) {
        return new Soda(variation, serving_size_ml, this);
    }

    public Soda createSoda(String name, String manufacturer, String country, String variation, Double serving_size_ml) {
        this.setName(name);
        this.setManufacturer(name);
        this.setCountry(name);
        return new Soda(variation, serving_size_ml, this);
    }

    public SodaType(String name, String manufacturer, String country, Set<Category> categories) {
        setName(name);
        setManufacturer(manufacturer);
        setCountry(country);
        setCategories(categories);
    }

    /**
     * Gets the name of a Soda.
     *
     * @methodtype get
     * @return name of soda as string
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets the name of a Soda.
     *
     * @methodtype set
     * @param name name of soda
     */
    public final void setName(String name) throws IllegalArgumentException {
        assertName(name);
        this.name = name;
    }

    /**
     * Gets the name of the manufacturer.
     *
     * @methodtype get
     * @return manufacturer of soda as string
     */
    public final String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the name of the manufacturer.
     *
     * @methodtype set
     * @param manufacturer manufacturer of soda
     */
    public final void setManufacturer(String manufacturer) throws IllegalArgumentException{
        assertManufacturer(manufacturer);
        this.manufacturer = manufacturer;
    }

    /**
     * Gets the country of origin of a soda.
     *
     * @methodtype get
     * @return country the country of origin as string
     */
    public final String getCountry() {
        return country;
    }

    /**
     * Set the country of origin of a soda.
     *
     * @methodtype set
     * @param country the country of origin
     */
    public final void setCountry(String country) throws IllegalArgumentException {
        assertCountry(country);
        this.country = country;
    }

    /**
     * @methodtype assertion
     */
    protected void assertName(String name) throws IllegalArgumentException
    {
        if(name == null) {
            throw new IllegalArgumentException("Illegal name!");
        }
    }

    /**
     * @methodtype assertion
     */
    protected void assertManufacturer(String manufacturer) throws IllegalArgumentException
    {
        if(manufacturer == null) {
            throw new IllegalArgumentException("Illegal manufacturer!");
        }
    }

    /**
     * @methodtype assertion
     */
    protected void assertCountry(String country) throws IllegalArgumentException
    {
        if (!CountryUtil.isValidCountry(country)) {
            throw new IllegalArgumentException("Country doesn't exist!");
        }
    }


    /**
     * @methodtype assertion
     */
    protected void assetCategories(Set<Category> categories) throws IllegalArgumentException
    {
        if (categories == null) {
            throw new IllegalArgumentException("Categories can't be null!");
        }
    }

    /**
     * Gets the categories of a soda.
     *
     * @methodtype get
     * @return categories categories of soda as set
     */
    public final Set<Category> getCategories() {
        return categories;
    }

    /**
     * Sets categories of a soda.
     *
     * @methodtype set
     * @param categories categories to be associated with soda
     */
    public final void setCategories(Set<Category> categories) throws IllegalArgumentException {
        this.categories = categories;
    }


    /**
     * @methodtype assertion
     */
    protected void assertClassInvariants() throws IllegalStateException{
        try {
            assertName(name);
            assertManufacturer(manufacturer);
            assertCountry(country);
            assetCategories(categories);
        } catch(IllegalArgumentException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getManufacturer(), this.getCountry());
    }
}
