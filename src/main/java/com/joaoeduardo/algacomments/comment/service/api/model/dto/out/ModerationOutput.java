package com.joaoeduardo.algacomments.comment.service.api.model.dto.out;


import lombok.*;

@Builder
public record ModerationOutput(
        Boolean approved,
        String reason
){}
