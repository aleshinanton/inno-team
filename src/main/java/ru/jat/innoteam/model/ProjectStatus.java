package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.util.List;

/**
 * Статус проекта
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class ProjectStatus {
    /**
     * Статус
     */
    private String status;
    /**
     * Ожидаемые результаты и мероприятия
     */
    @Singular
    private List<String> expectedResults;
    /**
     * Дата
     */
    private String term;
}
