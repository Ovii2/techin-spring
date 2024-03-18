package lt.techin.cinema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
public class CinemaTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testEndpoint() throws Exception {
        mockMvc.perform(get("/seats"))
                .andExpect(status().isOk());
    }


    @Test
    void testEndpointAvailableSeats() throws Exception {
        mockMvc.perform(get("/seats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rows").value(9))
                .andExpect(jsonPath("$.columns").value(9))
                .andExpect(jsonPath("$.seats.length()").value(81));
    }

    @Test
    void testPurchaseTicket() throws Exception {
        Map<String, Object> ticketRequest = new HashMap<>();
        ticketRequest.put("row", 5);
        ticketRequest.put("column", 1);

        mockMvc.perform(post("/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ticketRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticket.row").value(5))
                .andExpect(jsonPath("$.ticket.column").value(1))
                .andExpect(jsonPath("$.ticket.price").value(8));
    }

    @Test
    void testReturnTicket() throws Exception {
        // First, purchase a ticket to get a valid token
        Map<String, Object> purchaseRequest = Map.of("row", 2, "column", 5);
        String purchaseResponseBody = mockMvc.perform(post("/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(purchaseRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Map<String, Object> purchaseResponse = objectMapper.readValue(purchaseResponseBody, Map.class);
        String token = (String) purchaseResponse.get("token");


        Map<String, String> returnRequest = Map.of("token", token);
        mockMvc.perform(post("/return")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(returnRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticket.row").value(2))
                .andExpect(jsonPath("$.ticket.column").value(5))
                .andExpect(jsonPath("$.ticket.price").value(10));



    }


    @Test
    void testReturnWrongTicket() throws Exception {
        String wrongToken = UUID.randomUUID().toString();
        Map<String, String> wrongReturnRequest = Map.of("token", wrongToken);
        mockMvc.perform(post("/return")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(wrongReturnRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Wrong token!"));
    }


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

        Map<String, Integer> outOfBoundsRequest = Map.of("row", -1, "column", 0);
        String outOfBoundsRequestBody = objectMapper.writeValueAsString(outOfBoundsRequest);


        mockMvc.perform(post("/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(outOfBoundsRequestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("The number of a row or a column is out of bounds!"));


    }
}