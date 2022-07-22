package pl.sda.service;

import pl.sda.model.Product;
import java.util.List;

public interface ProductService {

    void save(Product product);

    List<Product> getAll();

    void deleteByTitle(String title);

    void update(Product product);

    Product getById(Integer id);

    Product getByTitle(String title);

    List<Product> getAll(int pageNo, int pageSize, String sortBy);
}
