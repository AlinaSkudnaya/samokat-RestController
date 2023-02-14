package Alina.sarafan.repo;

import Alina.sarafan.domain.Ordr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Ordr,Long> {
}
