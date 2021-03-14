package de.yalama.onlineshopbackend.CartItem.service;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.CartItem.model.CartItem;
import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.shared.service.BaseService;

import java.util.List;

public abstract class CartItemService implements BaseService<CartItem> {
    public abstract List<CartItem> findByUserId(Long id);

    public abstract List<CartItem> findByUser(User user);

    public abstract List<CartItem> findByAdvertisementId(Long id);

    public abstract List<CartItem> findByAdvertisement(Advertisement advertisement);
}
