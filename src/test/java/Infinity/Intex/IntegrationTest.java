package Infinity.Intex;

import Infinity.Intex.dto.AdminDto;
import Infinity.Intex.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.class)
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static String token;

    private final String username = "xadicha";
    private final String password = "12345a";

    @Order(1)
    @Test
    public void addUser() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String user = null;

        try {
            user = objectMapper.writeValueAsString(new AdminDto(
                    null,
                    "XadichaBonu Qosimova",
                    username,
                    password,
                    null));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/add")
                .contentType("application/json")
                .content(user);

        String responseBody = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectReader reader = objectMapper.readerFor(new TypeReference<ResponseDto<Void>>() {});
        ResponseDto<Void> responseDto = reader.readValue(responseBody);
        Assertions.assertTrue(responseDto.getSuccess());
        Assertions.assertEquals(responseDto.getMessage(),"OK");
        Assertions.assertEquals(responseDto.getCode(),0);
    }
    @Order(2)
    @Test
    public void getToken() throws Exception {
        AdminDto adminDto = new AdminDto();
        adminDto.setUsername(username);
        adminDto.setPassword(password);

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(adminDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/token")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON);

        String responseBody = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectReader reader = objectMapper.readerFor(new TypeReference<ResponseDto<String>>() {});
        ResponseDto<String> responseDto = reader.readValue(responseBody);

        Assertions.assertNotNull(responseDto.getData());
        Assertions.assertTrue(responseDto.getSuccess());

        token = responseDto.getData();
    }
    @Order(3)
    @Test
    public void deleteUser() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/admin").param("name",username)
                .header("Authorization", "Bearer " + token);
        String response =  mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectReader objectReader = new ObjectMapper().readerFor(new TypeReference<ResponseDto<Integer>>() {});

        ResponseDto<Integer> responseDTO = objectReader.readValue(response);

        Assertions.assertTrue(responseDTO.getSuccess());
        Assertions.assertEquals(responseDTO.getMessage(),"OK");
        Assertions.assertNotNull(responseDTO.getData());
    }

}
