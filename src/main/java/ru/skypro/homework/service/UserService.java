package ru.skypro.homework.service;


import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;

public interface UserService {

UserDto getUser(String username);

boolean updatePassword(NewPassword newPassword, String username);

UserDto updateUser(UpdateUser updateUser, String username);

void updateUserImage(MultipartFile file, String username);

}
