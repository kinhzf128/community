package com.kinhzf128.community.mapper;

import com.kinhzf128.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author kinhzf128
 * @Date 2020/5/27 20:35
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    public void insert(Question question);

    @Select("select * from question limit #{limitStart},#{pageCount}")
    public List<Question> selectAll(int limitStart, int pageCount);

    @Select("select count(*) from question")
    public int selectCount();
}
