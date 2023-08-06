package ru.skypro.homework.exception;

public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String username){
        super(String.format("Пользователь с логином: %s не найден", username ));
    }
}
