import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class PermissionService {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private static final String API_URL="http://host.docker.internal:8081/permissions?userId=";
    public static List<String> findAllUserPermissions(String url){
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return REST_TEMPLATE.exchange(url, HttpMethod.GET,entity, List.class).getBody();
    }

}
