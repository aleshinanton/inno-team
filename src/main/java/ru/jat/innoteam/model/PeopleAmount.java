package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PeopleAmount {
    LESS_THAN_20("Менее 20"),
    FROM_10_TO_100("от 20 до 100"),
    FROM_100_TO_500("от 100 до 500"),
    MORE_THAN_500("более 500");

    @JsonValue
    private final String value;
}
