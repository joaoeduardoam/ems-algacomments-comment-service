package com.joaoeduardo.algacomments.comment.service.domain.exceptions;

public class CommentNotFoundException extends RuntimeException{

    public CommentNotFoundException(String message){
        super(message);
    }

    public CommentNotFoundException(){

    }
}
