package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.entity.Ads;

public interface AdsService {


    AdsDto getAllAds();

    AdDto addAds(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username);

    ExtendedAd getAdsById(Integer id);

    void removeAds(Integer id);

    AdDto updateAds(Integer id, CreateOrUpdateAd createOrUpdateAd);

    Ads updateImage(Integer id, MultipartFile image);

    AdsDto getMyAds(String username);

}
