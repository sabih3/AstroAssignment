package ahmed.sabih.com.astroassignment;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("ahmed.sabih.com.astroassignment", appContext.getPackageName());


        /**1. To display a list of Astro channels (showing at a minimum channel name and
         number)
            a. Ability to sort by channel number
            b. Ability to sort by channel name

         2. To allow a user to mark a favourite channel as on-device persistent.
            a. User launches app and tags a favourite channel
            b. User closes app and favourite channel appears in main screen identified
            as a favourite
         *
         */
    }
}
