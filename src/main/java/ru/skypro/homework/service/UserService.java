package ru.skypro.homework.service;


import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.entity.User;

public interface UserService {

User getUser();

void updatePassword(NewPassword newPassword);

void updateUser(User user);

void updateUserImage(MultipartFile file);

}
