package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class ProjectStage {
    /**
     * Наименование
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Stage stage;
    /**
     * Ожидаемая дата завершения
     */
    private String term;
}
