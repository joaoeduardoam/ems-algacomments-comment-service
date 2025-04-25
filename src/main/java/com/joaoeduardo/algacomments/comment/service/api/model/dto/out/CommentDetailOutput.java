package com.joaoeduardo.algacomments.comment.service.api.model.dto.out;


import lombok.*;

import java.time.*;
import java.util.*;

@Builder
public record CommentDetailOutput(
        CommentOutput commentOutput,
        ModerationOutput moderationOutput

){}
