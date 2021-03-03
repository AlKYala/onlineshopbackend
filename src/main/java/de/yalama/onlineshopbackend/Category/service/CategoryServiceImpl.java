package de.yalama.onlineshopbackend.Category.service;

import de.yalama.onlineshopbackend.Category.model.Category;
import de.yalama.onlineshopbackend.Category.repository.CategoryRepository;
import de.yalama.onlineshopbackend.Product.model.Product;
import de.yalama.onlineshopbackend.Product.repository.ProductRepository;
import de.yalama.onlineshopbackend.Product.service.ProductService;
import de.yalama.onlineshopbackend.shared.service.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl extends CategoryService {

    private CategoryRepository categoryRepository;
    private Validator<Category, CategoryRepository> validator;
    private ProductService productService;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               ProductService productService) {
        this.validator = new Validator<Category, CategoryRepository>("Category", categoryRepository);
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Override
    public Category findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category save(Category instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.categoryRepository.save(instance);
    }

    @Override
    public Category update(Long id, Category instance) {
        this.validator.checkCanUpdate(id, instance.getId());
        return this.categoryRepository.save(instance);
    }

    /**
     * Deletes all Products of that Category!
     */
    @Override
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);
        Category toDelete = this.findById(id);
        Set<Product> productsOfCategory = toDelete.getProductsOfCategory();

        for(Product productToDelete : productsOfCategory) {
            this.productService.deleteById(productToDelete.getId());
        }

        this.categoryRepository.deleteById(id);
        return id;
    }
}
