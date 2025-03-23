package com.zzw.interview.mapper;

import com.zzw.interview.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 面试题Mapper接口
 */
@Mapper
public interface QuestionMapper {
    
    /**
     * 获取所有面试题
     * @return 面试题列表
     */
    List<Question> findAll();
    
    /**
     * 根据ID获取面试题
     * @param id 面试题ID
     * @return 面试题
     */
    Question findById(@Param("id") Integer id);
    
    /**
     * 根据条件筛选面试题
     * @param post 岗位
     * @param company 公司
     * @return 面试题列表
     */
    List<Question> findByCondition(@Param("post") String post, @Param("company") String company);
    
    /**
     * 更新阅读量
     * @param id 面试题ID
     * @return 影响行数
     */
    int updateReadCount(@Param("id") Integer id);
    
    /**
     * 根据条件统计记录数
     * @param post 岗位
     * @param company 公司
     * @param keyword 关键词
     * @param startDate 起始日期
     * @return 记录数
     */
    int countByCondition(@Param("post") String post, 
                         @Param("company") String company, 
                         @Param("keyword") String keyword, 
                         @Param("startDate") Date startDate);
    
    /**
     * 根据条件分页查询
     * @param offset 偏移量
     * @param size 每页大小
     * @param post 岗位
     * @param company 公司
     * @param keyword 关键词
     * @param startDate 起始日期
     * @return 面试题列表
     */
    List<Question> findByPageAndCondition(@Param("offset") int offset, 
                                          @Param("size") int size,
                                          @Param("post") String post, 
                                          @Param("company") String company,
                                          @Param("keyword") String keyword, 
                                          @Param("startDate") Date startDate);
    
    /**
     * 根据多个公司名称查询
     * @param companies 公司名称数组
     * @return 面试题列表
     */
    List<Question> findByCompanies(@Param("companies") String[] companies);
}
