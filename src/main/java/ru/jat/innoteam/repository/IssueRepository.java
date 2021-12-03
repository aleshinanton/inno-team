package ru.jat.innoteam.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.jat.innoteam.model.Issue;

import java.util.UUID;

/**
 * Репозиторий запросов на решение проблем
 */
public interface IssueRepository extends ElasticsearchRepository<Issue, UUID> {
}
