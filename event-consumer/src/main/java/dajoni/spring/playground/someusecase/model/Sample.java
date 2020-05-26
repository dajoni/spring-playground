package dajoni.spring.playground.someusecase.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sample {
    private final String id;
    private final String set;
    private final String someData;
}
