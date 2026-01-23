package org.pomog;

import adapters.http.JavaHttpGateway;
import chemistry.CasIdentityProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import ports.CasConfig;
import ports.CasEnvConfig;
import ports.HttpGateway;

import java.net.http.HttpClient;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        
        Dotenv dotenv = Dotenv.load();
        System.out.println("CCC_API_KEY from env = " + dotenv.get("CCC_API_KEY"));
        
        CasConfig cfg = CasEnvConfig.fromDotEnv();
        
        HttpGateway http = new JavaHttpGateway(HttpClient.newHttpClient());
        ObjectMapper mapper = new ObjectMapper();
        
        CasIdentityProvider idp = new CasIdentityProvider(http, cfg, mapper);
        
        System.out.println(idp.resolveByName("caffeine").orElse(null));
        System.out.println(idp.resolveByCasRn("58-08-2").orElse(null));
    }
}