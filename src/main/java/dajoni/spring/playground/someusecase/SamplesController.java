package dajoni.spring.playground.someusecase;

import dajoni.spring.playground.someusecase.model.Sample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/someusecase")
@Slf4j
public class SamplesController {

    @GetMapping("/{name}/{id}")
    public SampleResource getSample(@PathVariable String name, @PathVariable String id) {
        log.info("Got request {}, {}", name, id);
        return new SampleResource(Sample.builder().id(id).set(name).build());
    }
}
