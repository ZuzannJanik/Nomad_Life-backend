package com.crud.nomad.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "answers")
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_id")
    private Long answerId;

    @NotNull
    @Column(name = "question")
    private String question;

    @Column(name = "snippet")
    private String snippet;

    @Column(name = "link")
    private String link;
}
