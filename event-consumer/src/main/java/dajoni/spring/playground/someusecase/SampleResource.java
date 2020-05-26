package dajoni.spring.playground.someusecase;

import dajoni.spring.playground.someusecase.model.Sample;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
public class SampleResource extends RepresentationModel<SampleResource> {
    private final Sample sample;

    public SampleResource(Sample sample) {
        this.sample = sample;

        add(linkTo(SamplesController.class).withRel("samples"));
        add(linkTo(methodOn(SamplesController.class).getSample(sample.getSet(), sample.getId())).withSelfRel());
        add(linkTo(methodOn(SamplesController.class).getSampleSet(sample.getSet())).withRel("collection"));
    }
}
