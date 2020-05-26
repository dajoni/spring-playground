package dajoni.spring.playground;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "service.playground")
@Data
public class PlaygroundProperties {
    @NotBlank
    private String someUrl;
    @NotEmpty
    private List<String> names;

    private List<ApplicationUser> applicationUsers = new ArrayList<>();

}
