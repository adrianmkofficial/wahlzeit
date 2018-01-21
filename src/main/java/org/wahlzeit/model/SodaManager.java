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

import java.util.HashMap;
import java.util.Objects;

import org.wahlzeit.services.ObjectManager;

/**
 *
 * The SodaManager class manages Soda and SodaType objects.
 *
 */
public class SodaManager extends ObjectManager {

    /**
     * Single instance of SodaManager
     */
    protected static final SodaManager instance = new SodaManager();

    /**
     * sodaTypes stores all exisiting Sodatype objects
     */
    private HashMap<Integer, SodaType> sodaTypes = new HashMap<Integer, SodaType>();

    /**
     * sodas stores all existing Soda objects
     */
    private HashMap<Integer, Soda> sodas = new HashMap<Integer, Soda>();

    /**
     * @methodtype constructor
     */
    private SodaManager() {

    }


    /**
     * Public singleton access method
     *
     * @methodtype getter
     */
    public static final SodaManager getInstance() {
        return instance;
    }

    /**
     * @methodtype factory method
     * @param name
     * @param manufacturer
     * @param country
     * @param variation
     * @param serving_size_ml
     * @return
     * New Soda object or already existing one
     */
    public Soda createSoda(String name, String manufacturer, String country, String variation, Double serving_size_ml) {
        if (name == null) {
            throw new IllegalArgumentException("name shouldn't be null!");
        }
        if (manufacturer == null) {
            throw new IllegalArgumentException("manufacturer shouldn't be null!");
        }
        if (country == null) {
            throw new IllegalArgumentException("country shouldn't be null!");
        }
        if (variation == null) {
            throw new IllegalArgumentException("variation shouldn't be null!");
        }
        if (serving_size_ml == null) {
            throw new IllegalArgumentException("serving_size_ml shouldn't be null!");
        }
        int hash = Objects.hash(variation, serving_size_ml);
        SodaType SodaType = getSodaType(name, manufacturer, country);
        Soda Soda = SodaType.createInstance(variation, serving_size_ml);
        sodas.put(hash, Soda);
        return Soda;
    }

    /**
     * @methodtype get
     * @param name
     * @param manufacturer
     * @param country
     * @return SodaType object
     */
    public SodaType getSodaType(String name, String manufacturer,  String country) {
        if (name == null) {
            throw new IllegalArgumentException("name shouldn't be null!");
        }
        if (country == null) {
            throw new IllegalArgumentException("country shouldn't be null!");
        }
        if (manufacturer == null) {
            throw new IllegalArgumentException("manufacturer shouldn't be null!");
        }
        int hash = Objects.hash(name, manufacturer, country);
        if (sodaTypes.containsKey(hash)) {
            return sodaTypes.get(hash);
        } else {
            SodaType newSodatype = new SodaType(name, manufacturer, country);
            sodaTypes.put(hash, newSodatype);
            return newSodatype;
        }
    }

    /**
     * @methodtype get
     * @param variation
     * @param serving_size_ml
     * @return Soda object
     */
    public Soda getSoda(String variation, Double serving_size_ml){
        if (variation == null) {
            throw new IllegalArgumentException("variation shouldn't be null!");
        }
        if (serving_size_ml == null) {
            throw new IllegalArgumentException("serving_size_ml shouldn't be null!");
        }
        int hash = Objects.hash(variation, serving_size_ml);
        if(sodas.containsKey(hash)) {
            return sodas.get(hash);
        }
        return null;
    }
}