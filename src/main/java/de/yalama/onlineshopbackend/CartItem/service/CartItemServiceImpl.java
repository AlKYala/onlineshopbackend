package de.yalama.onlineshopbackend.CartItem.service;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.Advertisement.repository.AdvertisementRepository;
import de.yalama.onlineshopbackend.Advertisement.service.AdvertisementService;
import de.yalama.onlineshopbackend.CartItem.model.CartItem;
import de.yalama.onlineshopbackend.CartItem.repository.CartItemRepository;
import de.yalama.onlineshopbackend.User.model.User;
import de.yalama.onlineshopbackend.User.repository.UserRepository;
import de.yalama.onlineshopbackend.User.service.UserService;
import de.yalama.onlineshopbackend.shared.service.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl extends CartItemService {

    private Validator<CartItem, CartItemRepository> validator;
    private CartItemRepository cartItemRepository;
    private AdvertisementRepository advertisementRepository;
    private UserRepository userRepository;

    public CartItemServiceImpl(CartItemRepository cartItemRepository,
                               AdvertisementRepository advertisementRepository,
                               UserRepository userRepository) {
        this.validator = new Validator<CartItem, CartItemRepository>("CartItem", cartItemRepository);
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public List<CartItem> findByUserId(Long id) {
        return this.findAll().stream().filter(cartItem -> cartItem.getId() == id).collect(Collectors.toList());
    }

    @Override
    public List<CartItem> findByUser(User user) {
        return this.findByUserId(user.getId());
    }

    @Override
    public List<CartItem> findByAdvertisementId(Long id) {
        return this.findAll().stream().filter(cartItem -> cartItem.getId() == id).collect(Collectors.toList());
    }

    @Override
    public List<CartItem> findByAdvertisement(Advertisement advertisement) {
        return this.findByAdvertisementId(advertisement.getId());
    }

    @Override
    public CartItem findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.cartItemRepository.findById(id).get();
    }

    @Override
    public List<CartItem> findAll() {
        return this.cartItemRepository.findAll();
    }

    @Override
    public CartItem save(CartItem instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.cartItemRepository.save(instance);
    }

    @Override
    public CartItem update(Long id, CartItem instance) {
        this.validator.checkCanUpdate(id, instance.getId());
        return this.cartItemRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        CartItem toDelete = this.findById(id);

        User userOfCartItem = this.userRepository.findById(toDelete.getUser().getId()).get();
        Advertisement advertisementOfCartItem = this.advertisementRepository
                .findById(toDelete.getAdvertisement().getId()).get();

        userOfCartItem.getItemsInCartItem().removeIf(cartItem -> cartItem.getId() == id);
        advertisementOfCartItem.getInstancesInCartItems().removeIf(cartItem -> cartItem.getId() == id);

        this.cartItemRepository.deleteById(id);
        return id;
    }
}
