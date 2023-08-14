package ru.skypro.homework.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDTO;
import ru.skypro.homework.entity.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    List<CommentDto> toCommentDtoList(List<Comment> commentList);

    Comment toComment(CreateOrUpdateCommentDTO createOrUpdateCommentDto);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.image", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    CommentDto toCommentDto(Comment comment);

}
