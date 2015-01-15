package de.franziskaneumeister.counterswipe;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class ApplicationTest {
   
    @Test
    public void shouldSucceed(){
        assertTrue(true);
    }

    @Test
    public void shouldFail(){
        assertTrue(false);
    }

}