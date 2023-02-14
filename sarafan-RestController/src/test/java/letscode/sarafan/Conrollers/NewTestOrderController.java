package letscode.sarafan.Conrollers;


import com.fasterxml.jackson.databind.ObjectMapper;
import letscode.sarafan.domain.Ordr;
import letscode.sarafan.domain.Zvk;
import letscode.sarafan.repo.OrderRepo;
import letscode.sarafan.repo.ZayavkaRepo;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NewTestOrderController {
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrderRepo orderRepo;
    @Autowired
    private MockMvc mockMvc;


    //View
    @Test
    public void Get_order() throws Exception {
        Ordr p1 = new Ordr(111L, "Jane","fre","fre","fre","fre","fre","fre");
        Ordr p2 = new Ordr(112L, "Jane","fre","fre","fre","fre","fre","fre");
        Mockito.when(orderRepo.findAll()).thenReturn(Arrays.asList(p1, p2));
        mockMvc.perform(
                        get("/Order"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(p1, p2))));
        ;
    }

    //Delete
    @Test
    public void delete_order() throws Exception {
        Ordr zvk = new Ordr(121L, "Jane","fre","fre","fre","fre","fre","fre");
        Mockito.when(orderRepo.findById(Mockito.any())).thenReturn(Optional.of(zvk));
        mockMvc.perform(
                        delete("/Order/121"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    //Add
    @Test
    public void Add_zayavka() throws Exception {
        Ordr p1 = new Ordr(121L, "Jane","fre","fre","fre","fre","fre","fre");
        Mockito.when(orderRepo.save(Mockito.any())).thenReturn(p1);
        mockMvc.perform(
                        post("/Order")
                                .content(objectMapper.writeValueAsString(p1))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p1)));
    }


    //Edit
    @Test
    public void edit_order() throws Exception {
        Ordr p1 = new Ordr(121L, "Jane","fre","fre","fre","fre","fre","fre");
        Mockito.when(orderRepo.save(Mockito.any())).thenReturn(p1);
        Mockito.when(orderRepo.findById(Mockito.any())).thenReturn(Optional.of(p1));
        mockMvc.perform(
                        put("/Order/121")
                                .content(objectMapper.writeValueAsString(new Zvk(121L, "Jake","fre")))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("121"))
                .andExpect( jsonPath("$.name").value("Jake"));

    }

}

