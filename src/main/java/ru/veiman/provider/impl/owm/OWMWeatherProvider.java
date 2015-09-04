package ru.veiman.provider.impl.owm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import ru.veiman.config.Constants;
import ru.veiman.domain.City;
import ru.veiman.domain.Weather;
import ru.veiman.provider.WeatherProvider;
import ru.veiman.provider.impl.owm.dto.RootDTO;
import ru.veiman.provider.impl.owm.dto.WeatherDTO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by veiman on 20.08.15.
 */
public class OWMWeatherProvider implements WeatherProvider {

    public static final String API_KEY = "a0690c4d13d610f6e24d99c65bb3ea70";
    public static final String VERSION = "2.5";
    public static final String KEY = "OWMap";
    private static final Logger log = LoggerFactory.getLogger(OWMWeatherProvider.class);

    /**
     * Получение текущей погоды
     *
     * @param city город
     * @return объект погоды
     */
    @Override
    public Weather getCurrentWeather(City city) {
        RestTemplate restTemplate = new RestTemplate();
        final String url = "http://api.openweathermap.org/data/{version}/weather?q={city},{country}&APPID={api_key}&lang=ru&units=metric";
        Map<String, String> params = new HashMap<String, String>();
        params.put("version", VERSION);
        params.put("api_key", API_KEY);
        params.put("city", city.getName());
        params.put("country", city.getCountry());
        RootDTO rootDTO = restTemplate.getForObject(url, RootDTO.class, params);
        WeatherDTO weatherDTO = rootDTO.getWeather().get(0);


        Weather weather = Weather.newBuilder()
                .setCity(city)
                .setDescription(weatherDTO.getDescription())
                .setHumidity(rootDTO.getMain().getHumidity())
                .setPressure(rootDTO.getMain().getPressure() * 0.75) // из ГПа в мм рт.ст
                .setTemperature(rootDTO.getMain().getTemp())
                .setVisibility(rootDTO.getVisibility() == 0d ? Constants.UNKNOWN : "" + (rootDTO.getVisibility() / 1000)) // из метров в километры
                .setWindSpeed(rootDTO.getWind().getSpeed())
                .setWindDirection(Constants.UNKNOWN) // нет информации о направлении ветра
                .setProviderName(KEY)
                .build();
        log.debug("weather: " + weather);
        return weather;
    }

    /**
     * Получение имени провайдера погодных данных
     *
     * @return имя (версия API)
     */
    @Override
    public String getProviderName() {
        return "Open Weather Map";
    }
}
