package de.yalama.onlineshopbackend.Advertisement.service;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.Advertisement.repository.AdvertisementRepository;
import de.yalama.onlineshopbackend.Pictures.repository.PictureRepository;
import de.yalama.onlineshopbackend.Product.repository.ProductRepository;
import de.yalama.onlineshopbackend.Purchase.repository.PurchaseRepository;
import de.yalama.onlineshopbackend.User.repository.UserRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdvertisementServiceImpl extends AdvertisementService {

    private AdvertisementRepository advertisementRepository;
    private Validator<Advertisement, AdvertisementRepository> advertisementValidator;
    private PictureRepository pictureRepository;
    private PurchaseRepository purchaseRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository,
                                    PictureRepository pictureRepository,
                                    PurchaseRepository purchaseRepository,
                                    ProductRepository productRepository,
                                    UserRepository userRepository) {
        this.advertisementRepository = advertisementRepository;
        this.advertisementValidator = new Validator<Advertisement, AdvertisementRepository>("Advertisement",
                this.advertisementRepository);
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Advertisement findById(Long id) {
        this.advertisementValidator.checkEntityExists(id);
        return this.advertisementRepository.findById(id).get();
    }

    @Override
    public List<Advertisement> findAll() {
        return this.advertisementRepository.findAll();
    }

    @Override
    public Advertisement save(Advertisement instance) {
        this.advertisementValidator.checkEntityNotExists(instance.getId());
        return this.advertisementRepository.save(instance);
    }

    @Override
    public Advertisement update(Advertisement instance) {
        this.advertisementValidator.checkEntityExists(instance.getId());
        return this.advertisementRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        this.advertisementValidator.checkEntityExists(id);

        Advertisement toDelete = this.findById(id);

        toDelete.getProduct().getAdvertisementsOfProduct()
                .removeIf(advertisement -> advertisement.getId() == toDelete.getId());
        toDelete.getSeller().getSalesOfUser()
                .removeIf(advertisement -> advertisement.getId() == toDelete.getId());

        toDelete.getPicturesOfAdvertisement().forEach(picture -> this.pictureRepository.deleteById(picture.getId()));
        //CANNOT DELETE PURCHASES
        toDelete.getPurchasesOfAdvertisement()
                .forEach(purchase ->
                        this.purchaseRepository.findById(toDelete.getId()).get().setAdvertisementOfPurchase(null));

        this.advertisementRepository.deleteById(id);
        return id;
    }
}
