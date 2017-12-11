package org.wahlzeit.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.wahlzeit.model.persistence.PersistenceTestSuite;


@RunWith(Suite.class)
@SuiteClasses({
        PersistenceTestSuite.class,
        AccessRightsTest.class,
        CartesianCoordinateTest.class,
        SphericCoordinateTest.class,
        FlagReasonTest.class,
        GenderTest.class,
        GuestTest.class,
        LocationTest.class,
        PhotoFilterTest.class,
        TagsTest.class,
        UserStatusTest.class,
        ValueTest.class,
        SodaPhotoTest.class
})
public class ModelTestSuite {
}