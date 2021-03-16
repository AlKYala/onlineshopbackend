package de.yalama.onlineshopbackend.CartItem.controller;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.CartItem.model.CartItem;
import de.yalama.onlineshopbackend.CartItem.service.CartItemService;
import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartItems")
@RequiredArgsConstructor
public class CartItemController implements BaseController<CartItem, Long> {

    @Autowired
    private CartItemService cartItemService;

    @Override
    @GetMapping
    public List<CartItem> findAll() {
        return this.cartItemService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public CartItem findById(@PathVariable Long id) {
        return this.cartItemService.findById(id);
    }

    @Override
    @PostMapping
    public CartItem create(@RequestBody CartItem cartItem) {
        return this.cartItemService.save(cartItem);
    }

    @Override
    @PutMapping("/{id}")
    public CartItem update(@PathVariable Long id, @RequestBody CartItem cartItem) {
        return this.cartItemService.update(id, cartItem);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.cartItemService.deleteById(id);
    }

    @GetMapping("/user")
    public List<CartItem> findAllByUser(@RequestBody User user) {
        return this.cartItemService.findByUser(user);
    }

    @GetMapping("/user/{id}")
    public List<CartItem> findAllByUserId(@PathVariable Long id) {
        return this.cartItemService.findByUserId(id);
    }

    @GetMapping("/ad")
    public List<CartItem> findAllByAdvertisement(@RequestBody Advertisement advertisement) {
        return this.cartItemService.findByAdvertisement(advertisement);
    }

    @GetMapping("/ad/{id}")
    public List<CartItem> findAllByAdvertisementId(@PathVariable Long id) {
        return this.cartItemService.findByAdvertisementId(id);
    }

    @GetMapping("/user/{id}/price")
    public Double getPriceOfUserCartById(@PathVariable Long id) {
        return this.cartItemService.findCartPriceByUserId(id);
    }
}
