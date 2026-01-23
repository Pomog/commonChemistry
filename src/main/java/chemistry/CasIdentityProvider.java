package chemistry;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.ChemicalIdentity;
import dto.CasDetailDto;
import dto.CasSearchResponse;
import dto.CasSearchResult;
import ports.CasConfig;
import ports.HttpGateway;
import ports.IdentityProvider;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;



public class CasIdentityProvider implements IdentityProvider {
    private final HttpGateway http;
    private final CasConfig config;
    private final ObjectMapper mapper;
    
    public CasIdentityProvider(HttpGateway http, CasConfig config, ObjectMapper mapper) {
        this.http = http;
        this.config = config;
        this.mapper = mapper;
    }
    
    private Map<String, String> headers() {
        return Map.of(
                "X-API-KEY", config.apiKey(),
                "Accept", "application/json"
        );
    }
    
    private URI searchUri(String q) {
        String encoded = URLEncoder.encode(q, StandardCharsets.UTF_8);
        return URI.create(config.baseUrl() + "/search?q=" + encoded);
    }
    
    private URI detailUri(String casRn) {
        String encoded = URLEncoder.encode(casRn, StandardCharsets.UTF_8);
        return URI.create(config.baseUrl() + "/detail?cas_rn=" + encoded);
    }
    
    @Override
    public Optional<ChemicalIdentity> resolveByName(String name) {
        if (name == null || name.isBlank()) return Optional.empty();
        
        try {
            String json = http.get(searchUri(name), headers());
            CasSearchResponse resp = mapper.readValue(json, CasSearchResponse.class);
            
            if (resp == null || resp.results == null || resp.results.isEmpty()) {
                return Optional.empty();
            }
            
            CasSearchResult first = resp.results.get(0);
            if (first == null || first.rn == null || first.rn.isBlank()) {
                return Optional.empty();
            }
            
            return resolveByCasRn(first.rn)
                    .map(ci -> {
                        if (ci.getDisplayName() == null || ci.getDisplayName().isBlank()) {
                            ci.setDisplayName(first.name);
                        }
                        return ci;
                    });
            
        } catch (Exception e) {
            throw new RuntimeException("CAS resolveByName failed: " + name, e);
        }
    }
        
    @Override
    public Optional<ChemicalIdentity> resolveByCasRn(String casRn) {
        if (casRn == null || casRn.isBlank()) return Optional.empty();
        
        try {
            String json = http.get(detailUri(casRn), headers());
            CasDetailDto dto = mapper.readValue(json, CasDetailDto.class);
            
            if (dto == null || dto.rn == null || dto.rn.isBlank()) {
                return Optional.empty();
            }
            
            ChemicalIdentity identity = new ChemicalIdentity();
            identity.setCasRn(dto.rn);
            identity.setInchiKey(dto.inchiKey);
            identity.setDisplayName(dto.name);
            
            identity.setPubchemCid(null);
            
            return Optional.of(identity);
            
        } catch (Exception e) {
            throw new RuntimeException("CAS resolveByCasRn failed: " + casRn, e);
        }
    }

}
