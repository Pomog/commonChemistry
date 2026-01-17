package ports;

import io.github.cdimascio.dotenv.Dotenv;

public class CasEnvConfig implements CasConfig{
    private final String baseUrl;
    private final String apiKey;
    
    public CasEnvConfig(String baseUrl, String apiKey){
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }
    
    public static CasConfig fromDotEnv(){
        Dotenv dotenv = Dotenv.load();
        String key = dotenv.get("CCC_API_KEY");
        if (key == null || key.isBlank()) {
            throw new IllegalStateException("CCC_API_KEY is missing in .env");
        }
        return new CasEnvConfig("https://commonchemistry.cas.org/api", key);
    }
    @Override
    public String baseUrl() {
        return null;
    }
    
    @Override
    public String apiKey() {
        return null;
    }
}
