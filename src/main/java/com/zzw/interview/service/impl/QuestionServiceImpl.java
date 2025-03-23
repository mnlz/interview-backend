package com.zzw.interview.service.impl;

import com.zzw.interview.entity.Question;
import com.zzw.interview.mapper.QuestionMapper;
import com.zzw.interview.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 面试题服务实现类
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    
    @Autowired
    private QuestionMapper questionMapper;
    
    @Override
    public List<Question> getAllQuestions() {
        return questionMapper.findAll();
    }
    
    @Override
    public Question getQuestionById(Integer id) {
        return questionMapper.findById(id);
    }
    
    @Override
    public List<Question> getQuestionsByCondition(String post, String company) {
        return questionMapper.findByCondition(post, company);
    }
    
    @Override
    public boolean updateReadCount(Integer id) {
        return questionMapper.updateReadCount(id) > 0;
    }
    
    @Override
    public Map<String, Object> getQuestionsByPage(int page, int size, String post, String company, String keyword, String timeRange) {
        // 计算分页参数
        int offset = (page - 1) * size;
        
        // 处理时间范围
        Date startDate = null;
        if (timeRange != null && !timeRange.isEmpty()) {
            startDate = calculateStartDate(timeRange);
        }
        
        // 查询总记录数
        int total = questionMapper.countByCondition(post, company, keyword, startDate);
        
        // 查询分页数据
        List<Question> questions = questionMapper.findByPageAndCondition(offset, size, post, company, keyword, startDate);
        
        // 构建返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("data", questions);
        result.put("page", page);
        result.put("size", size);
        
        return result;
    }
    
    @Override
    public List<Question> getQuestionsByCompanies(String[] companies) {
        if (companies == null || companies.length == 0) {
            return new ArrayList<>();
        }
        return questionMapper.findByCompanies(companies);
    }
    
    /**
     * 根据时间范围字符串计算起始日期
     * @param timeRange 时间范围
     * @return 起始日期
     */
    private Date calculateStartDate(String timeRange) {
        Calendar calendar = Calendar.getInstance();
        
        switch(timeRange) {
            case "week":
                calendar.add(Calendar.DAY_OF_MONTH, -7);
                break;
            case "month":
                calendar.add(Calendar.MONTH, -1);
                break;
            case "quarter":
                calendar.add(Calendar.MONTH, -3);
                break;
            case "year":
                calendar.add(Calendar.YEAR, -1);
                break;
            default:
                return null;
        }
        
        return calendar.getTime();
    }
}
