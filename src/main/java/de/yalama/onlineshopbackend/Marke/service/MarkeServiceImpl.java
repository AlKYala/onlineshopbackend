package de.yalama.onlineshopbackend.Marke.service;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.Advertisement.service.AdvertisementService;
import de.yalama.onlineshopbackend.Marke.model.Marke;
import de.yalama.onlineshopbackend.Marke.repository.MarkeRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class MarkeServiceImpl extends MarkeService {

    private MarkeRepository markeRepository;
    private Validator<Marke, MarkeRepository> validator;
    private AdvertisementService advertisementService;

    public MarkeServiceImpl(MarkeRepository markeRepository,
                            AdvertisementService advertisementService) {
        this.markeRepository = markeRepository;
        this.advertisementService = advertisementService;
        this.validator = new Validator<Marke, MarkeRepository>("Marke", this.markeRepository);
    }

    @Override
    public Marke findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.markeRepository.findById(id).get();
    }

    @Override
    public List<Marke> findAll() {
        return this.markeRepository.findAll();
    }

    @Override
    public Marke save(Marke instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.markeRepository.save(instance);
    }

    @Override
    public Marke update(Long id, Marke instance) {
        this.validator.checkCanUpdate(id, instance.getId());
        return this.markeRepository.save(instance);
    }

    @Override
    /**
     * All Ads for it deleted.
     */
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);
        Marke toDelete = this.findById(id);
        Set<Advertisement> adsOfProduct = toDelete.getAdvertisementsOfMarke();
        for(Advertisement ad : adsOfProduct) {
            this.advertisementService.deleteById(ad.getId());
        }

        this.markeRepository.deleteById(id);
        return id;
    }


}
