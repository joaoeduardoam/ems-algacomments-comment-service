package com.joaoeduardo.algacomments.comment.service.domain.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment {

    @Id
//    @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "BIGINT"))
    private UUID id;
    private String text;
    private String author;
    private LocalDateTime createdAt;


    @PrePersist
    private void prePersist(){
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
    }

}
