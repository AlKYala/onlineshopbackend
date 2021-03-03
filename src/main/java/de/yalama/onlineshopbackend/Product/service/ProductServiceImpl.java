package de.yalama.onlineshopbackend.Product.service;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.Advertisement.repository.AdvertisementRepository;
import de.yalama.onlineshopbackend.Advertisement.service.AdvertisementService;
import de.yalama.onlineshopbackend.Product.model.Product;
import de.yalama.onlineshopbackend.Product.repository.ProductRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class ProductServiceImpl extends ProductService{

    private ProductRepository productRepository;
    private Validator<Product, ProductRepository> validator;
    private AdvertisementService advertisementService;

    public ProductServiceImpl(ProductRepository productRepository,
                              AdvertisementService advertisementService) {
        this.productRepository = productRepository;
        this.advertisementService = advertisementService;
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
     * All Ads for it deleted.
     */
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);
        Product toDelete = this.findById(id);
        Set<Advertisement> adsOfProduct = toDelete.getAdvertisementsOfProduct();
        for(Advertisement ad : adsOfProduct) {
            this.advertisementService.deleteById(ad.getId());
        }

        this.productRepository.deleteById(id);
        return id;
    }


}
