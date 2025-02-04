package itsgosho.repositories;

import itsgosho.domain.entities.Branch;
import itsgosho.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
