package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Organization {
    MOSCOW_SUBWAY("Московский метрополитен"),
    MOSGORTRANS("Мосгорстранс"),
    CODD("ЦОДД"),
    TRANSPORTATION_ORGANIZER("Организатор перевозок"),
    MOSTRANSPROEKT("Мостранспроект"),
    AMPP("АМПП");

    @JsonValue
    private final String value;
}
