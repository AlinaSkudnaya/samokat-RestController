package Alina.sarafan.controller;


import Alina.sarafan.repo.ArendaRepo;
import Alina.sarafan.domain.Arnd;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController   //для реста
@RequestMapping("Arenda") //по этому адресу будет направляться к этому контроллеру
public class ArendaController {

    private final ArendaRepo arendarepo;


    public ArendaController(ArendaRepo arendarepo) {
        this.arendarepo = arendarepo;
    }

    @GetMapping
    public List<Arnd> list() {
        return arendarepo.findAll();
    }


    @GetMapping("{id}")
    public Arnd getOneSamokat(@PathVariable("id") Arnd arenda)
    {
        return arenda;
    }

    @PostMapping
    public Arnd createSamokat(
            @RequestBody Arnd arenda) {
        return arendarepo.save(arenda);
    }

    @PutMapping("{id}")
    public Arnd updateSamokat(
            @PathVariable("id") Arnd samokatFromDB,
            @RequestBody Arnd arenda)
    {

        BeanUtils.copyProperties(arenda,samokatFromDB,"id");
        return arendarepo.save(samokatFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Arnd arenda) {
        arendarepo.delete(arenda);
    }


}
