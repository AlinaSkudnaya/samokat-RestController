package Alina.sarafan.Conrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import Alina.sarafan.domain.Arnd;
import Alina.sarafan.repo.ArendaRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ArendaControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ArendaRepo repository;
    @Autowired
    private MockMvc mockMvc;


    //View
    @Test
    public void Get_arenda() throws Exception {
        Arnd p1 = new Arnd(111L, "Jane","fre", "fre","fre","fre","fre","fre","fre","fre");
        Arnd p2 = new Arnd(112L, "Joe", "fre", "fre","fre","fre","fre","fre","fre","fre");
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(p1, p2));
        mockMvc.perform(
                        get("/Arenda"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(p1, p2))));
        ;
    }

    @Test
    public void delete_samokat() throws Exception {
        Arnd samokat = new Arnd(121L, "Jane","fre", "fre","fre","fre","fre","fre","fre","fre");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(samokat));
        mockMvc.perform(
                        delete("/Arenda/121"))
                .andDo(print())
                .andExpect(status().isOk());
    }



//    Edit
    @Test
    public void edit_ar() throws Exception {
        Arnd p1 = new Arnd(114L, "Jane","fre", "fre","fre","fre","fre","fre","fre","fre");
        Mockito.when(repository.save(Mockito.any())).thenReturn(p1);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(p1));
        mockMvc.perform(
                        put("/Arenda/114")
                                .content(objectMapper.writeValueAsString(new Arnd(114L, "Jake","fre", "fre","fre","fre","fre","fre","fre","fre")))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("114"))
                .andExpect( jsonPath("$.name_samokat").value("Jake"));

    }



    @Test
    public void add_ar() throws Exception {
        Arnd p1 = new Arnd(114L, "Jane","fre", "fre","fre","fre","fre","fre","fre","fre");
        Mockito.when(repository.save(Mockito.any())).thenReturn(p1);
        mockMvc.perform(
                        post("/Arenda")
                                .content(objectMapper.writeValueAsString(p1))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p1)));
    }


}
