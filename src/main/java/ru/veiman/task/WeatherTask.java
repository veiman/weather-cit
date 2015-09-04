package ru.veiman.task;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.veiman.config.WeatherConfig;
import ru.veiman.domain.City;
import ru.veiman.domain.Weather;
import ru.veiman.provider.WeatherProvider;
import ru.veiman.provider.WeatherProviderFactory;
import ru.veiman.provider.exception.ProviderNotFoundException;
import ru.veiman.repository.CityRepository;
import ru.veiman.repository.WeatherRepository;

import java.util.List;

/**
 * Created by veiman on 22.08.15.
 */
//@Component
public class WeatherTask implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(WeatherTask.class);

    WeatherConfig weatherConfig;

    CityRepository cityRepository;

    WeatherRepository weatherRepository;

//    public WeatherTask() {
//    }

    public WeatherTask(WeatherConfig weatherConfig, CityRepository cityRepository, WeatherRepository weatherRepository) {
        this.weatherConfig = weatherConfig;
        this.cityRepository = cityRepository;
        this.weatherRepository = weatherRepository;
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        DateTime dateTime = new DateTime();

        log.info("Start task (time: " + dateTime.toString() + ")");
        // read cities
        List<City> cities = cityRepository.findAll();


        // read providers
        List<String> providersName = weatherConfig.getProviders();
        log.debug("providers: " + providersName);

        for (String name : providersName) {
            try {
                if (WeatherProviderFactory.checkExistProvider(name)) {
                    WeatherProvider weatherProvider = WeatherProviderFactory.getWeatherProvider(name);
                    log.debug("------ " + weatherProvider.getProviderName() + " ------");
                    for (City city : cities) {
                        Weather currentWeather = weatherProvider.getCurrentWeather(city);
                        currentWeather.setDateTime(dateTime);
//                currentWeather.setDateTime(new DateTime());
                        log.debug("Save in DB: " + currentWeather.toString());
                        weatherRepository.saveAndFlush(currentWeather);
                    }





                }
            }
            catch (ProviderNotFoundException e) {
                log.error("Provider " + name + " not found");
                e.printStackTrace();
            }


        }

    }
}
