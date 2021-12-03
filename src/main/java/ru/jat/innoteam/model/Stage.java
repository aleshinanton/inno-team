package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Stage {

    POSITION("Позиционирование"),
    DETAIL("Уточнение деталей продуктового предложения"),
    PRE_PILOT("Подготовка к пилотному тестированию"),
    PILOT("Проведение пилотного тестирования"),
    PILOT_REPORT("Формирование отчета о пилотном тестировании");

    @JsonValue
    private final String value;
}
