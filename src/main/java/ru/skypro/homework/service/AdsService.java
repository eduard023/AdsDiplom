package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAds;

public interface AdsService {


    ResponseWrapperAds getAllAds();

    AdsDto addAds(CreateAdsDto createAdsDto, MultipartFile image);

    FullAdsDto getFullAdsById(Integer id);

    void removeAds(Integer id);

    AdsDto updateAds(Integer id, CreateAdsDto createAdsDto);

    ResponseWrapperAds getMyAds();

}
