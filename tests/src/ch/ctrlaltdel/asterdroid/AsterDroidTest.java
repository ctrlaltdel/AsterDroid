package ch.ctrlaltdel.asterdroid;

import android.test.ActivityInstrumentationTestCase;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class ch.ctrlaltdel.asterdroid.AsterDroidTest \
 * ch.ctrlaltdel.asterdroid.tests/android.test.InstrumentationTestRunner
 */
public class AsterDroidTest extends ActivityInstrumentationTestCase<AsterDroid> {

    public AsterDroidTest() {
        super("ch.ctrlaltdel.asterdroid", AsterDroid.class);
    }

}
