package de.yalama.onlineshopbackend.Pictures.service;

import de.yalama.onlineshopbackend.Advertisement.model.Advertisement;
import de.yalama.onlineshopbackend.Pictures.model.Picture;
import de.yalama.onlineshopbackend.shared.service.BaseService;

import java.util.List;

public abstract class PictureService implements BaseService<Picture> {
    public abstract List<Picture> findPicturesByAdId(Long adId);
    public abstract List<Picture> findPicturesByAd(Advertisement advertisement);
    public abstract Picture[] addAllPictures(Picture[] pictures);
}
