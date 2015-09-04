package ru.veiman.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.veiman.domain.City;
import ru.veiman.domain.Weather;
import ru.veiman.repository.WeatherRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by veiman. (28.08.15 15:57)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ru.veiman.Application.class)
@WebAppConfiguration
@IntegrationTest
public class WeatherResourceTest {

    public static final String DEFAULT_PROVIDER = "testProvider";
    MockMvc rest;

    private WeatherRepository weatherRepository = Mockito.mock(WeatherRepository.class);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WeatherResource weatherResource = new WeatherResource();
        ReflectionTestUtils.setField(weatherResource, "weatherRepository", weatherRepository);
        this.rest = MockMvcBuilders.standaloneSetup(weatherResource).build();

        City magnitogorsk = new City("Magnitogorsk", "RU");
        magnitogorsk.setId(0);
        City yekaterinburg = new City("Yekaterinburg", "RU");
        yekaterinburg.setId(1);
        List<Weather> weathers = new ArrayList<>();
        Weather weather1 = Weather.newBuilder()
                .setCity(magnitogorsk)
                .setProviderName(DEFAULT_PROVIDER)
                .setTemperature(1)
                .build();

        Weather weather2 = Weather.newBuilder()
                .setCity(yekaterinburg)
                .setProviderName(DEFAULT_PROVIDER)
                .setTemperature(5)
                .build();

        Weather weather3 = Weather.newBuilder()
                .setCity(magnitogorsk)
                .setProviderName(DEFAULT_PROVIDER)
                .setTemperature(2)
                .build();

        weathers.add(weather1);
        weathers.add(weather2);
        weathers.add(weather3);

        Mockito.when(weatherRepository.findByProviderName(DEFAULT_PROVIDER)).thenReturn(weathers);
    }

    @Test
    @Transactional
    public void testGetWeatherByCity() throws Exception {
        rest.perform(get("/rest/{provider}/weatherByCity", DEFAULT_PROVIDER))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasKey("Magnitogorsk (RU)")))
                .andExpect(jsonPath("$", hasKey("Yekaterinburg (RU)")))
        ;
    }
}