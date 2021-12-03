package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import java.util.List;

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
    @Singular
    private List<ExpectedProjectEffect> expectedProjectEffects;
    /**
     * ЭТАПЫ ПРОЕКТА
     */
    @Singular
    private List<ProjectStage> projectStages;
    /**
     * Команда проекта
     */
    @Singular
    private List<Member> projectMembers;
    /**
     * Бюджет проекта
     */
    @Singular
    private List<ExpenseItem> expenseItems;
    /**
     * Статусы проекта
     */
    @Singular
    private List<ProjectStatus> projectStatuses;
    /**
     * Собрания по проекту
     */
    @Singular
    private List<ProjectMeeting> projectMeetings;
    /**
     * Материалы проекта
     */
    @Singular
    private List<ProjectMaterial> projectMaterials;
}
