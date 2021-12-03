package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

/**
 * Паспорт проекта
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect
public class Project {
    /**
     * Наименование проекта
     */
    private String name;
    /**
     * Организация транспортного комплекса Москвы
     */
    private String orgName;
    /**
     * Участник программы пилотирования «Транспортные инновации Москвы»
     */
    private String participant;
    /**
     * Руководитель проекта
     */
    private String projectManager;
    /**
     * Координатор от участника программы пилотирования «Транспортные инновации Москвы»
     */
    private String programCoordinator;
    /**
     * Координатор от организации транспортного комплекса Москвы
     */
    private String orgCoordinator;
    /**
     * Краткое описание продукта
     */
    private String description;
    /**
     * Сроки реализации проекта
     */
    private String term;
    /**
     * КОНТЕКСТ И ПОТРЕБНОСТИ
     */
    private String context;
    /**
     * ОЖИДАЕМЫЕ ЭФФЕКТЫ ПРОЕКТА
     */
    private String profit;
    /**
     * ЭТАПЫ ПРОЕКТА
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private ProjectStage stage;
}
