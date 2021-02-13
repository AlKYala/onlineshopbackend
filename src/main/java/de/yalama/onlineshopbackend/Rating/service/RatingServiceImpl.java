package de.yalama.onlineshopbackend.Rating.service;

import de.yalama.onlineshopbackend.Rating.model.Rating;
import de.yalama.onlineshopbackend.Rating.repository.RatingRepository;
import de.yalama.onlineshopbackend.shared.service.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RatingServiceImpl extends RatingService {

    private Validator<Rating, RatingRepository> validator;
    private RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
        this.validator = new Validator<Rating, RatingRepository>("Rating", ratingRepository);
    }

    @Override
    public Rating findById(Long id) {
        this.validator.checkEntityExists(id);
        return this.ratingRepository.findById(id).get();
    }

    @Override
    public List<Rating> findAll() {
        return this.ratingRepository.findAll();
    }

    @Override
    public Rating save(Rating instance) {
        this.validator.checkEntityNotExists(instance.getId());
        return this.ratingRepository.save(instance);
    }

    @Override
    public Rating update(Long id, Rating instance) {
        this.validator.checkCanUpdate(id, instance.getId());
        return this.ratingRepository.save(instance);
    }

    @Override
    public Long deleteById(Long id) {
        this.validator.checkEntityExists(id);
        this.ratingRepository.deleteById(id);
        return id;
    }
}
