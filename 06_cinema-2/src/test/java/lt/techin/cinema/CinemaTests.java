package lt.techin.cinema;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CinemaTests {

    @Autowired
    private MockMvc mockMvc;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testEndpoint() throws Exception {
        mockMvc.perform(get("/seats"))
                .andExpect(status().isOk());
    }

    @Test
    public void testEndpointAvailableSeats() throws Exception {
        mockMvc.perform(get("/seats"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.columns").value(9))
                .andExpect(jsonPath("$.rows").value(9))
                .andExpect(jsonPath("$.seats.length()").value(81));

    }

    @Test
    public void testPurchaseTicket() throws Exception {

        Map<String, Integer> ticketRequest = Map.of("row", 1, "column", 1);
        String requestBody = objectMapper.writeValueAsString(ticketRequest);

        mockMvc.perform(post("/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.row").value(1))
                .andExpect(jsonPath("$.column").value(1))
                .andExpect(jsonPath("$.price").value(10));
    }

    @Test
    public void testErrorMessageThatTicketHasBeenPurchased() throws Exception {
        Map<String, Integer> ticketRequest = Map.of("row", 2, "column", 2);
        String requestBody = objectMapper.writeValueAsString(ticketRequest);
        mockMvc.perform(post("/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));


        mockMvc.perform(post("/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("The ticket has been already purchased!"));
    }

    @Test
    public void testErrorMessageThatNumbersOutOfBounds() throws Exception {

        Map<String, Integer> outOfBoundsRequest = Map.of("row", 10, "column", 1);
        String outOfBoundsRequestBody = objectMapper.writeValueAsString(outOfBoundsRequest);


        mockMvc.perform(post("/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(outOfBoundsRequestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("The number of a row or a column is out of bounds!"));


    }
}
