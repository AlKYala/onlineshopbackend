package de.yalama.onlineshopbackend.Purchase.controller;

import de.yalama.onlineshopbackend.Purchase.model.Purchase;
import de.yalama.onlineshopbackend.Purchase.service.PurchaseService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController implements BaseController<Purchase, Long> {

    @Autowired
    private PurchaseService purchaseService;

    @Override
    @GetMapping
    public List<Purchase> findAll() {
        return this.purchaseService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Purchase findById(@PathVariable Long id) {
        return this.purchaseService.findById(id);
    }

    @Override
    @PostMapping
    public Purchase create(@RequestBody Purchase purchase) {
        return this.purchaseService.save(purchase);
    }

    @Override
    @PutMapping("/{id}")
    public Purchase update(@PathVariable Long id, @RequestBody Purchase purchase) {
        return this.purchaseService.update(id, purchase);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.purchaseService.deleteById(id);
    }
}
