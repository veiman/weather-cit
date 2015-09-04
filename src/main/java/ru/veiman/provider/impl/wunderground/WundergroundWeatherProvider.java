package ru.veiman.provider.impl.wunderground;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import ru.veiman.domain.City;
import ru.veiman.domain.Weather;
import ru.veiman.provider.WeatherProvider;
import ru.veiman.provider.impl.wunderground.dto.request1.ResultDTO;
import ru.veiman.provider.impl.wunderground.dto.request1.RootDTO;
import ru.veiman.provider.impl.wunderground.dto.request2.ObservationDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by veiman on 22.08.15.
 */
public class WundergroundWeatherProvider implements WeatherProvider {

    public static final String KEY = "Wunderground";
    private static final String API_KEY = "fa61ad413e72e43c";
    private static final Logger log = LoggerFactory.getLogger(WundergroundWeatherProvider.class);

    /**
     * Получение текущей погоды
     *
     * @param city город
     * @return объект погоды
     */
    @Override
    public Weather getCurrentWeather(City city) {
        ru.veiman.provider.impl.wunderground.dto.request2.RootDTO rootDTO2 = getResultFromAPI(city);

        ObservationDTO current_observation = rootDTO2.getCurrent_observation();
        String visibility = current_observation.getVisibility_km().equals("N/A") ?
                "N/A" : "" + Double.parseDouble(current_observation.getVisibility_km());

        Weather weather = Weather.newBuilder()
                .setCity(city)
                .setTemperature(current_observation.getTemp_c())
                .setHumidity(fixHumidity(current_observation.getRelative_humidity()))
                .setDescription(current_observation.getWeather())
                .setPressure(Integer.parseInt(current_observation.getPressure_mb()) * 0.75) // из ГПа в мм рт.ст
                .setVisibility(visibility)
                .setWindSpeed(new BigDecimal(current_observation.getWind_kph() * 0.278).setScale(3, RoundingMode.FLOOR).doubleValue()) // из км/ч в м/с и округляем
                .setWindDirection(current_observation.getWind_dir())
                .setProviderName(KEY)
                .build();

//        log.debug("Weather: " + weather.toString());
        return weather;
    }

    /**
     * переделавыем из "48%" -> 48
     *
     * @param relative_humidity строчка
     * @return число
     */
    private int fixHumidity(String relative_humidity) {
        //отрезаем последний символ
        String s = relative_humidity.substring(0, relative_humidity.length() - 1).trim();
        return Integer.parseInt(s);
    }

    private ru.veiman.provider.impl.wunderground.dto.request2.RootDTO getResultFromAPI(City city) {
        RestTemplate restTemplate = new RestTemplate();
        final String url1 = "http://api.wunderground.com/api/{api_key}/conditions/lang:RU/q/{city}.json";
        Map params = new HashMap<>();
        params.put("api_key", API_KEY);
        params.put("city", city.getName());
        RootDTO rootDTO = restTemplate.getForObject(url1, RootDTO.class, params);
        log.debug(rootDTO.toString());
        ru.veiman.provider.impl.wunderground.dto.request2.RootDTO rootDTO2 = null;
        if (rootDTO.getResponse().getResults() == null) { // если такой город один и датчик погоды в нем тоже единственный
            rootDTO2 = restTemplate.getForObject(
                    url1,
                    ru.veiman.provider.impl.wunderground.dto.request2.RootDTO.class,
                    params
            );
        }
        else {
            List<ResultDTO> results = rootDTO.getResponse().getResults();
            for (ResultDTO result : results) {
                if (result.getCountry_iso3166().equals(city.getCountry())) {
                    log.debug(result.getZmw());
                    final String url2 = "http://api.wunderground.com/api/{api_key}/conditions/lang:RU/q/zmw:{zmw}.json";
                    params.remove("city");
                    params.put("zmw", result.getZmw());
                    rootDTO2 = restTemplate.getForObject(
                            url2,
                            ru.veiman.provider.impl.wunderground.dto.request2.RootDTO.class,
                            params
                    );
                    return rootDTO2;
                }
            }
        }
        return rootDTO2;
    }

    /**
     * Получение имени провайдера погодных данных
     *
     * @return имя (версия API)
     */
    @Override
    public String getProviderName() {
        return "Weather Underground";
    }
}
