package ru.veiman.provider;

import ru.veiman.domain.City;
import ru.veiman.domain.Weather;

/**
 * Created by veiman on 20.08.15.
 */
public interface WeatherProvider {
    /**
     * Получение текущей погоды
     * @param city город
     * @return объект погоды
     */
    Weather getCurrentWeather(City city);

    /**
     * Получение имени провайдера погодных данных
     * @return имя (версия API)
     */
    String getProviderName();

}
