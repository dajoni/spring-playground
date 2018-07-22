package dajoni.spring.playground.someusecase;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(SamplesController.class)
@ExtendWith(SpringExtension.class)
class SamplesControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void test_get_basic_sample() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/samples/some-set/15.3.5"))
                .andExpect(status().isOk());
    }

    @Test
    void test_check_for_basic_properties() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/samples/some-set/15.3.5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("sample.id", is("15.3.5")));
    }

    @Test
    void test_should_return_self_rel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/samples/some-set/15.3.5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_links.self.href", containsString("15.3.5")))
                .andExpect(jsonPath("_links.self.href", containsString("some-set")));
    }

    @Test
    void test_should_return_set_rel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/samples/some-set/15.3.5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_links.collection.href", containsString("some-set")));
    }

    @Test
    void test_should_return_samples_rel() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/samples/some-set/15.3.5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_links.samples.href", containsString("samples")));
    }



    @Test
    @Disabled
    void test_should_fail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/samples/some-set/15.3.5"))
                .andExpect(status().isNoContent());

    }
}