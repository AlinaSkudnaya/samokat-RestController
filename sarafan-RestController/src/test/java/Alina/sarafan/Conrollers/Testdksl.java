package Alina.sarafan.Conrollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import Alina.sarafan.repo.ArendaRepo;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Testdksl {
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ArendaRepo repository;
    @Autowired
    private MockMvc mockMvc;

//    //Delete
//    @Test
//    public void delete_ar() throws Exception {
//        Arnd p1 = new Arnd(114L, "Jane","fre", "fre","fre","fre","fre","fre","fre","fre");
//        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.of(p1));
//        mockMvc.perform(
//                        delete("/Ar/114"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
}
