package ru.veiman.provider.impl.wunderground.dto.request1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by veiman on 22.08.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RootDTO {
    private ResponseDTO response;

    public ResponseDTO getResponse() {
        return response;
    }

    public void setResponse(ResponseDTO response) {
        this.response = response;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RootDTO{");
        sb.append("response=").append(response);
        sb.append('}');
        return sb.toString();
    }
}
