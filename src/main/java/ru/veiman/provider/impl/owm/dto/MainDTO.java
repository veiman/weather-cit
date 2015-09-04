package ru.veiman.provider.impl.owm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by veiman on 21.08.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainDTO {
    private double temp;
    private int pressure;
    private int humidity;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MainDTO{");
        sb.append("temp=").append(temp);
        sb.append(", pressure=").append(pressure);
        sb.append(", humidity=").append(humidity);
        sb.append('}');
        return sb.toString();
    }
}
