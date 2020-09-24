package com.exercise.pojo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryVo {
    private Integer page = 1;
    private Integer pageSize = 20;
    private Boolean isGoBackup = true;
    private String cKey = "app-clothing-v1";
    private String fcid = "20000484";
    private String sort = "pop";
}
