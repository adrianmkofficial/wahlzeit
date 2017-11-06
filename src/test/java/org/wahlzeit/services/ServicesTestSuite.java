package org.wahlzeit.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.wahlzeit.services.mailing.EmailAddressTest;
import org.wahlzeit.services.mailing.EmailServiceTestSuite;

@RunWith(Suite.class)
@SuiteClasses({
        EmailServiceTestSuite.class,
        LogBuilderTest.class
})
public class ServicesTestSuite {

}