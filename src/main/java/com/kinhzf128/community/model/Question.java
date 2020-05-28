package com.kinhzf128.community.model;

import lombok.Data;
import org.springframework.context.annotation.Description;

/**
 * @author kinhzf128
 * @Date 2020/5/27 20:10
 */
@Data
public class Question {
    private int id;
    private String title;
    private String Description;
    private Long gmtCreate;
    private Long gmtModified;
    private int creator;
    private int commentCount;
    private int viewCount;
    private int likeCount;
    private String tag;

}
