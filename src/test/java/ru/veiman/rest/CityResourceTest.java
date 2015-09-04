package ru.veiman.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.veiman.repository.CityRepository;

import javax.inject.Inject;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by veiman. (28.08.15 13:10)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ru.veiman.Application.class)
@WebAppConfiguration
@IntegrationTest
public class CityResourceTest {

    @Inject
    private CityRepository cityRepository;

    private MockMvc rest;

    @Before
    public void setup() {
        CityResource cityResource = new CityResource();
        ReflectionTestUtils.setField(cityResource, "cityRepository", cityRepository);
        this.rest = MockMvcBuilders.standaloneSetup(cityResource).build();
    }



    @Test
    public void testGetAll() throws Exception {
        rest.perform(get("/rest/city").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
//                .andExpect(jsonPath("$", hasSize(4)))
        ;
    }

    @Test
    public void testGet() throws Exception {
        rest.perform(get("/rest/city/{id}",1).accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",is(1)));
    }
}