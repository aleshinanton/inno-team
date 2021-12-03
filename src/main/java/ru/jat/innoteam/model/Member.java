package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

/**
 * Член команды
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class Member {
    /**
     * №
     */
    private String id;
    /**
     * ФИО
     */
    private String fullName;
    /**
     * Должность
     */
    private String position;
    /**
     * Организация
     */
    private String organization;
    /**
     * Контакты
     */
    private String contact;
}
