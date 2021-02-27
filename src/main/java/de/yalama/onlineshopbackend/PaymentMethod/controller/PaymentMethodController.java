package de.yalama.onlineshopbackend.PaymentMethod.controller;

import de.yalama.onlineshopbackend.PaymentMethod.model.PaymentMethod;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/paymentMethod")
@RestController
@RequiredArgsConstructor
public class PaymentMethodController implements BaseController<PaymentMethod, Long> {

    @Autowired
    private PaymentMethodController paymentMethodController;

    @Override
    @GetMapping
    public List<PaymentMethod> findAll() {
        return this.paymentMethodController.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public PaymentMethod findById(@PathVariable Long id) {
        return this.paymentMethodController.findById(id);
    }

    @Override
    @PostMapping
    public PaymentMethod create(@RequestBody PaymentMethod paymentMethod) {
        return this.paymentMethodController.create(paymentMethod);
    }

    @Override
    @PutMapping("/{id}")
    public PaymentMethod update(@PathVariable Long id, @RequestBody PaymentMethod paymentMethod) {
        return this.paymentMethodController.update(id, paymentMethod);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.paymentMethodController.delete(id);
    }
}
