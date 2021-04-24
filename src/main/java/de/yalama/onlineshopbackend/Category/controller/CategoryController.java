package de.yalama.onlineshopbackend.Category.controller;

import de.yalama.onlineshopbackend.Category.model.Category;
import de.yalama.onlineshopbackend.Category.service.CategoryService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController implements BaseController<Category, Long> {

    @Autowired
    private CategoryService categoryService;

    @Override
    @GetMapping
    public List<Category> findAll() {
        return this.categoryService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id) {
        return this.categoryService.findById(id);
    }

    @Override
    @PostMapping
    public Category create(@RequestBody Category category) {
        return this.categoryService.save(category);
    }

    @Override
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        return this.categoryService.update(id, category);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.categoryService.deleteById(id);
    }

    @GetMapping("/exists/{name}")
    public Boolean existsByName(@PathVariable String name) {
        return this.categoryService.existsByName(name);
    }
}
