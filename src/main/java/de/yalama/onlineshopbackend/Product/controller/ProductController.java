package de.yalama.onlineshopbackend.Product.controller;

import de.yalama.onlineshopbackend.Product.model.Product;
import de.yalama.onlineshopbackend.Product.service.ProductService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController implements BaseController<Product, Long> {

    @Autowired
    private ProductService productService;

    @Override
    @GetMapping
    public List<Product> findAll() {
        return this.productService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @Override
    @PostMapping
    public Product create(@RequestBody Product product) {
        return this.productService.save(product);
    }

    @Override
    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return this.productService.update(id, product);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.productService.deleteById(id);
    }
}
