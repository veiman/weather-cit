package ru.veiman.provider.impl.owm;

import org.junit.Test;
import ru.veiman.provider.WeatherProvider;

import static org.junit.Assert.assertEquals;

/**
 * Created by veiman on 20.08.15.
 */
public class OWMWeatherProviderTest {

    WeatherProvider weatherProvider = new OWMWeatherProvider();

    @Test
    public void testGetProviderName() throws Exception {
        assertEquals("Open Weather Map",weatherProvider.getProviderName());
    }
}