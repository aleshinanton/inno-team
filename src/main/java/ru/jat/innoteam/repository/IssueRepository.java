package ru.jat.innoteam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.jat.innoteam.model.Application;
import ru.jat.innoteam.model.Issue;

import java.util.UUID;

/**
 * Репозиторий запросов на решение проблем
 */
public interface IssueRepository extends ElasticsearchRepository<Issue, UUID> {

    /**
     * Нечеткий поиск
     *
     * @param issue    строка поиска
     * @param pageable постраничный фильтр
     * @return
     */
    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"issue\"], \"fuzziness\": \"AUTO\"}}")
    Page<Application> find(String issue, Pageable pageable);

    @Query("{\n" +
            "    \"multi_match\": {\n" +
            "      \"query\": \"?0\",\n" +
            "      \"minimum_should_match\": \"35%\",\n" +
            "      \"type\": \"cross_fields\",\n" +
            "      \"fields\": [\n" +
            "        \"issue\",\n" +
            "        \"issueDescription\",\n" +
            "        \"affect\",\n" +
            "        \"cause\",\n" +
            "        \"responsible\",\n" +
            "        \"tryToResolve\"\n" +
            "      ]\n" +
            "    }\n" +
            "  }")
    Page<Application> multiFieldFullTextSearch(String searchString, Pageable pageable);
}
