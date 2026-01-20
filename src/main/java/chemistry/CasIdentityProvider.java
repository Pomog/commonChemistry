package chemistry;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.ChemicalIdentity;
import ports.CasConfig;
import ports.HttpGateway;
import ports.IdentityProvider;

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
    
    @Override
    public Optional<ChemicalIdentity> resolveByName(String name) {
        return Optional.empty();
    }
    
    @Override
    public Optional<ChemicalIdentity> resolveByCasRn(String casRn) {
        return Optional.empty();
    }
}
