package de.yalama.onlineshopbackend.PaymentMethod.controller;

import de.yalama.onlineshopbackend.PaymentMethod.model.PaymentMethod;
import de.yalama.onlineshopbackend.PaymentMethod.service.PaymentMethodService;
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
    private PaymentMethodService paymentMethodService;

    @Override
    @GetMapping
    public List<PaymentMethod> findAll() {
        return this.paymentMethodService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public PaymentMethod findById(@PathVariable Long id) {
        return this.paymentMethodService.findById(id);
    }

    @Override
    @PostMapping
    public PaymentMethod create(@RequestBody PaymentMethod paymentMethod) {
        return this.paymentMethodService.save(paymentMethod);
    }

    @Override
    @PutMapping("/{id}")
    public PaymentMethod update(@PathVariable Long id, @RequestBody PaymentMethod paymentMethod) {
        return this.paymentMethodService.update(id, paymentMethod);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.paymentMethodService.deleteById(id);
    }

    @GetMapping("/user/{id}")
    public List<PaymentMethod> getPaymentMethodsByUserId(@PathVariable Long id) {
        return this.paymentMethodService.findPaymentMethodsByUserId(id);
    }
}
