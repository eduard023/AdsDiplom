package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.entity.Comment;

public interface CommentService {

    Comment getCommentById(Integer id);

    CommentDto addComment(Integer adPk, CommentDto commentDto);

    ResponseWrapperComment getCommentsByAdId(Integer adPk);

    void deleteComment(Integer adPk, Integer id);

    CommentDto updateComment(Integer adPk, Integer id, CommentDto commentDto);
}
