package ru.skypro.homework.controller;



import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;

import java.io.IOException;


@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class AdsController {

    private final AdsService adsService;



    @GetMapping
    @Operation(summary = "Получение всех объявлений")
    public ResponseEntity<AdsDto>getAllAds(){
        return ResponseEntity.ok(adsService.getAllAds());
    }




    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Добавление объявления")
    public ResponseEntity<AdDto>addAd(@RequestPart("image") MultipartFile image,
                                      @RequestPart("properties") CreateOrUpdateAd createOrUpdateAd,
                                       Authentication authentication)  {
        return ResponseEntity.ok(adsService.addAds(createOrUpdateAd, image, authentication.getName()));

    }


    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об объявлении")
    public ResponseEntity<ExtendedAd> getAds(@PathVariable Integer id){
        return ResponseEntity.ok(adsService.getAdsById(id));
    }

    @PreAuthorize("hasRole('ADMIN') or @adsServiceImpl.getAdsById(#id).getEmail() == authentication.principal.username")
    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление объявления")
    public ResponseEntity<?> removeAd(@PathVariable Integer id){
        adsService.removeAds(id);
        return ResponseEntity.ok().build();

    }

    @PreAuthorize("hasRole('ADMIN') or @adsServiceImpl.getAdsById(#id).getEmail() == authentication.principal.username")
    @PatchMapping("/{id}")
    @Operation(summary = "Обновление информации об объявлении")
    public ResponseEntity<AdDto> updateAds(@PathVariable Integer id,
                                           @RequestBody CreateOrUpdateAd createOrUpdateAd){
        return ResponseEntity.ok(adsService.updateAds(id, createOrUpdateAd));

    }

    @GetMapping("/me")
    @Operation(summary = "Получение объявлений авторизованного пользователя")
    public ResponseEntity<AdsDto> getAdsMe(Authentication authentication){
        return ResponseEntity.ok(adsService.getMyAds(authentication.getName()));
    }


    @PatchMapping("{id}/image")
    @Operation(summary = "Обновление картинки объявления")
    public ResponseEntity<?> updateImage(@PathVariable Integer id,
                                              @RequestParam MultipartFile image) {
        return ResponseEntity.ok(adsService.updateImage(id, image));
    }


    @GetMapping(value = "/image/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImages(@PathVariable String name) throws IOException{
        return adsService.getImage(name);
    }


}
