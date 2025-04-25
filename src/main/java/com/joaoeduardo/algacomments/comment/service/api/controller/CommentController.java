package com.joaoeduardo.algacomments.comment.service.api.controller;

import com.joaoeduardo.algacomments.comment.service.api.client.*;
import com.joaoeduardo.algacomments.comment.service.api.config.mapper.*;
import com.joaoeduardo.algacomments.comment.service.api.model.dto.in.*;
import com.joaoeduardo.algacomments.comment.service.api.model.dto.out.*;
import com.joaoeduardo.algacomments.comment.service.domain.model.*;
import com.joaoeduardo.algacomments.comment.service.domain.repository.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

import java.util.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final ModerationCommentClient moderationCommentClient;

    private final CommentRepository commentRepository;

    private final IMapper mapper;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CommentDetailOutput> getAll(@PageableDefault Pageable pageable) {

        Page<Comment> comments = commentRepository.findAll(pageable);

        return comments.map(comment -> {
            // Primeiro, converte Comment para CommentOutput
            CommentOutput commentOutput = mapper.toCommentOutput(comment);

            // Segundo, cria ModerationInput a partir do Comment
            ModerationInput moderationInput = mapper.toModerationInput(comment);

            // Terceiro, envia para moderação e obtém resultado
            ModerationOutput moderationOutput = moderationCommentClient.moderate(moderationInput);

            // Por fim, constrói o output final combinando os dois resultados
            return CommentDetailOutput.builder()
                    .commentOutput(commentOutput)
                    .moderationOutput(moderationOutput)
                    .build();
        });
    }



    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDetailOutput getOne(@PathVariable UUID id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var moderationOutput = moderationCommentClient.moderate(new ModerationInput(comment.getId().toString(),comment.getText()));

        //return mapper.toCommentOutput(comment);

        return CommentDetailOutput.builder()
                .commentOutput(mapper.toCommentOutput(comment))
                .moderationOutput(moderationOutput)
                .build();
    }
//
//    @GetMapping("{commentId}/detail")
//    public CommentDetailOutput getOneWithDetail(@PathVariable TSID commentId) {
//        Comment comment = commentRepository.findById(new UUID(commentId))
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//
//        CommentMonitoringOutput commentMonitoringOutput = commentMonitoringClient.getDetail(commentId);
//
//        return CommentDetailOutput.builder()
//                .commentOutput(mapper.toCommentOutput(comment))
//                .commentMonitoringOutput(commentMonitoringOutput)
//                .build();
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDetailOutput create(@RequestBody CommentInput commentInput) {

        var comment = mapper.toComment(commentInput);

        comment = commentRepository.saveAndFlush(comment);

        var moderationOutput = moderationCommentClient.moderate(new ModerationInput(comment.getId().toString(),commentInput.text()));

        //return mapper.toCommentOutput(comment);

        return new CommentDetailOutput(mapper.toCommentOutput(comment), moderationOutput);
    }



}
