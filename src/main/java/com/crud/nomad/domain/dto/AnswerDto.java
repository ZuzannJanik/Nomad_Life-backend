package com.crud.nomad.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerDto {
    private Long answerId;
    private String question;
    private String snippet;
    private String link;
}
