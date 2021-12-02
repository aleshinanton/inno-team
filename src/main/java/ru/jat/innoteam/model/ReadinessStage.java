package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReadinessStage {
    IDEA("Идея"),
    PROTOTYPE("Прототип"),
    PRODUCT("Продукт");

    @JsonValue
    private final String value;
}
