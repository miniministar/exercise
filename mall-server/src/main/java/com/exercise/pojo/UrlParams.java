package com.exercise.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UrlParams {
    private String url;
    private String cookie;
    private String referer;
}
