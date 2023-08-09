package ru.skypro.homework.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.UsernameNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final ImageService imageService;

    private User find(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }



    @Override
    public UserDto getUser(String username) {
        User user = find(username);
       return userMapper.toUserDto(user);
    }

    @Override
    public boolean updatePassword(NewPassword newPassword, String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (encoder.matches(newPassword.getCurrentPassword(), user.getPassword())){
                user.setPassword(encoder.encode(newPassword.getNewPassword()));
                userRepository.save(user);
                log.trace("Пароль обновлен");
                return true;
            }
        }
        log.trace("Пароль не обновлен");
        return false;
    }

    @Override
    public UserDto updateUser(UpdateUser updateUser, String username) {
        User user = find(username);
        userMapper.update(updateUser, user);
        userRepository.save(user);
        log.trace("Пользователь обновлен");
        return userMapper.toUserDto(user);
    }

    @Override
    public void updateUserImage(MultipartFile image, String username) {
        User user = find(username);
        imageService.deleteImage(user.getImage());
        user.setImage(imageService.saveImage(image, "/users"));
        userRepository.save(user);
        log.trace("Фото профиля обновлено");
    }

    @Override
    public byte[] getImage(String name) throws IOException {
        return imageService.getImage(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws org.springframework.security.core.userdetails.UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new org.springframework.security.core.userdetails.UsernameNotFoundException(username));
        return new UserPrincipalDetailsImpl(userMapper.toUserPrincipalDto(user));
    }
}
