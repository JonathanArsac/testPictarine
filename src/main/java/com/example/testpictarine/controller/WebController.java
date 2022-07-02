package com.example.testpictarine.controller;

import com.example.testpictarine.model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {

    private static List<Article> articles = new ArrayList<Article>();

    static {
        articles.add(new Article("Bill", "Gates"));
        articles.add(new Article("Steve", "Jobs"));
    }



    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", "testststs");

        return "index";
    }

}
