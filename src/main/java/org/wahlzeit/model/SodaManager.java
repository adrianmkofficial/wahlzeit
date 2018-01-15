package org.wahlzeit.model;


import org.wahlzeit.services.ObjectManager;

import java.util.Objects;
import java.util.Set;
import java.util.HashMap;

public class SodaManager extends ObjectManager {

    protected static final SodaManager instance = new SodaManager();

    private HashMap<Integer, Soda> Sodas = new HashMap<>();
    private HashMap<Integer, SodaType> SodaTypes = new HashMap<>();


    /**
     * @methodtype constructor
     */
    public SodaManager() {
    }

    /**
     * @methodtype get
     */
    public static final SodaManager getSodaManager() {
        return instance;
    }


    public synchronized SodaType createSodaType(String name, String country, String manufacturer, Set<SodaType.Category> categories) {
        int hash = Objects.hash(name, manufacturer, country);
        if(SodaTypes.containsKey(hash)) {
            return SodaTypes.get(hash);
        } else {
            SodaType newSodaType = new SodaType(name, country, manufacturer, categories);
            SodaTypes.put(hash, newSodaType);
            return newSodaType;
        }
    }

    public synchronized Soda createSoda(SodaType sodaType, String variation, Double serving_size_ml) {
        SodaType type = getSodaType(sodaType.hashCode());
        Soda result = type.createSoda(variation, serving_size_ml);

        if(Sodas.containsKey(result.hashCode())) {
            return Sodas.get(result.hashCode());
        } else {
            Sodas.put(result.hashCode(), result);
            return result;
        }
    }

    public Soda getSoda(String id) {
        return Sodas.get(id);
    }

    public SodaType getSodaType(Integer hash) {
        return SodaTypes.get(hash);
    }

    private void assertValidSodaTypeName(String name) {
        if(!SodaTypes.containsKey(name)) {
            throw new IllegalArgumentException("Invalid SodaType name");
        }
    }

}