package ru.veiman.provider.impl.owm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by veiman on 21.08.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootDTO {
    private List<WeatherDTO> weather;
    private MainDTO main;
    private double visibility;
    private WindDTO wind;

    public List<WeatherDTO> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDTO> weather) {
        this.weather = weather;
    }

    public MainDTO getMain() {
        return main;
    }

    public void setMain(MainDTO main) {
        this.main = main;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public WindDTO getWind() {
        return wind;
    }

    public void setWind(WindDTO wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RootDTO{");
        sb.append("weather=").append(weather);
        sb.append(", main=").append(main);
        sb.append(", visibility=").append(visibility);
        sb.append(", wind=").append(wind);
        sb.append('}');
        return sb.toString();
    }
}
