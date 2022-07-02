package com.example.testpictarine.controller.inputs;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class ArticleInput {

    @NotNull
    private String title;

    @NotNull
    private String body;


    public ArticleInput() {

    }

    public ArticleInput(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}
