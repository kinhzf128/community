package com.kinhzf128.community.dto;

import lombok.Data;

/**
 * @author kinhzf128
 * @Date 2020/5/26 20:57
 */
@Data
public class GithubUser {
    private int id;
    private String name;
    private String email;
    private String bio;
    private String avatarUrl;

}
