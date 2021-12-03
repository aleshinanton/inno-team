package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

/**
 * Материал проекта
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class ProjectMaterial {
    /**
     * Наименование
     */
    private String name;
    /**
     * Ссылка
     */
    private String url;
}
