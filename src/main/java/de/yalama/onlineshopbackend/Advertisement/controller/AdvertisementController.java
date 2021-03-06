package de.yalama.onlineshopbackend.Advertisement.controller;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.Advertisement.service.AdvertisementService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import de.yalama.onlineshopbackend.shared.models.SearchQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ad")
@RequiredArgsConstructor
public class AdvertisementController implements BaseController<Advertisement, Long> {

    @Autowired
    private AdvertisementService advertisementService;

    @Override
    @GetMapping
    public List<Advertisement> findAll() {
        return this.advertisementService.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Advertisement findById(@PathVariable Long id) {
        return this.advertisementService.findById(id);
    }

    @Override
    @PostMapping
    public Advertisement create(@RequestBody Advertisement advertisement) {
        return this.advertisementService.save(advertisement);
    }

    @Override
    @PutMapping("/{id}")
    public Advertisement update(@PathVariable Long id, @RequestBody Advertisement advertisement) {
        return this.advertisementService.update(id, advertisement);
    }

    @Override
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return this.advertisementService.deleteById(id);
    }

    @PostMapping("/filter")
    public Advertisement[] getFilteredAds(@RequestBody SearchQuery searchQuery) {
        return this.advertisementService.filter(searchQuery);
    }

    @GetMapping("/featured")
    public Advertisement[] getFeaturedAds() {
        SearchQuery featuredQuery = new SearchQuery();
        featuredQuery.setFeatured(true);
        return this.advertisementService.filter(featuredQuery);
    }
}
