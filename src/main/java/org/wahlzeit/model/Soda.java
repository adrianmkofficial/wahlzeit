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


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.CountryUtil;

import java.util.Objects;

@Entity
public class Soda extends DataObject {

    // Serving size of soda in milliliters
    protected Double serving_size_ml;

    // Name of the variation of a soda (e.g. Coke Cherry, Coke Regular)
    protected String variation;

    // Type of Soda
    protected SodaType sodaType;

    // Private constructor to ensure every soda also has a type associated with it
    private Soda() {}

    /**
     * Constructor of the Soda class.
     * @param variation
     * @param serving_size_ml
     * @param sodaType
     * @methodtype constructor
     */
    public Soda(String variation, Double serving_size_ml, SodaType sodaType) {
        setVariation(variation);
        setServing_size_ml(serving_size_ml);
        setSodaType(sodaType);
    }

    /**
     * Gets the variation  of a soda (e.g. coke lime)
     *
     * @methodtype get
     * @return sodaType the core type of a soda
     */
    public final String getVariation() {
        return variation;
    }

    /**
     * Gets the core type of a soda
     *
     * @methodtype get
     * @return sodaType the core type of a soda
     */
    public final SodaType getSodaType() {
        return sodaType;
    }

    /**
     * Gets the serving size in milliliters.
     *
     * @methodtype get
     * @return serving_size_ml the serving size of soda in milliliters as double
     */
    public final Double getServing_size_ml() {
        return serving_size_ml;
    }

    /**
     * Gets the serving size in fluid ounces.
     *
     * @methodtype get
     * @return serving_size_oz the serving size of soda in ounces as double
     */
    public final Double getServing_size_oz() {
        return serving_size_ml * 0.033814;
    }


    /**
     * Set the serving size in milliliters.
     *
     * @methodtype set
     * @param serving_size_ml the serving size of soda in milliliters
     */
    public final void setServing_size_ml(Double serving_size_ml) throws IllegalArgumentException {
        assertServingSize(serving_size_ml);
        this.serving_size_ml = serving_size_ml;
    }

    /**
     * @methodtype assertion
     */
    protected void assertServingSize(Double serving_size_ml) throws IllegalArgumentException
    {
        if (serving_size_ml <= 0 || Double.isInfinite(serving_size_ml) || Double.isNaN(serving_size_ml)) {
            throw new IllegalArgumentException("Illegal serving size!");
        }
    }
    /**
     * Set the variation of a soda.
     *
     * @methodtype set
     * @param variation the variation of soda
     */
    public void setVariation(String variation) {
        assertVariation(variation);
        this.variation = variation;
    }

    /**
     * @methodtype assertion
     */
    protected void assertVariation(String variation) throws IllegalArgumentException{
        if (variation == null) {
            throw new IllegalArgumentException("Variation can't be null");
        }
    }

    /**
     * Set the type of a soda.
     *
     * @methodtype set
     * @param sodaType the type of soda
     */
    public void setSodaType(SodaType sodaType) {
        assertSodaType(sodaType);
        this.sodaType = sodaType;
    }

    /**
     * @methodtype assertion
     */
    protected void assertSodaType(SodaType sodaType) throws IllegalArgumentException{
        sodaType.assertClassInvariants();
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((variation == null) ? 0 : variation.hashCode());
        result = prime * result + Double.valueOf(serving_size_ml).hashCode(); ;
        result = prime * result + ((sodaType == null) ? 0 : sodaType.hashCode());
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
        Soda other = (Soda) obj;
        if (variation == null) {
            if (other.variation != null)
                return false;
        } else if (!variation.equals(other.variation))
            return false;
        if (serving_size_ml == null) {
            if (serving_size_ml != null)
                return false;
        } else if (this.serving_size_ml != other.serving_size_ml)
            return false;
        if (sodaType == null) {
            if (other.sodaType != null)
                return false;
        } else if (!sodaType.equals(other.sodaType))
            return false;
        return true;
    }
}
