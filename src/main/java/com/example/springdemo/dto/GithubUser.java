package com.example.springdemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubUser {
    private String name;
    private long id;
    private String bio;
}
