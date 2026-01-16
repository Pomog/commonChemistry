package adapters.http;

import ports.HttpGateway;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class JavaHttpGateway implements HttpGateway {
    
    private final HttpClient http;
    
    public JavaHttpGateway(HttpClient http) {
        this.http = http;
    }
    
    @Override
    public String get(URI uri, Map<String, String> headers) {
        try {
            HttpRequest.Builder b = HttpRequest.newBuilder(uri).GET();
            for (Map.Entry<String, String> e : headers.entrySet()) {
                b.header(e.getKey(), e.getValue());
            }
            HttpResponse<String> resp = http.send(b.build(), HttpResponse.BodyHandlers.ofString());
            
            if (resp.statusCode() / 100 != 2) {
                throw new RuntimeException("HTTP " + resp.statusCode() + " for " + uri + "\nBody: " + resp.body());
            }
            return resp.body();
        } catch (Exception e) {
            throw new RuntimeException("HTTP GET failed: " + uri, e);
        }
    }
}
