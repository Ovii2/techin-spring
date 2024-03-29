package hellopath;

import fi.helsinki.cs.tmc.edutestutils.Points;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Points("01-05")
public class HelloPathTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void pathTest() throws Exception {
        this.mockMvc.perform(get("/path"))
                .andExpect(content().string("Correct!"));
    }

    @Test
    public void invalidPathTest() throws Exception {
        this.mockMvc.perform(get("/paths"))
                .andExpect(status().is(404));
    }

    @Test
    public void invalidPath2Test() throws Exception {
        this.mockMvc.perform(get("/" + UUID.randomUUID().toString()))
                .andExpect(status().is(404));
    }
}
