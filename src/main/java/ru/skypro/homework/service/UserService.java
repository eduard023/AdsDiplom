package ru.skypro.homework.service;


import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;

public interface UserService {

UserDto getUser(String username);

boolean updatePassword(NewPassword newPassword, String username);

UserDto updateUser(UpdateUser updateUser, String username);

void updateUserImage(MultipartFile file, String username);

byte[] getImage(String name) throws IOException;

}
