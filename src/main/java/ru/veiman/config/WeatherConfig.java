package ru.veiman.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veiman on 23.08.15.
 */
@Configuration
@ConfigurationProperties(prefix = "weather")
public class WeatherConfig {

    private static final Logger log = LoggerFactory.getLogger(WeatherConfig.class);

    private List<String> providers = new ArrayList<String>();
    private List<CityLocal> cities = new ArrayList<CityLocal>();

    // переодичность запуска сбора погоды
    private String period;


    public List<String> getProviders() {
        return providers;
    }

    public void setProviders(List<String> providers)  {
        this.providers = providers;
    }

    public List<CityLocal> getCities() {
        return cities;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period)  {
            this.period = period;
    }

    public static class CityLocal {
        // @NotEmpty
        String name;

        // @NotEmpty
        String country;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("CityLocal{");
            sb.append("name='").append(name).append('\'');
            sb.append(", country='").append(country).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
