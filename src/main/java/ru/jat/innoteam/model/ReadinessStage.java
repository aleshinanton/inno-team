package ru.jat.innoteam.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReadinessStage {
    IDEA("Идея"),
    PROTOTYPE("Прототип"),
    PRODUCT("Продукт");

    private final String value;
}
