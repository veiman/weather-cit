package ru.veiman.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.veiman.domain.City;
import ru.veiman.repository.CityRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;


/**
 * Created by veiman. (28.08.15 11:02)
 */
@RestController
@RequestMapping("/rest")
public class CityResource {
    private static final Logger log = LoggerFactory.getLogger(ProviderResource.class);

    @Inject
    CityRepository cityRepository;

    @RequestMapping(value = "/city",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<City> getAll() {
        log.info("REST request to get all cities");
        return cityRepository.findAll();
    }

    @RequestMapping(value = "/city/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> get(@PathVariable Long id) {
        log.info("REST request to get city");
        return Optional.ofNullable(cityRepository.findOne(id))
                .map(city -> new ResponseEntity<>(
                        city,
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
