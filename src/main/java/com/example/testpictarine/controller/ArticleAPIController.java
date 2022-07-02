package com.example.testpictarine.controller;


import com.example.testpictarine.model.Article;
import com.example.testpictarine.services.ArticleAPIService;
import org.springframework.http.MediaType;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "${api.basePath}/article", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleAPIController {

    private ArticleAPIService articleAPIService;

    public ArticleAPIController(ArticleAPIService articleAPIService) {
        this.articleAPIService = articleAPIService;
    }


    @GetMapping("/getAllArticles")
    public List<Article> getAllArticles() {
        return articleAPIService.findAll();
    }

    @PostMapping("/addArticle")
    public Article addArticle(@RequestParam("body") String body, @RequestParam("title") String title, @RequestParam("image") MultipartFile image) throws IOException {
        Article article = null;
        try {
            article = articleAPIService.addArticle(title, body, image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return article;
    }

    @PostMapping("/modifyTitleArticle")
    public Article modifyTitleArticle(@RequestParam("id") Long id, @RequestParam("title") String title) throws IOException {
        Article article = null;
        article = articleAPIService.modifyTitleArticle(id, title);
        return article;
    }

    @PostMapping("/modifyBodyArticle")
    public Article modifyBodyArticle(@RequestParam("id") Long id, @RequestParam("body") String body) {
        Article article = null;
        article = articleAPIService.modifyBodyArticle(id, body);
        return article;
    }

    @PostMapping("/modifyImageArticle")
    public Article modifyImageArticle(@RequestParam("id") Long id, @RequestParam("image") MultipartFile image) {
        Article article = null;
        try {
            article = articleAPIService.modifyImageArticle(id, image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return article;
    }

    @DeleteMapping("/deleteArticle")
    public void deleteArticle(@RequestParam("id") Long id) {
        Article article = null;
        try {
            articleAPIService.deleteArticle(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}