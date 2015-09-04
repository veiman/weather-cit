package ru.veiman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.veiman.domain.City;
import ru.veiman.domain.Weather;

import java.util.List;

/**
 * Created by veiman on 20.08.15.
 */
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    List<Weather> findByCity(City city);

    List<Weather> findByProviderName(String providerName);

    List<Weather> findByProviderNameAndCity(String providerName, City city);
}
