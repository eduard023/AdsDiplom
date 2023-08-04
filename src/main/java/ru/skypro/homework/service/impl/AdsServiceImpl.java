package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.UsernameNotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;
    private final UserRepository userRepository;

    private Ads find(Integer id){
        return adsRepository.findById(id)
                .orElseThrow(()-> new AdsNotFoundException("Не найдено обьявление с id: " + id));
    }
    @Override
    public AdsDto getAllAds() {
        List<Ads> adsList = adsRepository.findAll();
        List<AdDto> adDtoList = adsMapper.toAdDtoList(adsList);
        AdsDto adsDto = new AdsDto();
        adsDto.setCount(adsList.size());
        adsDto.setResult(adDtoList);
        return adsDto;
    }

    @Override
    public AdDto addAds(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username) {
        Ads ads = adsMapper.toAds(createOrUpdateAd);
        ads.setAuthor(userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username)));
        adsRepository.save(ads);
        return adsMapper.toAdDto(ads);
    }

    @Override
    public ExtendedAd getAdsById(Integer id) {
        Ads ads = find(id);
        return adsMapper.toExtendedAd(ads);
    }

    @Override
    @Transactional
    public void removeAds(Integer id) {
        Ads ads = find(id);
        adsRepository.delete(ads);

    }

    @Override
    public AdDto updateAds(Integer id, CreateOrUpdateAd createOrUpdateAd) {
        Ads ads = find(id);
        adsMapper.update(createOrUpdateAd, ads);
        adsRepository.save(ads);
        return adsMapper.toAdDto(ads);
    }

    @Override
    public String updateImage(Integer id, MultipartFile image) {
        return null;
    }

    @Override
    public AdsDto getMyAds(String username) {
        List<Ads> adsList = adsRepository.findByAuthor(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username)));
        List<AdDto> adDtoList = adsMapper.toAdDtoList(adsList);
        AdsDto adsDto = new AdsDto();
        adsDto.setResult(adDtoList);
        adsDto.setCount(adsList.size());
        return adsDto;
    }
}
