package com.crud.nomad.mapper;

import com.crud.nomad.domain.Answer;
import com.crud.nomad.domain.dto.AnswerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerMapper {
    public Answer mapToAnswer(final AnswerDto answerDto) {
        return new Answer(
                answerDto.getAnswerId(),
                answerDto.getQuestion(),
                answerDto.getSnippet(),
                answerDto.getLink()
        );
    }

    public AnswerDto mapToAnswerDto(final Answer answer) {
        return new AnswerDto(
                answer.getAnswerId(),
                answer.getQuestion(),
                answer.getSnippet(),
                answer.getLink()
        );
    }

    public List<AnswerDto> mapToAnswerDtoList(final List<Answer> answerList) {
        return answerList.stream()
                .map(this::mapToAnswerDto)
                .collect(Collectors.toList());
    }
}
