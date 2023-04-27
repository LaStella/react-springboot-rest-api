package com.cnu.coffee.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // 데이터가 null이 아닌 것만 전달합니다.
public class ResponseUserDto {
    private String email;
    private String name;
    private String userId;
    private List<ResponseReviewDto> bookmarks;
}