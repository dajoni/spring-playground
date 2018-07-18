package dajoni.spring.playground.someusecase.model;

import lombok.Data;

import java.util.Map;

@Data
public class SampleSet {

    private final String name;
    private final String url;

    private Map<String,Sample> samples;

}
