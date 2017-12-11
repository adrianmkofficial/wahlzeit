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
import org.wahlzeit.utils.CountryUtil;


@Subclass
public class SodaPhoto extends Photo {

	// Name of the soda depicted
	protected String name;

	// Name of the manufacturer of a soda
	protected String manufacturer;

	// Serving size of soda in milliliters
	protected Double serving_size_ml;

	/**
	 * Country of origin of a soda.
	 * NOTE: Sodas can be attributed with a country, so we can distinguish between e.g. a Coke from USA and Germany
	 * since the ingredients differ slightly as well (e.g. use of high glucose fructose syrup instead of sugar)
	 */
	protected String country;

	/**
	 * This is the standard constructor of the SodaPhoto class.
	 *
	 * @methodtype constructor
	 */
	public SodaPhoto() {
		super();
	}

	/**
	 * Constructor of the SodaPhoto class.
	 *
	 * @param id
	 * @methodtype constructor
	 */
	public SodaPhoto(PhotoId id) {
		super(id);
	}

	/**
	 * Convenience constructor of the SodaPhoto class.
	 *
	 * @param name
	 * @param manufacturer
	 * @param serving_size_ml
	 * @param country
	 * @methodtype constructor
	 */
	public SodaPhoto(PhotoId photoId, String name, String manufacturer, Double serving_size_ml, String country) {
		this.id = photoId;
		setName(name);
		setManufacturer(manufacturer);
		setServing_size_ml(serving_size_ml);
		setCountry(country);
		assertClassInvariants();
	}

	/**
	 * Gets the name of the Soda in the photo.
	 *
	 * @return name of soda as string
	 * @methodtype get
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Sets the name of the Soda in the photo.
	 *
	 * @param name name of soda
	 * @methodtype set
	 */
	public final void setName(String name) throws IllegalArgumentException {
		assertName(name);
		this.name = name;
	}

	/**
	 * Gets the name of the manufacturer.
	 *
	 * @return manufacturer of soda as string
	 * @methodtype get
	 */
	public final String getManufacturer() {
		return manufacturer;
	}

	/**
	 * Sets the name of the manufacturer.
	 *
	 * @param manufacturer manufacturer of soda
	 * @methodtype set
	 */
	public final void setManufacturer(String manufacturer) throws IllegalArgumentException{
		assertManufacturer(manufacturer);
		this.manufacturer = manufacturer;
	}


	/**
	 * Gets the serving size in milliliters.
	 *
	 * @return serving_size_ml the serving size of soda in milliliters as double
	 * @methodtype get
	 */
	public final Double getServing_size_ml() {
		return serving_size_ml;
	}

	/**
	 * Gets the serving size in fluid ounces.
	 *
	 * @return serving_size_oz the serving size of soda in ounces as double
	 * @methodtype get
	 */
	public final Double serving_size_oz() {
		return serving_size_ml * 0.033814;
	}


	/**
	 * Set the serving size in milliliters.
	 *
	 * @param serving_size_ml the serving size of soda in milliliters
	 * @methodtype set
	 */
	public final void setServing_size_ml(Double serving_size_ml) throws IllegalArgumentException {
		assertServingSize(serving_size_ml);
		this.serving_size_ml = serving_size_ml;
	}

	/**
	 * Gets the country of origin of a soda.
	 *
	 * @return country the country of origin as string
	 * @methodtype get
	 */
	public final String getCountry() {
		return country;
	}

	/**
	 * Set the country of origin of a soda.
	 *
	 * @param country the country of origin
	 * @methodtype set
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
	protected void assertServingSize(Double serving_size_ml) throws IllegalArgumentException
	{
		if (serving_size_ml <= 0 || Double.isInfinite(serving_size_ml) || Double.isNaN(serving_size_ml)) {
			throw new IllegalArgumentException("Illegal serving size!");
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
			assertServingSize(serving_size_ml);
			assertCountry(country);
		} catch(IllegalArgumentException e) {
			throw new IllegalStateException();
		}
	}
}
