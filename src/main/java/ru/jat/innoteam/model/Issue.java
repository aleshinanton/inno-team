package ru.jat.innoteam.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Паспорт запроса на инновацию «Решение проблемы»
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "issue")
@JsonAutoDetect
public class Issue {
    /**
     * Идентификатор запроса
     */
    @Id
    private UUID uuid;
    /**
     * Что болит?
     * Опишите своими словами существующую в организации проблему. Можно сформулировать проблему в форму задачи
     */
    private String issue;
    /**
     * Как проявляется ваша проблема?
     * Приведите описание реальной ситуации, в которой проблема бы проявилась
     */
    private String issueDescription;
    /**
     * Что будет если проблему не решать?
     * Опишите нежелательные эффекты, которые возникают или могут возникнуть из-за того, что проблема не решается
     */
    private String affect;
    /**
     * Почему так происходит?
     * Какие на ваш взгляд ключевые причины возникновения проблемы? Что на ваш взгляд является причиной возникновения проблемы?
     */
    private String cause;
    /**
     * У кого болит?
     * Кто является непосредственно ответственным за проблемный участок?
     */
    private String responsible;
    /**
     * Какой желательный срок решения проблемы?
     */
    private String resolveTerm;
    /**
     * Пробовали решать?
     * Как пытались решить проблему ранее? Почему эти попытки оказались неудачными или почему были признаны неудачными? Чем не устроили найденные решения?
     * Общались ли с рынком? Если да, то с кем?
     */
    private String tryToResolve;
    /**
     * Как с вами связаться?
     * Укажите наименование вашего предприятия, ваши ФИО, и телефон для связи
     */
    private String contact;
    /**
     * Время создание запроса на решения проблемы
     */
    @JsonIgnore
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime createdAt;
    /**
     * Время обновления запроса на решения проблемы
     */
    @JsonIgnore
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime updatedAt;

    /**
     * Ключевые метки заявки
     */
    private List<String> tags;
}
