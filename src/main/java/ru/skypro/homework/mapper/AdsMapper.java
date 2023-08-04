package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.Ads;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdsMapper {
    Ads toAds(CreateOrUpdateAd createOrUpdateAd);


    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    AdDto toAdDto(Ads ads);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "author.lastName", target = "authorLastName")
    @Mapping(source = "author.username", target = "email")
    @Mapping(source = "author.phone", target = "phone")
    ExtendedAd toExtendedAd(Ads ads);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    List<AdDto> toAdDtoList(List<Ads> ads);

    void update(CreateOrUpdateAd createOrUpdateAd, @MappingTarget Ads ads);
}
