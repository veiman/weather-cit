package ru.veiman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.veiman.domain.City;

/**
 * Created by veiman on 20.08.15.
 */
public interface CityRepository extends JpaRepository<City, Long> {
    City findByNameAndCountry(String name, String country);
}
