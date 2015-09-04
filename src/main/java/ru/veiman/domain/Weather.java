package ru.veiman.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import ru.veiman.domain.util.CustomDateTimeDeserializer;
import ru.veiman.domain.util.CustomDateTimeSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by veiman on 20.08.15.
 */
@Entity
@Table(name = "WEATHER")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private double temperature;

    @NotNull
    private int humidity;

    // давление
    private double pressure;

    @NotNull
    @ManyToOne
    private City city;

    // видимость в км (может быть N/A)
    private String visibility;

    // скорость ветра
    @Column(name = "wind_speed")
    private double windSpeed;

    // Направление ветра
    @Column(name = "wind_direction")
    private String windDirection;

    // описание (ясно, облачно, дождь)
    private String description;

    // имя провайдера
    private String providerName;

    @Column(name = "weather_time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
//    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
//    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime dateTime;

    public Weather() {
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weather weather = (Weather) o;

        if ( ! Objects.equals(id, weather.id)) return false;

        return true;

    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Weather{");
        sb.append("id=").append(id);
        sb.append(", temperature=").append(temperature);
        sb.append(", humidity=").append(humidity);
        sb.append(", pressure=").append(pressure);
        sb.append(", city=").append(city);
        sb.append(", visibility=").append(visibility);
        sb.append(", windSpeed=").append(windSpeed);
        sb.append(", windDirection='").append(windDirection).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", providerName='").append(providerName).append('\'');
        sb.append(", dateTime=").append(dateTime);
        sb.append('}');
        return sb.toString();
    }

    public static Builder newBuilder() {
        return new Weather().new Builder();
    }

    /**
     * Builder for Weather bean
     */
    public class Builder {
        private Builder() {
        }

        public Builder setTemperature(double temperature) {
            Weather.this.temperature = temperature;
            return this;
        }

        public Builder setHumidity(int humidity) {
            Weather.this.humidity = humidity;
            return this;
        }

        public Builder setPressure(double pressure) {
            Weather.this.pressure = pressure;
            return this;
        }

        public Builder setCity(City city) {
            Weather.this.city = city;
            return this;
        }

        public Builder setVisibility(String visibility) {
            Weather.this.visibility = visibility;
            return this;
        }

        public Builder setWindSpeed(double windSpeed) {
            Weather.this.windSpeed = windSpeed;
            return this;
        }

        public Builder setWindDirection(String windDirection) {
            Weather.this.windDirection = windDirection;
            return  this;
        }

        public Builder setDescription(String description) {
            Weather.this.description = description;
            return this;
        }

        public Builder setProviderName(String providerName) {
            Weather.this.providerName = providerName;
            return this;
        }

        public Weather build() {
            return Weather.this;
        }
    }

}
