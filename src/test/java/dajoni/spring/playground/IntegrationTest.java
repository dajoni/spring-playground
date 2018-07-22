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

}
