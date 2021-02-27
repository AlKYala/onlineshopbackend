package de.yalama.onlineshopbackend.Message.purchaseMessage.Controller;

import de.yalama.onlineshopbackend.Message.purchaseMessage.model.PurchaseMessage;
import de.yalama.onlineshopbackend.Message.purchaseMessage.service.PurchaseMessageService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchaseMessage")
public class PurchaseMessageController implements BaseController<PurchaseMessage, Long> {

    @Autowired
    private PurchaseMessageService purchaseMessageService;

    @Override
    @GetMapping
    public List<PurchaseMessage> findAll() {
        return this.purchaseMessageService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public PurchaseMessage findById(@PathVariable Long id) {
        return this.purchaseMessageService.findById(id);
    }

    @Override
    @PostMapping
    public PurchaseMessage create(@RequestBody PurchaseMessage purchaseMessage) {
        return this.purchaseMessageService.save(purchaseMessage);
    }

    @Override
    @PutMapping("/{id}")
    public PurchaseMessage update(@PathVariable Long id, @RequestBody PurchaseMessage purchaseMessage) {
        return this.purchaseMessageService.update(id, purchaseMessage);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.purchaseMessageService.deleteById(id);
    }
}
