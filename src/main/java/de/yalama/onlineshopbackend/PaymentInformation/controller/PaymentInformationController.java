package de.yalama.onlineshopbackend.PaymentInformation.controller;

import de.yalama.onlineshopbackend.PaymentInformation.model.PaymentInformation;
import de.yalama.onlineshopbackend.PaymentInformation.service.PaymentInformationService;
import de.yalama.onlineshopbackend.PaymentMethod.model.PaymentMethod;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentInformation")
@RequiredArgsConstructor
public class PaymentInformationController implements BaseController<PaymentInformation, Long> {

    @Autowired
    private PaymentInformationService paymentInformationService;

    @Override
    @GetMapping
    public List<PaymentInformation> findAll() {
        return this.paymentInformationService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public PaymentInformation findById(@PathVariable Long id) {
        return this.paymentInformationService.findById(id);
    }

    @Override
    @PostMapping
    public PaymentInformation create(@RequestBody PaymentInformation paymentInformation) {
        return this.paymentInformationService.save(paymentInformation);
    }

    @PostMapping("/persist")
    public PaymentInformation createOrUpdate(@RequestBody PaymentInformation paymentInformation) {
        return this.paymentInformationService.createOrUpdatePaymentInformation(paymentInformation);
    }

    @Override
    @PutMapping("/{id}")
    public PaymentInformation update(@PathVariable Long id, @RequestBody PaymentInformation paymentInformation) {
        return this.paymentInformationService.update(id, paymentInformation);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.paymentInformationService.deleteById(id);
    }

    @GetMapping("/user/{id}")
    public List<PaymentInformation> getBySellerId(@PathVariable Long id) {
        return this.paymentInformationService.getPaymentInformationByUserId(id);
    }

    @GetMapping("/user/{userId}/paymentMethod/{paymentMethodId}")
    public PaymentInformation getBySellerIdAndPaymentMethodId(@PathVariable Long userId, @PathVariable Long paymentMethodId) {
        return this.paymentInformationService.getPaymentInformationByUserIdAndPaymentMethodID(userId, paymentMethodId);
    }
}
