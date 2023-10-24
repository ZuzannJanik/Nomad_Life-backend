package com.crud.nomad.mapper;

import com.crud.nomad.domain.Answer;
import com.crud.nomad.domain.dto.AnswerDto;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AnswerMapperTest {
    private final AnswerMapper answerMapper = new AnswerMapper();

    @Test
    void mapToAnswer() {
        //Given
        AnswerDto answerDto = new AnswerDto(1L, "Test question", "Test snippet", "Test link");

        //When
        Answer answer = answerMapper.mapToAnswer(answerDto);

        //Then
        assertEquals(answerDto.getAnswerId(), answer.getAnswerId());
        assertEquals(answerDto.getQuestion(), answer.getQuestion());
        assertEquals(answerDto.getSnippet(), answer.getSnippet());
        assertEquals(answerDto.getLink(), answer.getLink());
    }

    @Test
    void mapToAnswerDto() {
        //Given
        Answer answer = new Answer(2L, "Another question", "Another snippet", "Another link");

        //When
        AnswerDto answerDto = answerMapper.mapToAnswerDto(answer);

        //Then
        assertEquals(answer.getAnswerId(), answerDto.getAnswerId());
        assertEquals(answer.getQuestion(), answerDto.getQuestion());
        assertEquals(answer.getSnippet(), answerDto.getSnippet());
        assertEquals(answer.getLink(), answerDto.getLink());
    }

    @Test
    void mapToAnswerDtoList() {
        //Given
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer(3L, "Third question", "Third snippet", "Third link"));
        answerList.add(new Answer(4L, "Fourth question", "Fourth snippet", "Fourth link"));

        List<AnswerDto> expectedAnswerDtoList = new ArrayList<>();
        expectedAnswerDtoList.add(new AnswerDto(3L, "Third question", "Third snippet", "Third link"));
        expectedAnswerDtoList.add(new AnswerDto(4L, "Fourth question", "Fourth snippet", "Fourth link"));

        //When
        List<AnswerDto> actualAnswerDtoList = answerMapper.mapToAnswerDtoList(answerList);

        //Then
        assertEquals(expectedAnswerDtoList, actualAnswerDtoList);
    }
}