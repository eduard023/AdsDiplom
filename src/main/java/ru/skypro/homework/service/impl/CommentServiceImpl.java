package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.Ads;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;




    private Comment find(Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Комментарий не найден"));
    }

    @Override
    public CommentDto addComment(Integer adId, CreateOrUpdateCommentDTO commentDto, String username) {
        Ads ads = adsRepository.findById(adId)
                .orElseThrow(() -> new AdsNotFoundException("Обявление не найдено"));

        Comment comment = commentMapper.toComment(commentDto);
        comment.setAds(ads);
        comment.setAuthor(userRepository.findByUsername(username).get());
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        log.trace("Добавлен комментарий с id: ", comment.getId());
        return commentMapper.toCommentDto(comment);
    }

    @Override
    public CommentsDto getCommentsByAdId(Integer adId) {
        List<Comment> commentList = commentRepository.findAllByAdsId(adId);
        List<CommentDto> commentDtoList = commentMapper.toCommentDtoList(commentList);
        CommentsDto commentsDto = new CommentsDto();
        commentsDto.setResults(commentDtoList);
        commentsDto.setCount(commentDtoList.size());
        return commentsDto;
    }


    @Override
    @Transactional
    public void deleteComment(Integer adId, Integer id) {
       Comment comment = find(id);
       if (comment.getAds().getId() != adId){
           throw new RuntimeException();
       }
       commentRepository.delete(comment);
       log.trace("Удален комментарий с id: ", id);
    }

    @Override
    public CommentDto updateComment(Integer adId, Integer id, CreateOrUpdateCommentDTO commentDTO) {
        Comment comment = find(id);
        comment.setText(commentDTO.getText());
        commentRepository.save(comment);
        log.trace("Обновлен комментарий с id: ", id);
        return commentMapper.toCommentDto(comment);
    }

    public String getCommentOfUSer(Integer id){
        return find(id).getAuthor().getUsername();
    }
}
