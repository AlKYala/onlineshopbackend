package de.yalama.onlineshopbackend.Product.service;

import de.yalama.onlineshopbackend.Product.model.Product;
import de.yalama.onlineshopbackend.Product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl extends ProductService{

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product findById(Long id) {
        //TODO Validator exists, Exception notFound
        return this.productRepository.findById(id).get();
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product save(Product instance) {
        //TODO Validator notExists, Exception notSaved
        return this.productRepository.save(instance);
    }

    @Override
    public Product update(Product instance) {
        //TODO Validator exists, Exception notFound, not Saved
        return this.productRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        //TODO Validator exists, Exception notFound, not Deleted
        //TODO Relationships with deletion
        return null;
    }
}
