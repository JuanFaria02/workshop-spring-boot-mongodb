package com.juanfaria.workshopmongo.dto;


import java.io.Serializable;

import java.time.LocalDateTime;

public class CommentDto implements Serializable {
    private String text;
    private LocalDateTime date;
    private AuthorDto authorDto;

    public CommentDto(){
    }

    public CommentDto(String text, LocalDateTime date, AuthorDto authorDto) {
        this.text = text;
        this.date = date;
        this.authorDto = authorDto;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public void setAuthorDto(AuthorDto authorDto) {
        this.authorDto = authorDto;
    }

}
