package pl.sda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

        Product findByTitle(String title);

}
