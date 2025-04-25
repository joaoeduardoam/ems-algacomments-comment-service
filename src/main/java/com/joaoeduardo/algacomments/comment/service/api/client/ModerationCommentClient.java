package com.joaoeduardo.algacomments.comment.service.api.client;

import com.joaoeduardo.algacomments.comment.service.api.model.dto.in.*;
import com.joaoeduardo.algacomments.comment.service.api.model.dto.out.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.*;


@HttpExchange("/api/moderate")
public interface ModerationCommentClient {

    @PostExchange
    ModerationOutput moderate(@RequestBody ModerationInput moderationInput);
}
