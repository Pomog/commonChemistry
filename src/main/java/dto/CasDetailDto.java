package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CasDetailDto {
    
    public String uri;
    public String rn;
    public String name;
    
    public String inchi;
    
    @JsonProperty("inchiKey")
    public String inchiKey;
    
    // JSON provides molecularMass as a string; BigDecimal is the safest representation.
    @JsonProperty("molecularMass")
    public BigDecimal molecularMass;
    
    public List<String> synonyms;
    
    // Contains SVG strings
    public List<String> images;
    
    @JsonProperty("experimentalProperties")
    public List<ExperimentalProperty> experimentalProperties;
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ExperimentalProperty {
        public String name;
        public String property;
        public Integer sourceNumber;
    }
}
