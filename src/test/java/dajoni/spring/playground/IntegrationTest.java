package dajoni.spring.playground;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@Slf4j
public class IntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void test_get_urls_for_a_sample() throws Exception {
        ResponseEntity<String> entity = restTemplate.getForEntity("/samples/some-set/15.3.5", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void test_get_root_anonymous_is_unauthorized() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity("/", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void test_get_root_as_admin_is_forbidden() throws Exception {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("serviceadmin", "admin123")
                .getForEntity("/", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void test_get_actuator_is_unauthorized() throws Exception {
        ResponseEntity<String> response = restTemplate
                .getForEntity("/actuator", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void test_get_actuator_as_monitor_is_authorized() throws Exception {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("monitor", "monitor_me")
                .getForEntity("/actuator", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
