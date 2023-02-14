package letscode.sarafan;


import com.fasterxml.jackson.databind.ObjectMapper;
import letscode.sarafan.domain.Arnd;
import letscode.sarafan.domain.Zvk;
import letscode.sarafan.repo.ArendaRepo;
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
public class NewTest {
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ArendaRepo arendaRepo;
    @Autowired
    private MockMvc mockMvc;


    //View
    @Test
    public void Get_order() throws Exception {
        Arnd p1 = new Arnd(111l, "Jane","fre","fre","fre","fre","fre","fre","fre","fre");
        Arnd p2 = new Arnd(112l, "Jane","fre","fre","fre","fre","fre","fre","fre","fre");
        Mockito.when(arendaRepo.findAll()).thenReturn(Arrays.asList(p1, p2));
        mockMvc.perform(
                        get("/Ar"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(p1, p2))));
        ;
    }




    //Add
    @Test
    public void Add_zayavka() throws Exception {
        Arnd p1 = new Arnd(111l, "Jane","fre","fre","fre","fre","fre","fre","fre","fre");
        Mockito.when(arendaRepo.save(Mockito.any())).thenReturn(p1);
        mockMvc.perform(
                        post("/Ar")
                                .content(objectMapper.writeValueAsString(p1))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p1)));
    }


    @Test
    public void update_arnd() throws Exception {
        Arnd person = new Arnd(156l, "Michail","ewr","de","de","de","de","de","de","de");
        Mockito.when(arendaRepo.save(Mockito.any())).thenReturn(person);
        Mockito.when(arendaRepo.findById(Mockito.any())).thenReturn(Optional.of(person));
        mockMvc.perform(
                        put("/Ar/156")
                                .content(objectMapper.writeValueAsString(new Arnd(156l,"Jake","Michail","Michail","Michail","Michail","Michail","Michail","Michail","Michail")))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("156"))
                .andExpect(jsonPath("$.name").value("Jake"));
    }


    //Delete
    @Test
    public void delete_xsa() throws Exception {
        Arnd arnd = new Arnd(156l, "Michail","ewr","de","de","de","de","de","de","de");
        Mockito.when(arendaRepo.findById(Mockito.any())).thenReturn(Optional.of(arnd));
        mockMvc.perform(
                        delete("/Ar/156"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
