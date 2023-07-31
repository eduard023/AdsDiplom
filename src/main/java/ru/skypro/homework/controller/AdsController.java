package ru.skypro.homework.controller;



import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdsService;


@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class AdsController {

    private final AdsService adsService;


    public AdsController(AdsService adsService) {
        this.adsService = adsService;
    }

    //КОНТРОЛЛЕРЫ ДЛЯ ОБЬЯВЛЕНИЙ

    @GetMapping
    public ResponseEntity<ResponseWrapperAds>getAllAds(){
        return ResponseEntity.ok(adsService.getAllAds());
    }



    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdsDto>addAds(@RequestPart MultipartFile image,
                                        @RequestPart CreateAdsDto createAdsDto){
        return ResponseEntity.ok(adsService.addAds(createAdsDto, image));

    }


    @GetMapping("/{id}")
    public ResponseEntity<FullAdsDto> getAds(@PathVariable Integer id){
        return ResponseEntity.ok(adsService.getFullAdsById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeAds(@PathVariable Integer id){
        adsService.removeAds(id);
        return ResponseEntity.ok().build();

    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdsDto> updateAds(@PathVariable Integer id,
                                            @RequestBody CreateAdsDto createAdsDto){
        return ResponseEntity.ok(adsService.updateAds(id, createAdsDto));

    }

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperAds> getAdsMe(){
        return ResponseEntity.ok(adsService.getMyAds());
    }


}
