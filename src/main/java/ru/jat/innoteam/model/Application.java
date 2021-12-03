package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Форма заявки для стартапа
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "application")
@JsonAutoDetect
public class Application {
    /**
     * Идентификатор заявки
     */
    private long id;
    /**
     * Наименование команды/организации
     */
    private String teamName;
    /**
     * Стадия готовности продукта * (Варианты ответа: Идея, Прототип, продукт)
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private ReadinessStage stage;
    /**
     * Краткое описание продукта
     */
    private String productDescription;
    /**
     * Кейсы использования продукта
     */
    @Field(type = FieldType.Text, fielddata = true)
    private String productUseCases;
    /**
     * Польза продукта
     */
    private String productBenefits;
    /**
     * Организация Московского транспорта, интересная в первую очередь *
     * (Варианты ответа: Московский метрополитен, мосгорстранс, ЦОДД, Организатор перевозок, Мостранспроект, АМПП)
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private Organization organization;
    /**
     * Запрос к акселератору и видение пилотного проекта
     */
    private String acceleratorRequest;
    /**
     * Требуется ли сертификация продукта *
     * (Варианты ответа: “да, требуется сертификация и у нас она есть”, “да, требуется сертификация, но  у нас ее нет”,
     * “нет, не требуется”)
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private CertificationRequirementStatus certificationRequirementStatus;
    /**
     * ФИО контактного лица по заявке
     */
    private String contactPersonFullName;
    /**
     * Должность контактного лица
     */
    private String contantPersonPosition;
    /**
     * Контактный телефон
     */
    private String contactPhone;
    /**
     * Контактная почта
     */
    private String contactMail;
    /**
     * Наименование юридического лица
     */
    private String legalEntityName;
    /**
     * ИНН юридического лица
     */
    private String legalEntityInn;
    /**
     * Сколько человек в организации * (Варианты ответа: Менее 20, от 20 до 100, от 100 до 500, более 500)
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private PeopleAmount peopleAmount;
    /**
     * Сайт
     */
    private String site;
    /**
     * Откуда узнали про акселератор
     */
    private String sourceOfInformation;
    /**
     * Ссылка на презентацию
     */
    private String presentationUrl;

    /**
     * Паспорт проекта
     */
    private ProjectPassport projectPassport;
}
