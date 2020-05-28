package com.kinhzf128.community.model;

import lombok.Data;

/**
 * @author kinhzf128
 * @Date 2020/5/27 15:12
 */
@Data
public class User {
    private int id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatarUrl;

}
