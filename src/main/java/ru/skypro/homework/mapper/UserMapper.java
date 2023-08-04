package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserPrincipalDto;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "username")
    UserDto toUserDto(User user);
    User toUserReg(Register register);

    UserPrincipalDto toUserPrincipalDto(User user);

    void update(UpdateUser updateUser, @MappingTarget User user);
}
