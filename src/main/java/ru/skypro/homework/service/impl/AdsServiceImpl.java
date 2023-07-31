package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAds;
import ru.skypro.homework.service.AdsService;

@Service
public class AdsServiceImpl implements AdsService {
    @Override
    public ResponseWrapperAds getAllAds() {
        return null;
    }

    @Override
    public AdsDto addAds(CreateAdsDto createAdsDto, MultipartFile image) {
        return null;
    }

    @Override
    public FullAdsDto getFullAdsById(Integer id) {
        return null;
    }

    @Override
    public void removeAds(Integer id) {

    }

    @Override
    public AdsDto updateAds(Integer id, CreateAdsDto createAdsDto) {
        return null;
    }

    @Override
    public ResponseWrapperAds getMyAds() {
        return null;
    }
}
