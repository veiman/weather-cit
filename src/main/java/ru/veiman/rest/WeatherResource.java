package ru.veiman.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.veiman.domain.Weather;
import ru.veiman.repository.WeatherRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by veiman on 22.08.15.
 */
@RestController
@RequestMapping("/rest")
public class WeatherResource {
    private final Logger log = LoggerFactory.getLogger(WeatherResource.class);

    @Inject
    WeatherRepository weatherRepository;

    @RequestMapping(value = "/{provider}/weatherByCity",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Weather>> getWeatherByCity(@PathVariable String provider) {
        log.info("REST request to get Weather with grouping by City and Provider is {}", provider);
        List<Weather> weathers = weatherRepository.findByProviderName(provider);
        log.debug(weathers.toString());
        Map<String, List<Weather>> collect = weathers
                .stream()
                        //.map(city -> city.getId())
                .collect(Collectors.groupingBy(
                        weather -> weather.getCity().getName() + " (" + weather.getCity().getCountry() + ")"
                ));
        log.debug(collect.toString());
        return collect;
    }
}
