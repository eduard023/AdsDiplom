package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.Comment;

public interface CommentService {


    CommentDto addComment(Integer adId, CreateOrUpdateCommentDTO commentDto, String username);

    CommentsDto getCommentsByAdId(Integer adId);

    void deleteComment(Integer adId, Integer id);

    CommentDto updateComment(Integer adId, Integer id, CreateOrUpdateCommentDTO commentDTO);
}
