package com.zzw.interview.controller;

import com.zzw.interview.entity.Question;
import com.zzw.interview.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面试题控制器
 */
@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    
    @Autowired
    private QuestionService questionService;
    
    /**
     * 获取所有面试题（分页）
     * @param page 页码
     * @param size 每页大小
     * @param post 岗位筛选条件
     * @param company 公司筛选条件
     * @param keyword 关键词搜索
     * @param timeRange 时间范围
     * @return 分页结果
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getQuestionsByPage(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String post,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String timeRange) {
        
        Map<String, Object> result = questionService.getQuestionsByPage(page, size, post, company, keyword, timeRange);
        return ResponseEntity.ok(result);
    }
    
    /**
     * 获取所有面试题（不分页，兼容旧版本）
     * @return 面试题列表
     */
    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }
    
    /**
     * 根据ID获取面试题
     * @param id 面试题ID
     * @return 面试题
     */
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer id) {
        Question question = questionService.getQuestionById(id);
        if (question != null) {
            // 更新阅读量
            questionService.updateReadCount(id);
            return ResponseEntity.ok(question);
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 根据条件筛选面试题
     * @param post 岗位
     * @param company 公司
     * @return 面试题列表
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Question>> getQuestionsByCondition(
            @RequestParam(required = false) String post,
            @RequestParam(required = false) String company) {
        return ResponseEntity.ok(questionService.getQuestionsByCondition(post, company));
    }
    
    /**
     * 获取所有岗位和公司列表（用于筛选）
     * @return 岗位和公司列表
     */
    @GetMapping("/options")
    public ResponseEntity<Map<String, Object>> getFilterOptions() {
        List<Question> questions = questionService.getAllQuestions();
        
        // 提取所有不重复的岗位和公司
        Map<String, Boolean> posts = new HashMap<>();
        Map<String, Boolean> companies = new HashMap<>();
        
        for (Question question : questions) {
            if (question.getPost() != null && !question.getPost().isEmpty()) {
                posts.put(question.getPost(), true);
            }
            if (question.getCompany() != null && !question.getCompany().isEmpty()) {
                companies.put(question.getCompany(), true);
            }
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("posts", posts.keySet());
        result.put("companies", companies.keySet());
        
        return ResponseEntity.ok(result);
    }
    
    /**
     * 根据多个公司名称筛选面试题（支持公司系列筛选）
     * @param companies 多个公司名称，逗号分隔
     * @return 面试题列表
     */
    @GetMapping("/bySeries")
    public ResponseEntity<List<Question>> getQuestionsByCompanies(
            @RequestParam String companies) {
        String[] companyArray = companies.split(",");
        return ResponseEntity.ok(questionService.getQuestionsByCompanies(companyArray));
    }
}
