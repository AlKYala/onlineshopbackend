package de.yalama.onlineshopbackend.Rating.controller;

import de.yalama.onlineshopbackend.Rating.model.Rating;
import de.yalama.onlineshopbackend.Rating.service.RatingService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController implements BaseController<Rating, Long> {

    @Autowired
    private RatingService ratingService;

    @Override
    @GetMapping
    public List<Rating> findAll() {
        return this.ratingService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Rating findById(@PathVariable Long id) {
        return this.ratingService.findById(id);
    }

    @Override
    @PostMapping
    public Rating create(@RequestBody Rating rating) {
        return this.ratingService.save(rating);
    }

    @Override
    @PutMapping("/{id}")
    public Rating update(@PathVariable Long id, @RequestBody Rating rating) {
        return this.ratingService.update(id, rating);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.ratingService.deleteById(id);
    }
}
