package com.kinhzf128.community.dto;

import lombok.Data;

/**
 * @author kinhzf128
 * @Date 2020/5/26 20:08
 */
@Data
public class AccessTokenDto {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
