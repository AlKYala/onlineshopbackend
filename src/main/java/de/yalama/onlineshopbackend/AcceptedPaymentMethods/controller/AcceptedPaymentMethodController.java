package de.yalama.onlineshopbackend.AcceptedPaymentMethods.controller;

import de.yalama.onlineshopbackend.AcceptedPaymentMethods.model.AcceptedPaymentMethod;
import de.yalama.onlineshopbackend.AcceptedPaymentMethods.service.AcceptedPaymentMethodService;
import de.yalama.onlineshopbackend.PaymentMethod.model.PaymentMethod;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/acceptedPaymentMethods")
public class AcceptedPaymentMethodController implements BaseController<AcceptedPaymentMethod, Long> {

    @Autowired
    private AcceptedPaymentMethodService acceptedPaymentMethodService;

    @Override
    @GetMapping
    public List<AcceptedPaymentMethod> findAll() {
        return this.acceptedPaymentMethodService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public AcceptedPaymentMethod findById(@PathVariable Long id) {
        return this.acceptedPaymentMethodService.findById(id);
    }

    @Override
    @PostMapping
    public AcceptedPaymentMethod create(@RequestBody AcceptedPaymentMethod acceptedPaymentMethod) {
        return this.acceptedPaymentMethodService.save(acceptedPaymentMethod);
    }

    @Override
    @PutMapping("/{id}")
    public AcceptedPaymentMethod update(@PathVariable Long id, @RequestBody AcceptedPaymentMethod acceptedPaymentMethod) {
        return this.acceptedPaymentMethodService.update(id, acceptedPaymentMethod);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.acceptedPaymentMethodService.deleteById(id);
    }

    @GetMapping("/user/{id}")
    public List<PaymentMethod> findPaymentMethodsByUserId(@PathVariable Long id) {
        return this.acceptedPaymentMethodService.findPaymentMethodsBySellerId(id);
    }

    @DeleteMapping("/deleteobject")
    public Long deleteByObject(@RequestBody AcceptedPaymentMethod acceptedPaymentMethod) {
        return this.acceptedPaymentMethodService.deleteByObject(acceptedPaymentMethod);
    }
}
