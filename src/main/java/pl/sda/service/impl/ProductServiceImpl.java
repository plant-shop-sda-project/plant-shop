package pl.sda.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.sda.model.Product;
import pl.sda.repository.ProductRepository;
import pl.sda.service.ProductService;

import java.util.List;
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void deleteByTitle(String title) {
        Product product = getByTitle(title);
        productRepository.deleteById(product.getId());
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product getByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    @Override
    public List<Product> getAll(int pageNo, int pageSize, String sortBy) {

        Pageable pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Product> productPage = productRepository.findAll(pageRequest);

        log.info("Total elements: " + productPage.getTotalElements());
        log.info("Total pages: " + productPage.getTotalPages());
        log.info("Elements in this page: " + productPage.getNumberOfElements());
        log.info("Page number: " + productPage.getNumber());

        return productPage.getContent();
    }


}