package com.zzw.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 页面控制器
 */
@Controller
public class PageController {
    
    /**
     * 首页
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/index.html";
    }
    
    /**
     * 问题详情页
     */
    @GetMapping("/question/{id}")
    public String questionDetail(@PathVariable Integer id) {
        return "redirect:/question.html?id=" + id;
    }
}
