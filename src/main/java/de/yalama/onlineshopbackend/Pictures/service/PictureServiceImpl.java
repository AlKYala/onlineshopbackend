package de.yalama.onlineshopbackend.Pictures.service;

import de.yalama.onlineshopbackend.Pictures.model.Picture;
import de.yalama.onlineshopbackend.Pictures.repository.PictureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PictureServiceImpl extends PictureService {

    public PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Picture findById(Long id) {
        //TODO Validator exists, Exception notFound
        return this.pictureRepository.findById(id).get();
    }

    @Override
    public List<Picture> findAll() {
        return this.pictureRepository.findAll();
    }

    @Override
    public Picture save(Picture instance) {
        //TODO Validator notExists, Exception notSaved
        return this.pictureRepository.save(instance);
    }

    @Override
    public Picture update(Picture instance) {
        //TODO Validator exists, Exception notFound, not Saved
        return this.pictureRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        //TODO Validator exists, Exception notFound, not Deleted
        //TODO Relationships with deletion
        return null;
    }
}
