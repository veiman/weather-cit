package ru.veiman.provider.impl.wunderground.dto.request1;

import java.util.List;

/**
 * Created by veiman on 22.08.15.
 */
public class ResponseDTO {
    private List<ResultDTO> results;

    public List<ResultDTO> getResults() {
        return results;
    }

    public void setResults(List<ResultDTO> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResponseDTO{");
        sb.append("results=").append(results);
        sb.append('}');
        return sb.toString();
    }
}
