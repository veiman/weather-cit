package ru.veiman.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.veiman.config.WeatherConfig;
import ru.veiman.provider.WeatherProviderFactory;
import ru.veiman.provider.exception.ProviderNotFoundException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by veiman. (27.08.15 13:25)
 */
@RestController
@RequestMapping("/rest")
public class ProviderResource {

    private static final Logger log = LoggerFactory.getLogger(ProviderResource.class);

    @Inject
    private WeatherConfig weatherConfig;

    @RequestMapping(value = "/providerNames",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getProviderNames() {
        log.info("REST Call /rest/providerNames");
        List<String> providers = weatherConfig.getProviders();
        List<String> result = new ArrayList<>();
        for (String provider : providers) {
            try {
                result.add(
                        WeatherProviderFactory.getWeatherProvider(provider).getProviderName()
                );
            }
            catch (ProviderNotFoundException e) {
                log.error("Not find provider {}", provider);
            }
        }
        log.debug("providers: "+result.toString());
        return result;
    }

    @RequestMapping(value = "/providers",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getProviders() {
        log.info("REST Call /rest/providers");
        return weatherConfig.getProviders();
    }
}
