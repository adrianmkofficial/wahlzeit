package org.wahlzeit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.wahlzeit.handlers.HandlersTestSuite;
import org.wahlzeit.model.ModelTestSuite;
import org.wahlzeit.services.ServicesTestSuite;
import org.wahlzeit.utils.UtilsTestSuite;


@RunWith(Suite.class)
@SuiteClasses({
        HandlersTestSuite.class,
        ModelTestSuite.class,
        ServicesTestSuite.class,
        UtilsTestSuite.class
})

public class WahlzeitTestSuite {
}
