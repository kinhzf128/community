package com.kinhzf128.community.service;

import com.kinhzf128.community.dto.QuestionDto;
import com.kinhzf128.community.dto.QuestionPageDto;
import com.kinhzf128.community.mapper.QuestionMapper;
import com.kinhzf128.community.mapper.UserMapper;
import com.kinhzf128.community.model.Question;
import com.kinhzf128.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kinhzf128
 * @Date 2020/5/29 10:31
 */
@Service
public class QuestionService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;

    /**
    * @author kinhzf128
    * @date 2020/5/29 14:27
    * @param [page, pageCount]
    * @return java.util.List<com.kinhzf128.community.dto.QuestionDto>
     *查询列表
    */
    public QuestionPageDto selectAll(QuestionPageDto questionPageDto){
        int i=questionPageDto.getLimitStart();
        int i2=questionPageDto.getPageCount();
        List<Question> questions = questionMapper.selectAll(questionPageDto.getLimitStart(),questionPageDto.getPageCount());
        List<QuestionDto> questionDtoList=new ArrayList<>();
        for (Question question:questions){
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            User user = userMapper.findById(question.getCreator());
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        questionPageDto.setCountMax(questionMapper.selectCount());
        questionPageDto.setQuestionDtos(questionDtoList);
        return questionPageDto;
    }

}
