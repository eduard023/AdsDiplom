package ru.skypro.homework.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.service.CommentService;

@RestController
@RequestMapping("/ads")
@CrossOrigin(value = "http://localhost:3000")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/{ad_pk}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable(name = "ad_pk") Integer adPk,
                                                 @RequestBody CommentDto commentDto){

        return ResponseEntity.ok(commentService.addComment(adPk, commentDto));
    }

    @GetMapping("/{ad_pk}comments")
    public ResponseEntity<ResponseWrapperComment> getComment(@PathVariable Integer adPk){
        return ResponseEntity.ok(commentService.getCommentsByAdId(adPk));
    }

    @DeleteMapping("/{ad_pk}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "ad_pk") Integer adPk,
                                           @PathVariable Integer id){
        commentService.deleteComment(adPk, id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{ad_pk}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(name = "ad_pk") Integer adPk,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(adPk, commentId, commentDto));
    }
}
