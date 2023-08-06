package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public boolean login(String username, String password) {
        Optional<ru.skypro.homework.entity.User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            return false;
        }
        return encoder.matches(password, optionalUser.get().getPassword());
    }

    @Override
    public boolean register(Register register, Role role) {
        if (userRepository.findByUsername(register.getUsername()).isPresent()) {
            return false;
        }
        ru.skypro.homework.entity.User user = userMapper.toUserReg(register);
        user.setUsername(user.getUsername());
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(role);
        userRepository.save(user);
        return true;
    }

}
