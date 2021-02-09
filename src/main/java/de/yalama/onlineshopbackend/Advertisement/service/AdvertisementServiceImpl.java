package de.yalama.onlineshopbackend.Advertisement.service;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.Advertisement.repository.AdvertisementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdvertisementServiceImpl extends AdvertisementService {

    private AdvertisementRepository advertisementRepository;

    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public Advertisement findById(Long id) {
        //TODO Validator exists, Exception notFound
        return this.advertisementRepository.findById(id).get();
    }

    @Override
    public List<Advertisement> findAll() {
        return this.advertisementRepository.findAll();
    }

    @Override
    public Advertisement save(Advertisement instance) {
        //TODO Validator notExists, Exception notSaved
        return this.advertisementRepository.save(instance);
    }

    @Override
    public Advertisement update(Advertisement instance) {
        //TODO Validator exists, Exception notFound, not Saved
        return this.advertisementRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        //TODO Validator exists, Exception notFound, not Deleted
        //TODO Relationships with deletion
        return null;
    }
}
