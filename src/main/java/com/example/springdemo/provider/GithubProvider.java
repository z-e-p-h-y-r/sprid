package com.example.springdemo.provider;

import com.alibaba.fastjson2.JSON;
import com.example.springdemo.dto.AccessTokenDTO;
import com.example.springdemo.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token?client_id=Ov23linnb3tCEsDnkIuC&client_secret=6d3e0121990544618068331575ccfe55cf72f972&code="+accessTokenDTO.getCode()+"&redirect_url=http://localhost:8887/callback&state=1")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] splits=string.split("&");
            String tokenStr=splits[0];
            String token=tokenStr.split("=")[1];
            System.out.println(string);
            return token;
        } catch (IOException e) {
            return null;
        }
    }
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try{
            Response response = client.newCall(request).execute();
            String str=response.body().string();
            GithubUser githubUser = JSON.parseObject(str, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            return null;
        }
    }
}
