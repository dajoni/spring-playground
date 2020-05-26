package dajoni.spring.playground.someusecase;

import dajoni.spring.playground.someusecase.model.Sample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/samples")
@Slf4j
public class SamplesController {

    @GetMapping("/{collection}/{id}")
    public SampleResource getSample(@PathVariable String collection, @PathVariable String id) {
        log.info("Got request {}, {}", collection, id);
        return new SampleResource(Sample.builder().id(id).set(collection).build());
    }

    @GetMapping("/{collection}")
    public List<SampleResource> getSampleSet(@PathVariable String collection) {
        log.info("Got request {}", collection);
        List<SampleResource> results = new ArrayList<>();
        results.add(new SampleResource(Sample.builder().id("1.3.5").set(collection).build()));
        results.add(new SampleResource(Sample.builder().id("2.3.4").set(collection).build()));
        results.add(new SampleResource(Sample.builder().id("6.7.5").set(collection).build()));
        return results;
    }


}
