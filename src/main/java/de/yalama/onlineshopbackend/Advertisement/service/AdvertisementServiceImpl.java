package de.yalama.onlineshopbackend.Advertisement.service;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.Advertisement.repository.AdvertisementRepository;
import de.yalama.onlineshopbackend.Pictures.repository.PictureRepository;
import de.yalama.onlineshopbackend.Marke.repository.MarkeRepository;
import de.yalama.onlineshopbackend.Purchase.repository.PurchaseRepository;
import de.yalama.onlineshopbackend.User.repository.UserRepository;
import de.yalama.onlineshopbackend.shared.models.SearchQuery;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class AdvertisementServiceImpl extends AdvertisementService {

    private AdvertisementRepository advertisementRepository;
    private Validator<Advertisement, AdvertisementRepository> advertisementValidator;
    private PictureRepository pictureRepository;
    private PurchaseRepository purchaseRepository;
    private MarkeRepository markeRepository;
    private UserRepository userRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository,
                                    PictureRepository pictureRepository,
                                    PurchaseRepository purchaseRepository,
                                    MarkeRepository markeRepository,
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
    public Advertisement update(Long id, Advertisement instance) {
        this.advertisementValidator.checkCanUpdate(id, instance.getId());
        return this.advertisementRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        this.advertisementValidator.checkEntityExists(id);

        Advertisement toDelete = this.findById(id);

        toDelete.getMarke().getAdvertisementsOfMarke()
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

    @Override
    public Advertisement[] filter(SearchQuery searchQuery) {
        Advertisement[] allAds = this.findAll().toArray(new Advertisement[this.findAll().size()]);
        AtomicInteger countdeleted = new AtomicInteger(0);
        searchQuery.filter(allAds, countdeleted);
        return searchQuery.getRemainingAds(allAds, countdeleted);
    }
}
