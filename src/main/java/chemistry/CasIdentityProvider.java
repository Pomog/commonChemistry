package chemistry;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.ChemicalIdentity;
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
        return Optional.empty();
    }
    
    @Override
    public Optional<ChemicalIdentity> resolveByCasRn(String casRn) {
        return Optional.empty();
    }
}
