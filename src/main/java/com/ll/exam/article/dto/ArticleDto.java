package com.ll.exam.article.dto;

import lombok.*;

//@Getter
//@Setter
//@ToString
@Data
@AllArgsConstructor

public class ArticleDto {
    private long id;
    private String title;
    private String body;
}
