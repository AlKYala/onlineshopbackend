package de.yalama.onlineshopbackend.Marke.service;

import de.yalama.onlineshopbackend.Marke.model.Marke;
import de.yalama.onlineshopbackend.shared.service.BaseService;

public abstract class MarkeService implements BaseService<Marke> {
    public abstract Boolean existsByName(String name);
}
