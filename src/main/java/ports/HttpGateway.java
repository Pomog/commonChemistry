package ports;

import java.net.URI;
import java.util.Map;

public interface HttpGateway {
    String get(URI uri, Map<String, String> headers);
}
