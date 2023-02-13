package letscode.sarafan.Conrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import letscode.sarafan.domain.Samokat;
import letscode.sarafan.domain.Zvk;
import letscode.sarafan.repo.SamokatRepo;
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

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ZayavkaControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ZayavkaRepo repository;
    @Autowired
    private MockMvc mockMvc;



    //View
    @Test
    public void Get_zayavka() throws Exception {
        Zvk p1 = new Zvk(111L, "Jane","fre");
        Zvk p2 = new Zvk(112L, "Joe", "fre");
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(p1, p2));
        mockMvc.perform(
                        get("/Zayavka"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(p1, p2))));
        ;
    }


    //Delete
    @Test
    public void delete_zayavka() throws Exception {
        Zvk zvk = new Zvk(121L, "Jane","fre");
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(zvk));
        mockMvc.perform(
                        delete("/Zayavka/121"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    //Edit
    @Test
    public void edit_zayavka() throws Exception {
        Zvk p1 = new Zvk(111L, "Jane","fre");
        Mockito.when(repository.save(Mockito.any())).thenReturn(p1);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(p1));
        mockMvc.perform(
                        put("/Zayavka/111")
                                .content(objectMapper.writeValueAsString(new Zvk(111L, "Jane","fre")))
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("111"))
                .andExpect( jsonPath("$.name").value("Jane"));

    }

    @Test
    public void Add_zayavka() throws Exception {
        Zvk p1 = new Zvk(111L, "Jane","fre");
        Mockito.when(repository.save(Mockito.any())).thenReturn(p1);
        mockMvc.perform(
                        post("/Zayavka")
                                .content(objectMapper.writeValueAsString(p1))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p1)));
    }

}
