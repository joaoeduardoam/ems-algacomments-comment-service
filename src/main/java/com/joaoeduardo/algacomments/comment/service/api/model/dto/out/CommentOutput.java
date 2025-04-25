package com.joaoeduardo.algacomments.comment.service.api.model.dto.out;


import lombok.*;

import java.time.*;
import java.util.*;

@Builder
public record CommentOutput(
        UUID id,
        String text,
        String author,
        LocalDateTime createdAt

){}
