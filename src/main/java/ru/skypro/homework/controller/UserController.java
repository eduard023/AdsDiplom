package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;
@RestController

@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    @PostMapping("/set_password")
    @Operation(summary = "обновление пароля")
    @ApiResponse(responseCode = "200",
    description = "операция успешна")
    @ApiResponse(responseCode = "401",
    description = "ошибка авторизации")
    @ApiResponse(responseCode = "403",
    description = "операция запрещена")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword,
                                                   Authentication authentication){
        userService.updatePassword(newPassword, authentication.getName());
        return ResponseEntity.ok(newPassword);
    }



    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<UserDto> getUser(Authentication authentication){
        UserDto user = userService.getUser(authentication.getName());
        return ResponseEntity.ok().body(user);
    }



    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser,
                                              Authentication authentication){
        userService.updateUser(updateUser, authentication.getName());
        return ResponseEntity.ok().body(updateUser);
    }



    @PatchMapping(value = "me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<?>updateUserImage(@RequestParam MultipartFile image,
                                            Authentication authentication){
        userService.updateUserImage(image, authentication.getName());
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

}
