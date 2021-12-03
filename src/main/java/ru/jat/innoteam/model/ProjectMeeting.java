package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

/**
 * Собрание по проекту
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class ProjectMeeting {
    /**
     * Комментарий
     */
    private String comment;
    /**
     * Дата
     */
    private String date;
}
