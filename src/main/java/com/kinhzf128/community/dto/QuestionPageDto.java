package com.kinhzf128.community.dto;

import java.util.List;

/**
 * @author kinhzf128
 * @Date 2020/5/29 14:14
 */
public class QuestionPageDto {
    //当前页数
    private int page;
    //每页条数
    private int pageCount;
    //最大页数
    private int pageMax;
    //往数据库分页中要插入的起始值
    private int limitStart;
    // 一共有多少行数
    private int CountMax;
    //当前页封装的数据
    private List<QuestionDto> questionDtos;

    public int getCountMax() {
        return CountMax;
    }

    public void setCountMax(int countMax) {
        CountMax = countMax;
    }

    //通过当前页和每页个数，直接算出分页起始点
    public int getLimitStart() {
        setLimitStart((page-1)*pageCount);
        return (page-1)*pageCount;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart = limitStart;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageMax() {
        if (CountMax%pageCount==0){
            setPageMax(CountMax/pageCount);
            return CountMax/pageCount;
        }else {
            setCountMax((CountMax/pageCount)+1);
            return (CountMax/pageCount)+1;
        }
    }

    public void setPageMax(int pageMax) {
        this.pageMax = pageMax;
    }

    public List<QuestionDto> getQuestionDtos() {
        return questionDtos;
    }

    public void setQuestionDtos(List<QuestionDto> questionDtos) {
        this.questionDtos = questionDtos;
    }
}
