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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private OrderRepo repository;
    @Autowired
    private MockMvc mockMvc;
    //View
    @Test
    public void Get_order() throws Exception {
        Ordr p1 = new Ordr(111L, "Jane","fre","fre","fre","fre","fre","fre");
        Ordr p2 = new Ordr(112L, "Joe", "fre","fre","fre","fre","fre","fre");
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
    public void delete_order() throws Exception {
        Ordr zvk = new Ordr(121L, "Jane","fre","fre","fre","fre","fre","fre");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(zvk));
        mockMvc.perform(
                        delete("/Order/121"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
