package ru.veiman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import ru.veiman.config.WeatherConfig;
import ru.veiman.domain.City;
import ru.veiman.provider.WeatherProviderFactory;
import ru.veiman.repository.CityRepository;
import ru.veiman.repository.WeatherRepository;
import ru.veiman.task.WeatherTask;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 * Created by veiman on 19.08.15.
 */
@EnableAutoConfiguration
@ComponentScan
//@EnableScheduling
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static boolean hasErrorInProperties = false;
    @Inject
    WeatherConfig weatherConfig;
    @Inject
    CityRepository cityRepository;
    @Inject
    WeatherRepository weatherRepository;
    private static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        log.info("Start Application");
        applicationContext = SpringApplication.run(Application.class, args);
//        SpringApplication.exit(applicationContext);
    }

    @PostConstruct
    public void initApplication() throws IOException {
        if (weatherConfigValidate(weatherConfig)) {
            // add in database all cities from config
            List<WeatherConfig.CityLocal> cityLocals = weatherConfig.getCities();
            for (WeatherConfig.CityLocal cityLocal : cityLocals) {
                City city = new City(cityLocal.getName(), cityLocal.getCountry());
                log.debug(city.toString());
                cityRepository.saveAndFlush(city);
            }
            long period = Long.parseLong(weatherConfig.getPeriod());
            log.debug("period: " + period);

            // Запускаем переодическую задачу
            WeatherTask weatherTask = new WeatherTask(weatherConfig, cityRepository, weatherRepository);
            ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//        taskScheduler.setPoolSize(5);
            taskScheduler.setDaemon(false);
            taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
            taskScheduler.initialize();


            taskScheduler.scheduleWithFixedDelay(weatherTask, period);
        }

        else {
            log.error("Ошибка в конфигурационном файле.");
        }

    }

    private boolean weatherConfigValidate(WeatherConfig weatherConfig) {
        try {
            Long.parseLong(weatherConfig.getPeriod());
        }
        catch (NumberFormatException e) {
            log.error("Период должен быть целым числом");
            return false;
        }

        // проверяем есть ли у нас такие провайдеры
        for (String provider : weatherConfig.getProviders()) {
            if (!WeatherProviderFactory.checkExistProvider(provider)) {
                log.error("Не найден провайдер");
                return false;
            }
        }
        return true;
    }

}
