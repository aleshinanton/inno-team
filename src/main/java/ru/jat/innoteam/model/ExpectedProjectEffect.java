package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

/**
 * Ожидаемый эффект
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class ExpectedProjectEffect {
    /**
     * №
     */
    private String id;
    /**
     * Наименование
     */
    private String name;
    /**
     * Значение показателя
     */
    private String indicator;
}
