package letscode.sarafan.controller;

import letscode.sarafan.domain.Ordr;
import letscode.sarafan.domain.Samokat;
import letscode.sarafan.repo.OrderRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController   //для реста
@RequestMapping("Order") //по этому адресу будет направляться к этому контроллеру
public class OrderController {

    private final OrderRepo orderRepo;


    public OrderController(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping
    public List<Ordr> list() {
        return orderRepo.findAll();
    }


    @GetMapping("{id}")
    public Ordr getOneSamokat(@PathVariable("id") Ordr order)
    {
        return order;
    }

    @PostMapping
    public Ordr createSamokat(
            @RequestBody Ordr order) {
        return orderRepo.save(order);
    }

    @PutMapping("{id}")
    public Ordr updateSamokat(
            @PathVariable("id") Ordr samokatFromDB,
            @RequestBody Ordr order)
    {

        BeanUtils.copyProperties(order,samokatFromDB,"id");
        return orderRepo.save(samokatFromDB);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Ordr order) {
        orderRepo.delete(order);
    }


}
