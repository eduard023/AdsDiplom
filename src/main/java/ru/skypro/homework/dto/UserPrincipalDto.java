package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserPrincipalDto {

    private Integer id;
    private String username;
    private String password;
    private Role role;

}
