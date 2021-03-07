package de.yalama.onlineshopbackend.Advertisement.service;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.shared.models.SearchQuery;
import de.yalama.onlineshopbackend.shared.service.BaseService;

public abstract class AdvertisementService implements BaseService<Advertisement> {

    public abstract Advertisement[] filter(SearchQuery searchQuery);
}
