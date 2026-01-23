package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CasSearchResponse {
    public Integer count;
    public List<CasSearchResult> results;
}
