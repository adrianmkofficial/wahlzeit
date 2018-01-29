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

import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.CountryUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class SodaType extends DataObject {

    // Name of a soda
    protected String name;

    // Name of the manufacturer of a soda
    protected String manufacturer;

    /**
     * Country of origin of a soda.
     * NOTE: Sodas can be attributed with a country, so we can distinguish between e.g. a Coke from USA and Germany
     * since the ingredients differ slightly as well (e.g. use of high glucose fructose syrup instead of sugar)
     */
    protected String country;

    protected SodaType superType = null;
    protected Set<SodaType> subTypes = new HashSet<SodaType>();

    /**
     * Constructor of the SodaType class.
     *
     * @methodtype constructor
     */
    public SodaType() {}

    /**
     * Constructor of the SodaType class.
     *
     * @methodtype constructor
     * @param name
     * @param manufacturer
     * @param country
     */
    public SodaType(String name, String manufacturer, String country) {
        this.superType = null;
        setName(name);
        setManufacturer(manufacturer);
        setCountry(country);
    }
    /**
     * Constructor of the SodaType class.
     *
     * @methodtype constructor
     * @param superType
     * @param name
     * @param manufacturer
     * @param country
     */
    public SodaType(SodaType superType, String name, String manufacturer, String country) {
        if(superType != null) {
            this.superType = superType;
        }
        setName(name);
        setManufacturer(manufacturer);
        setCountry(country);
    }

    /**
     * Gets the supertype of a soda
     *
     * @methodtype get
     * @return superType the superType of a soda
     */
    public SodaType getSuperType(){
        return superType;
    }

    /**
     * Sets the supertype of a SodaType
     *
     * @methodtype set
     */
    public void setSuperType(SodaType st) {
        if(st == null) {
            throw new IllegalArgumentException( "Super-type can not be null!");
        }
        this.superType = st;
    }

    /**
     * Gets an iterator for subtypes of a SodaType
     *
     * @methodtype get
     * @return subTypes.iterator() the subtypes iterator of a SodaType
     */
    public Iterator <SodaType> getSubTypeIterator(){
        return subTypes.iterator();
    }




    public void addSubType(SodaType st) {
        if(st == null) {
            throw new IllegalArgumentException( "Sub-type can not be null!");
        }
        st.setSuperType(this);
        subTypes.add(st);
    }

    /**
     * @methodtype boolean query
     * @return true if soda has a type instance
     */
    public boolean hasInstance(Soda s)
    {
        if(s == null) {
            throw new IllegalArgumentException( "Soda can not be null!");
        }
        if(s.getSodaType() == this)
            return true;

        return isSubtype(s.getSodaType());
    }

    /**
     * @methodtype boolean query
     * @return true if this is a subtype
     */
    public boolean isSubtype(){
        return (superType != null);
    }

    /**
     * @methodtype boolean query
     * @param st
     * @return true if this is a subtype
     */
    public boolean isSubtype(SodaType st)
    {
        if(st == null) {
            throw new IllegalArgumentException( "SodaType can not be null!");
        }
        this.getSubTypeIterator();
        Iterator itr = this.getSubTypeIterator();

        while(itr.hasNext()) {
            SodaType type = (SodaType) itr.next();
            if (type == st) {
                return true;
            }
        }
        return false;
    }

    /**
     * @methodtype factory method
     * @param variation
     * @param serving_size_ml
     * @return Soda
     */
    public Soda createInstance(String variation, Double serving_size_ml) {
        return new Soda(variation, serving_size_ml, this);
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
    protected void assertClassInvariants() throws IllegalStateException{
        try {
            assertName(name);
            assertManufacturer(manufacturer);
            assertCountry(country);
        } catch(IllegalArgumentException e) {
            throw new IllegalStateException();
        }
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((subTypes == null) ? 0 : subTypes.hashCode());
        result = prime * result + ((superType == null) ? 0 : superType.hashCode());
        return result;
    }

    /**
     *
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SodaType st = (SodaType) obj;
        if (name == null) {
            if (st.name != null)
                return false;
        } else if (!name.equals(st.name))
            return false;
        if (manufacturer == null) {
            if (st.manufacturer != null)
                return false;
        } else if (!manufacturer.equals(st.manufacturer))
            return false;
        if (country == null) {
            if (st.country != null)
                return false;
        } else if (!country.equals(st.country))
            return false;
        if (subTypes == null) {
            if (st.subTypes != null)
                return false;
        } else if (!subTypes.equals(st.subTypes))
            return false;
        if (superType == null) {
            if (st.superType != null)
                return false;
        } else if (!superType.equals(st.superType))
            return false;
        return true;
    }
}
