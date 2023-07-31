package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    @Override
    public Comment getCommentById(Integer id) {
        return null;
    }

    @Override
    public CommentDto addComment(Integer adPk, CommentDto commentDto) {
        return null;
    }

    @Override
    public ResponseWrapperComment getCommentsByAdId(Integer adPk) {
        return null;
    }


    @Override
    public void deleteComment(Integer adPk, Integer id) {

    }

    @Override
    public CommentDto updateComment(Integer adPk, Integer id, CommentDto commentDto) {
        return null;
    }
}
