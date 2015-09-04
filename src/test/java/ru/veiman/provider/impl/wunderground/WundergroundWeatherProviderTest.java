package ru.veiman.provider.impl.wunderground;

import org.junit.Test;
import ru.veiman.provider.WeatherProvider;

import static org.junit.Assert.assertEquals;

/**
 * Created by veiman on 22.08.15.
 */
public class WundergroundWeatherProviderTest {

    WeatherProvider weatherProvider = new WundergroundWeatherProvider();

    @Test
    public void testGetProviderName() throws Exception {
        assertEquals("Weather Underground", weatherProvider.getProviderName());
    }
}