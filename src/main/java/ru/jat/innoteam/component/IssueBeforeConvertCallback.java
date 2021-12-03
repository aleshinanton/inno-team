package ru.jat.innoteam.component;

import org.springframework.data.elasticsearch.core.event.BeforeConvertCallback;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.stereotype.Component;
import ru.jat.innoteam.model.Issue;

import java.util.UUID;

/**
 * Компонент для заполнения служебных полей (uuid, createdAt, updatedAt) в issue при сохранении
 */
@Component
public class IssueBeforeConvertCallback implements BeforeConvertCallback<Issue> {

    @Override
    public Issue onBeforeConvert(Issue issue, IndexCoordinates index) {
        if (issue.getUuid() == null) {
            issue.setUuid(UUID.randomUUID());
        }
        return issue;
    }
}
