package ru.skypro.homework.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public ru.skypro.homework.entity.User getUser() {
        return null;
    }

    @Override
    public void updatePassword(NewPassword newPassword) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void updateUserImage(MultipartFile file) {

    }
}
