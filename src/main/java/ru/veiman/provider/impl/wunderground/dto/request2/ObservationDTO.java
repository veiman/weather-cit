package ru.veiman.provider.impl.wunderground.dto.request2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by veiman on 22.08.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObservationDTO {
    private String weather;
    private int temp_c;
    private String relative_humidity;
    private int wind_kph;
    private String wind_dir;
    private String pressure_mb;
    private String visibility_km;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(int temp_c) {
        this.temp_c = temp_c;
    }

    public String getRelative_humidity() {
        return relative_humidity;
    }

    public void setRelative_humidity(String relative_humidity) {
        this.relative_humidity = relative_humidity;
    }

    public int getWind_kph() {
        return wind_kph;
    }

    public void setWind_kph(int wind_kph) {
        this.wind_kph = wind_kph;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public String getPressure_mb() {
        return pressure_mb;
    }

    public void setPressure_mb(String pressure_mb) {
        this.pressure_mb = pressure_mb;
    }

    public String getVisibility_km() {
        return visibility_km;
    }

    public void setVisibility_km(String visibility_km) {
        this.visibility_km = visibility_km;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ObservationDTO{");
        sb.append("weather='").append(weather).append('\'');
        sb.append(", temp_c=").append(temp_c);
        sb.append(", relative_humidity='").append(relative_humidity).append('\'');
        sb.append(", wind_kph=").append(wind_kph);
        sb.append(", wind_dir='").append(wind_dir).append('\'');
        sb.append(", pressure_mb='").append(pressure_mb).append('\'');
        sb.append(", visibility_km='").append(visibility_km).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
