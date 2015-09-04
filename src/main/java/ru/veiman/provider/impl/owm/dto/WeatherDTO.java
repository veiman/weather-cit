package ru.veiman.provider.impl.owm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by veiman on 21.08.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDTO {

    private int id;
    private String main;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("WeatherDTO{");
        sb.append("id=").append(id);
        sb.append(", main='").append(main).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
