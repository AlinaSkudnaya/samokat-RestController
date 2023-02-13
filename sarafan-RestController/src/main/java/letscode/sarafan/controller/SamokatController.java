package letscode.sarafan.controller;

import letscode.sarafan.domain.Samokat;
import letscode.sarafan.repo.SamokatRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController   //для реста
@RequestMapping("samokat") //по этому адресу будет направляться к этому контроллеру
public class SamokatController {
    
    private final SamokatRepo samokatRepo;

    @Autowired
    public SamokatController(SamokatRepo samokatRepo) {
        this.samokatRepo = samokatRepo;
    }

    @GetMapping
    public List<Samokat> list() {
        return samokatRepo.findAll();
    }
    

    @GetMapping("{id}")
    public Samokat getOneSamokat(@PathVariable("id") Samokat samokat)
    {
        return samokat;
    }

    @PostMapping
    public Samokat createSamokat(
            @RequestBody Samokat samokat) {

        return samokatRepo.save(samokat);
    }

    @PutMapping("{id}")
    public Samokat updateSamokat(
            @PathVariable("id") Samokat samokatFromDB,
            @RequestBody Samokat samokat)
    {

        BeanUtils.copyProperties(samokat,samokatFromDB,"id");
        return samokatRepo.save(samokatFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Samokat samokat) {
    samokatRepo.delete(samokat);
    }


}
