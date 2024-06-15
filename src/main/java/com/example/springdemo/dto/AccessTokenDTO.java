package com.example.springdemo.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_url;
    private String state;

}
