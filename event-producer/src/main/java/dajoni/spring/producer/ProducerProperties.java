package dajoni.spring.producer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service.producer")
@Data
public class ProducerProperties {
    String fromDate;
    String toDate;
}
