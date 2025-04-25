package com.joaoeduardo.algacomments.comment.service.domain.repository;

import com.joaoeduardo.algacomments.comment.service.domain.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;


public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
