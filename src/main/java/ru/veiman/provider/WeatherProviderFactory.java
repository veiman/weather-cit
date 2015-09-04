package ru.veiman.provider;

import ru.veiman.provider.exception.ProviderNotFoundException;
import ru.veiman.provider.impl.owm.OWMWeatherProvider;
import ru.veiman.provider.impl.wunderground.WundergroundWeatherProvider;

/**
 * Created by veiman on 23.08.15.
 */
public class WeatherProviderFactory {

    private static final WeatherProvider owmProvider = new OWMWeatherProvider();
    private static final WeatherProvider wundergroundProvider = new WundergroundWeatherProvider();

    public static WeatherProvider getWeatherProvider(String providerName) throws ProviderNotFoundException {
        if (providerName.equals("OWMap")) {
            return owmProvider;
        }
        else if (providerName.equals("Wunderground")) {
            return wundergroundProvider;
        }
        else {
            throw new ProviderNotFoundException(providerName);
        }
    }

    public static boolean checkExistProvider(String providerName) {
        return
                providerName.equals("OWMap")
                || providerName.equals("Wunderground")
                ;
    }
}
