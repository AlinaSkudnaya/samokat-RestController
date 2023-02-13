package letscode.sarafan.repo;

import letscode.sarafan.domain.Ordr;
import letscode.sarafan.domain.Samokat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Ordr,Long> {
}
