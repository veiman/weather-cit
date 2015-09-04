package ru.veiman.provider.impl.wunderground.dto.request2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by veiman on 22.08.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootDTO {
    private ObservationDTO current_observation;

    public ObservationDTO getCurrent_observation() {
        return current_observation;
    }

    public void setCurrent_observation(ObservationDTO current_observation) {
        this.current_observation = current_observation;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RootDTO{");
        sb.append("current_observation=").append(current_observation);
        sb.append('}');
        return sb.toString();
    }
}
