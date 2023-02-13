package letscode.sarafan.controller;


import letscode.sarafan.domain.Arnd;

import letscode.sarafan.domain.Zvk;
import letscode.sarafan.repo.ZayavkaRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController   //для реста
@RequestMapping("Zayavka") //по этому адресу будет направляться к этому контроллеру
public class ZayavkaController  {

    private final ZayavkaRepo zayavkaRepo;



    public ZayavkaController(ZayavkaRepo zayavkaRepo) {
        this.zayavkaRepo = zayavkaRepo;
    }

    @GetMapping
    public List<Zvk> list() {
        return zayavkaRepo.findAll();
    }


    @GetMapping("{id}")
    public Zvk getOneSamokat(@PathVariable("id") Zvk zvk)
    {
        return zvk;
    }

    @PostMapping
    public Zvk createSamokat(
            @RequestBody Zvk zvk) {
        return zayavkaRepo.save(zvk);
    }

    @PutMapping("{id}")
    public Zvk updateSamokat(
            @PathVariable("id") Zvk samokatFromDB,
            @RequestBody Zvk zvk)
    {

        BeanUtils.copyProperties(zvk,samokatFromDB,"id");
        return zayavkaRepo.save(samokatFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Zvk zvk) {
        zayavkaRepo.delete(zvk);
    }

}
