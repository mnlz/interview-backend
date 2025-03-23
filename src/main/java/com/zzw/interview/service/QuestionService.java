package com.zzw.interview.service;

import com.zzw.interview.entity.Question;

import java.util.List;
import java.util.Map;

/**
 * 面试题服务接口
 */
public interface QuestionService {
    
    /**
     * 获取所有面试题
     * @return 面试题列表
     */
    List<Question> getAllQuestions();
    
    /**
     * 根据ID获取面试题
     * @param id 面试题ID
     * @return 面试题
     */
    Question getQuestionById(Integer id);
    
    /**
     * 根据条件筛选面试题
     * @param post 岗位
     * @param company 公司
     * @return 面试题列表
     */
    List<Question> getQuestionsByCondition(String post, String company);
    
    /**
     * 更新阅读量
     * @param id 面试题ID
     * @return 是否更新成功
     */
    boolean updateReadCount(Integer id);
    
    /**
     * 分页获取面试题
     * @param page 页码
     * @param size 每页大小
     * @param post 岗位筛选条件
     * @param company 公司筛选条件
     * @param keyword 关键词搜索
     * @param timeRange 时间范围
     * @return 分页结果
     */
    Map<String, Object> getQuestionsByPage(int page, int size, String post, String company, String keyword, String timeRange);
    
    /**
     * 根据多个公司名称筛选面试题
     * @param companies 公司名称数组
     * @return 面试题列表
     */
    List<Question> getQuestionsByCompanies(String[] companies);
}
