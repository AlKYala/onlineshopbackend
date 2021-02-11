package de.yalama.onlineshopbackend.Pictures.service;

import de.yalama.onlineshopbackend.Advertisement.repository.AdvertisementRepository;
import de.yalama.onlineshopbackend.Pictures.model.Picture;
import de.yalama.onlineshopbackend.Pictures.repository.PictureRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PictureServiceImpl extends PictureService {

    private PictureRepository pictureRepository;
    private Validator<Picture, PictureRepository> validator;
    private AdvertisementRepository advertisementRepository;

    public PictureServiceImpl(PictureRepository pictureRepository,
                              AdvertisementRepository advertisementRepository) {
        this.pictureRepository = pictureRepository;
        this.validator = new Validator<Picture, PictureRepository>("Picture", this.pictureRepository);
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public Picture findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.pictureRepository.findById(id).get();
    }

    @Override
    public List<Picture> findAll() {
        return this.pictureRepository.findAll();
    }

    @Override
    public Picture save(Picture instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.pictureRepository.save(instance);
    }

    @Override
    public Picture update(Picture instance) {
        this.validator.checkEntityExists(instance.getId());
        return this.pictureRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);

        boolean isAdDeleted = this.advertisementRepository.existsById(id);

        if(isAdDeleted) {
            this.advertisementRepository.deleteById(id);
            return id;
        }
        return null;
    }
}
