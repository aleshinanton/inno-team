package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

/**
 * Статья расходов
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class ExpenseItem {
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
