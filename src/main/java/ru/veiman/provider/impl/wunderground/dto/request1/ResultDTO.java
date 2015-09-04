package ru.veiman.provider.impl.wunderground.dto.request1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by veiman on 22.08.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDTO {
    private String name;
    private String country_iso3166;
    private String zmw;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZmw() {
        return zmw;
    }

    public void setZmw(String zmw) {
        this.zmw = zmw;
    }

    public String getCountry_iso3166() {
        return country_iso3166;
    }

    public void setCountry_iso3166(String country_iso3166) {
        this.country_iso3166 = country_iso3166;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResultDTO{");
        sb.append("name='").append(name).append('\'');
        sb.append(", country_iso3166='").append(country_iso3166).append('\'');
        sb.append(", zmw='").append(zmw).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
