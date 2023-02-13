package letscode.sarafan.Conrollers;


import com.fasterxml.jackson.databind.ObjectMapper;
import letscode.sarafan.controller.SamokatController;
import letscode.sarafan.domain.Samokat;
import letscode.sarafan.repo.SamokatRepo;
import lombok.var;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SamokatControllerTest {

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private SamokatRepo repository;
    @Autowired
    private MockMvc mockMvc;


    //View
    @Test
    public void Get_samokat() throws Exception {
        Samokat p1 = new Samokat(111L, "Jane","fre", "fre","fre","fre");
        Samokat p2 = new Samokat(112L, "Joe", "fre", "fre","fre","fre");
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(p1, p2));
        mockMvc.perform(
                        get("/samokat"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(p1, p2))));
        ;
    }


    //Delete
    @Test
    public void delete_samokat() throws Exception {
        Samokat samokat = new Samokat(121L, "Jane","fre", "fre","fre","fre");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(samokat));
        mockMvc.perform(
                delete("/samokat/121"))
                .andDo(print())
                .andExpect(status().isOk());
    }



    //Edit
    @Test
    public void edit_samokat() throws Exception {
        Samokat p1 = new Samokat(111L, "Jane","fre", "fre","fre","fre");
        Mockito.when(repository.save(Mockito.any())).thenReturn(p1);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(p1));
        mockMvc.perform(
                        put("/samokat/111")
                                .content(objectMapper.writeValueAsString(new Samokat(111L, "Jane","fre", "fre","fre","fre")))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("111"))
                .andExpect( jsonPath("$.name_samokat").value("Jane"));

    }



    @Test
    public void givenPerson_whenAdd_thenStatus201andPersonReturned() throws Exception {
        Samokat p1 = new Samokat(111L, "Jane","fre", "fre","fre","fre");
        Mockito.when(repository.save(Mockito.any())).thenReturn(p1);
        mockMvc.perform(
                        post("/samokat")
                                .content(objectMapper.writeValueAsString(p1))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p1)));
    }



}


