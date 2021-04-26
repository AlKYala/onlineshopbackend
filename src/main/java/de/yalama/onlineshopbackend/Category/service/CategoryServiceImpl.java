package de.yalama.onlineshopbackend.Category.service;

import de.yalama.onlineshopbackend.Category.model.Category;
import de.yalama.onlineshopbackend.Category.repository.CategoryRepository;
import de.yalama.onlineshopbackend.Marke.model.Marke;
import de.yalama.onlineshopbackend.Marke.service.MarkeService;
import de.yalama.onlineshopbackend.shared.service.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl extends CategoryService {

    private CategoryRepository categoryRepository;
    private Validator<Category, CategoryRepository> validator;
    private MarkeService markeService;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               MarkeService markeService) {
        this.validator = new Validator<Category, CategoryRepository>("Category", categoryRepository);
        this.categoryRepository = categoryRepository;
        this.markeService = markeService;
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
        Set<Marke> productsOfCategory = toDelete.getProductsOfCategory();

        for(Marke markeToDelete : productsOfCategory) {
            this.markeService.deleteById(markeToDelete.getId());
        }

        this.categoryRepository.deleteById(id);
        return id;
    }

    @Override
    public Boolean existsByName(String name) {
        return this.findAll()
                .stream().anyMatch(category -> category.getName().toLowerCase().equals(name.toLowerCase()));
    }
}
