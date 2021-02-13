package de.yalama.onlineshopbackend.Advertisement.controller;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.Advertisement.service.AdvertisementService;
import de.yalama.onlineshopbackend.shared.Controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ad")
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
    @PutMapping
    public Advertisement update(@PathVariable Long id, @RequestBody Advertisement advertisement) {
        return this.advertisementService.update(id, advertisement);
    }

    @Override
    public Long delete(Long id) {
        return this.advertisementService.deleteById(id);
    }
}
