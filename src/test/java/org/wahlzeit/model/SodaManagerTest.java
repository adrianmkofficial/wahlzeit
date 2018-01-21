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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

public class SodaManagerTest {

    private SodaManager m;

    private SodaType st;

    @Before
    public void setup() {
        m = SodaManager.getInstance();
        st = m.getSodaType("Dr Pepper", "Schweppes Deutschland GmbH", "DE");
    }

    @Test
    public void getInstanceTest() {
        assertNotNull(m);
    }

    @Test
    public void createSodaTest() {
        Soda soda = m.createSoda("Dr Pepper", "Schweppes Deutschland GmbH", "DE", "Original", 330d);
        assertNotNull(soda);
        assertEquals(soda.getSodaType().getName(), "Dr Pepper");
        assertEquals(soda.getSodaType().getManufacturer(), "Schweppes Deutschland GmbH");
        assertEquals(soda.getServing_size_ml(), 330d, 0.0001);
        assertEquals(soda.getVariation(), "Original");
    }

    @Test
    public void getSodaType() {
        SodaType st = m.getSodaType("Dr Pepper", "Schweppes Deutschland GmbH", "DE");
        assertNotNull(st);
        assertEquals(st.getName(), "Dr Pepper");
        assertEquals(st.getManufacturer(), "Schweppes Deutschland GmbH");
        assertEquals(st.getCountry(), "DE");
        assertEquals(st.getSuperType(), null);

    }

}