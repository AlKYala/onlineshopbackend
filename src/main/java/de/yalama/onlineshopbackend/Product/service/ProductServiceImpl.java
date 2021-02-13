package de.yalama.onlineshopbackend.Product.service;

import de.yalama.onlineshopbackend.Product.model.Product;
import de.yalama.onlineshopbackend.Product.repository.ProductRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl extends ProductService{

    private ProductRepository productRepository;
    private Validator<Product, ProductRepository> validator;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
        this.validator = new Validator<Product, ProductRepository>("Product", this.productRepository);
    }

    @Override
    public Product findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.productRepository.findById(id).get();
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product save(Product instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.productRepository.save(instance);
    }

    @Override
    public Product update(Long id, Product instance) {
        this.validator.checkCanUpdate(id, instance.getId());
        return this.productRepository.save(instance);
    }

    @Override
    /**
     * Only deleted when no ads for it exist
     */
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);
        Product toDelete = this.findById(id);
        boolean adsForProductExist = !toDelete.getAdvertisementsOfProduct().isEmpty();
        if(!adsForProductExist) {
            this.productRepository.deleteById(id);
            return id;
        }
        return null;
    }
}
