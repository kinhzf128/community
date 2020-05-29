package com.kinhzf128.community.provider;

import com.alibaba.fastjson.JSON;
import com.kinhzf128.community.dto.AccessTokenDto;
import com.kinhzf128.community.dto.GithubUserDto;
import okhttp3.*;
import org.springframework.stereotype.Component;

/**
 * @author kinhzf128
 * @Date 2020/5/26 20:15
 */
@Component
public class GithubProvide {

    /**
    * @author kinhzf128
    * @date 2020/5/26 20:21
    * @param [accessTokenDto]
    * @return java.lang.String
     * 利用okhttp发送post请求来拿到accesstoken
    */
    public String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String[] split = response.body().string().split("&");
            String[] split1 = split[0].split("=");
            return split1[1];
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public GithubUserDto getGithubUser(String accesstoken){
        OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accesstoken)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                System.out.println(string);
                GithubUserDto githubUserDto = JSON.parseObject(string, GithubUserDto.class);
                return githubUserDto;
            }catch (Exception e){
                e.printStackTrace();
            }
        return null;
    }


}
