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
import ru.skypro.homework.service.ImageService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;
    private final UserRepository userRepository;
    private final ImageService imageService;


    // метод для поиска обьявлений
    private Ads find(Integer id){
        return adsRepository.findById(id)
                .orElseThrow(()-> new AdsNotFoundException("Не найдено обьявление с id: " + id));
    }
    // получить все обьявления
    @Override
    public AdsDto getAllAds() {
        List<Ads> adsList = adsRepository.findAll();
        List<AdDto> adDtoList = adsMapper.toAdDtoList(adsList);
        AdsDto adsDto = new AdsDto();
        adsDto.setCount(adsList.size());
        adsDto.setResults(adDtoList);
        return adsDto;
    }

    // создание обьявления
    @Override
    public AdDto addAds(CreateOrUpdateAd createOrUpdateAd, MultipartFile image, String username) {
        Ads ads = adsMapper.toAds(createOrUpdateAd);
        ads.setAuthor(userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username)));
        ads.setImage(imageService.saveImage(image, "/ads"));
        adsRepository.save(ads);
        return adsMapper.toAdDto(ads);
    }

    // поиск обьявления по id
    @Override
    public ExtendedAd getAdsById(Integer id) {
        Ads ads = find(id);
        return adsMapper.toExtendedAd(ads);
    }

    //удаление обьявления
    @Override
    @Transactional
    public void removeAds(Integer id) {
        Ads ads = find(id);
        imageService.deleteImage(ads.getImage());
        adsRepository.delete(ads);

    }

    //обновление обьявления по его id
    @Override
    public AdDto updateAds(Integer id, CreateOrUpdateAd createOrUpdateAd) {
        Ads ads = find(id);
        adsMapper.update(createOrUpdateAd, ads);
        adsRepository.save(ads);
        return adsMapper.toAdDto(ads);
    }

    //обновление картинки обьявления
    @Override
    public Ads updateImage(Integer id, MultipartFile image) {
        Ads ads = find(id);
        imageService.deleteImage(ads.getImage());
        ads.setImage(imageService.saveImage(image, "/ads"));
        return adsRepository.save(ads);
    }

    // получения списка обьявления которые принадлежат пользователю
    @Override
    public AdsDto getMyAds(String username) {
        List<Ads> adsList = adsRepository.findByAuthor(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username)));
        List<AdDto> adDtoList = adsMapper.toAdDtoList(adsList);
        AdsDto adsDto = new AdsDto();
        adsDto.setResults(adDtoList);
        adsDto.setCount(adsList.size());
        return adsDto;
    }

    // получение картинки обьявления
    @Override
    public byte[] getImage(String name)throws IOException {
        return imageService.getImage(name);
    }
}
