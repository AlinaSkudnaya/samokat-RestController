package Alina.sarafan.Conrollers;


import com.fasterxml.jackson.databind.ObjectMapper;
import Alina.sarafan.domain.Ordr;
import Alina.sarafan.repo.OrderRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestingOrders {

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrderRepo repository;
    @Autowired
    private MockMvc mockMvc;

    //View
    @Test
    public void Get_orders() throws Exception {
        Ordr p1=new Ordr(111L,"dk","dk","dk","dk","dk","dk","dk");
        Ordr p2=new Ordr(112L,"dk","dk","dk","dk","dk","dk","dk");

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(p1, p2));
        mockMvc.perform(
                        get("/Order"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(p1, p2))));
        ;
    }



    //Delete
    @Test
    public void orders_delete() throws Exception {
        Ordr samokat=new Ordr(121L,"dk","dk","dk","dk","dk","dk","dk");

        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(samokat));
        mockMvc.perform(
                        delete("/Order/121"))
                        .andExpect(status().isOk());
    }


    //Edit
    @Test
    public void edit_order() throws Exception {
        Ordr p1=new Ordr(121L,"dk","dk","dk","dk","dk","dk","dk");
        Mockito.when(repository.save(Mockito.any())).thenReturn(p1);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(p1));
        mockMvc.perform(
                        put("/Order/121")
                                .content(objectMapper.writeValueAsString(new Ordr(121L, "Jane","fre", "fre","fre","fre","fre","fre")))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("121"))
                .andExpect( jsonPath("$.name").value("Jane"));

    }



    @Test
    public void add_order() throws Exception {
        Ordr p1=new Ordr(121L,"dk","dk","dk","dk","dk","dk","dk");
        Mockito.when(repository.save(Mockito.any())).thenReturn(p1);
        mockMvc.perform(
                        post("/Order")
                                .content(objectMapper.writeValueAsString(p1))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p1)));
    }


}
