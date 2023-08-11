package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.service.CommentService;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;



    @PostMapping("/{id}/comments")
    @Operation(summary = "Добавление комментария к объявлению")
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer id,
                                                 @RequestBody CreateOrUpdateCommentDTO commentDto,
                                                 Authentication authentication){

        return ResponseEntity.ok(commentService.addComment(id, commentDto, authentication.getName()));
    }

    @GetMapping("/{id}/comments")
    @Operation(summary = "Получение комментариев объявления")
    public ResponseEntity<CommentsDto> getComments(@PathVariable Integer id){
        return ResponseEntity.ok(commentService.getCommentsByAdId(id));
    }

    @PreAuthorize("hasRole ('ADMIN') or @commentServiceImpl.getCommentOfUSer(#commentId) == authentication.principal.username")
    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Удаление комментария")
    public ResponseEntity<?> deleteComment(@PathVariable Integer adId,
                                           @PathVariable Integer commentId){
        commentService.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole ('ADMIN') or @commentServiceImpl.getCommentOfUSer(#commentId) == authentication.principal.username")
    @PatchMapping("/{adId}/comments/{commentId}")
    @Operation(summary = "Обновление комментария")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDTO commentDTO){
        return ResponseEntity.ok(commentService.updateComment(adId, commentId, commentDTO));
    }
}
