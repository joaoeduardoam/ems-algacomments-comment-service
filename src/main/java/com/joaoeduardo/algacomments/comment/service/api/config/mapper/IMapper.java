package com.joaoeduardo.algacomments.comment.service.api.config.mapper;


import com.joaoeduardo.algacomments.comment.service.api.model.dto.in.*;
import com.joaoeduardo.algacomments.comment.service.api.model.dto.out.*;
import com.joaoeduardo.algacomments.comment.service.domain.model.*;
import org.mapstruct.*;

import static org.mapstruct.MappingConstants.ComponentModel.*;

@Mapper(componentModel = SPRING)
public interface IMapper {


//    @Mapping(source = "id.value", target = "id")
    CommentOutput toCommentOutput(Comment comment);


    //    @Mapping(target = "enabled", constant = "false")
//    @Mapping(target = "id", source = "")
//    @Mapping(target = "createdAt", source = "")
//    @Mapping(target = "approved", source = "")
    Comment toComment(CommentInput commentInput);

    @Mapping(source = "id", target = "commentId")
    ModerationInput toModerationInput(Comment comment);

}
