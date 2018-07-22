package dajoni.spring.playground.someusecase;

import dajoni.spring.playground.someusecase.model.Sample;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Getter
public class SampleResource extends ResourceSupport {
    private final Sample sample;

    public SampleResource(Sample sample) {
        this.sample = sample;

        add(linkTo(SamplesController.class).withRel("samples"));
        add(linkTo(methodOn(SamplesController.class).getSample(sample.getSet(), sample.getId())).withSelfRel());
        add(linkTo(methodOn(SamplesController.class).getSampleSet(sample.getSet())).withRel("collection"));
    }
}
