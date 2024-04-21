package com.istad.springsecuritybasic.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {
    @GetMapping
    public String getAllArticle() {
        return "Getting all articles.";
    }

    @GetMapping("/read/{id}")
    public String readSingleArticle(@PathVariable int id) {
        return "Reading a single article.";
    }

    @PostMapping("/write")
    public String createArticle() {
        return "Create an article";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteArticle(@PathVariable int id) {
        return "Deleting an article.";
    }
}