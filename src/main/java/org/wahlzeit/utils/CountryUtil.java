package org.wahlzeit.utils;

import java.util.*;

public final class CountryUtil {

    private static final List COUNTRIES = Arrays.asList(Locale.getISOCountries());

    // Protected constructor to prevent instantiation.
    protected CountryUtil() {}

    public static boolean isValidCountry(String c) {
        return COUNTRIES.contains(c);
    }
}