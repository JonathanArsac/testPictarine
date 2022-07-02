package com.example.testpictarine.services;

import com.example.testpictarine.controller.inputs.ArticleInput;
import com.example.testpictarine.exception.ArticleServiceException;
import com.example.testpictarine.model.Article;
import com.example.testpictarine.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ArticleAPIService {

    private ArticleRepository articleRepository;

    @Value("${project.image}")
    private String path;

    public ArticleAPIService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> findAll() {
        return this.articleRepository.findAll();
    }

    public Article modifyBodyArticle(Long id, String body) {
        Optional<Article> oArticle = articleRepository.findById(id);
        Article article = null;
        if (oArticle.isEmpty()) {
            throw new IllegalArgumentException("Article inconnu");
        } else {
            article = oArticle.get();
        }
        article.setBody(body);
        return articleRepository.save(article);


    }

    public Article modifyTitleArticle(Long id, String title) {
        Optional<Article> oArticle = articleRepository.findById(id);
        Article article = null;
        if (oArticle.isEmpty()) {
            throw new IllegalArgumentException("Article inconnu");
        } else {
            article = oArticle.get();
        }
        article.setTitle(title);
        return articleRepository.save(article);
    }

    public Article modifyImageArticle(Long id, MultipartFile file) throws IOException {
        Optional<Article> oArticle = articleRepository.findById(id);
        Article article = null;
        if (oArticle.isEmpty()) {
            throw new IllegalArgumentException("Article inconnu");
        } else {
            article = oArticle.get();
        }
        article.setImageName(this.getFileNameFromMultiPartFile(file));
        return articleRepository.save(article);
    }


    public void deleteArticle(Long id) throws IOException {
        Optional<Article> oArticle = articleRepository.findById(id);
        Article article = null;
        if (oArticle.isEmpty()) {
            throw new IllegalArgumentException("Article inconnu");
        } else {
            article = oArticle.get();
        }
        Path pathToFile = Path.of(path + File.separator + article.getImageName());
        Files.delete(pathToFile);
        articleRepository.delete(article);
    }

    public Article addArticle(String title, String body, MultipartFile file) throws IOException {
        Article article = new Article();
        article.setTitle(title);
        article.setBody(body);


        String fileName = getFileNameFromMultiPartFile(file);
        article.setImageName(fileName);
        return this.articleRepository.save(article);
    }

    private String getFileNameFromMultiPartFile(MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();

        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(name.substring((name.lastIndexOf("."))));
        String filePath = path + File.separator + fileName;

        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
    }

}
