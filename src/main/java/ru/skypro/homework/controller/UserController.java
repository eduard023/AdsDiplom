package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.UserService;
@RestController

@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/set_password")
    @Operation(summary = "обновление пароля")
    @ApiResponse(responseCode = "200",
    description = "операция успешна")
    @ApiResponse(responseCode = "401",
    description = "ошибка авторизации")
    @ApiResponse(responseCode = "403",
    description = "операция запрещена")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword){
        userService.updatePassword(newPassword);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<?> getUser(){
        ru.skypro.homework.entity.User user = userService.getUser();
        return ResponseEntity.ok().body(user);
    }



    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<?>updateUser(@RequestBody User user){
        userService.updateUser(user);
        return ResponseEntity.ok().body(user);
    }



    @PatchMapping(value = "me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<?>updateUserImage(@RequestParam("image")MultipartFile file){
        userService.updateUserImage(file);
        return ResponseEntity.ok().build();
    }

}
