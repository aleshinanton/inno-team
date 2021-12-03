package ru.jat.innoteam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.jat.innoteam.model.Application;

/**
 * Репозиторий заявок
 */
public interface ApplicationRepository extends ElasticsearchRepository<Application, Long> {

    /**
     * Нечеткий поиск
     *
     * @param productUseCases строка поиска
     * @param pageable        постраничный фильтр
     * @return
     */
    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"productUseCases\"], \"fuzziness\": \"AUTO\"}}")
    Page<Application> find(String productUseCases, Pageable pageable);

    /**
     * Полнотекстовый поиск по всем полям
     *
     * @param searchString строка поиска
     * @param pageable     постраничный фильтр
     * @return
     */
    @Query("{\n" +
            "    \"multi_match\": {\n" +
            "      \"query\": \"?0\",\n" +
            "      \"minimum_should_match\": \"35%\",\n" +
            "      \"type\": \"cross_fields\",\n" +
            "      \"fields\": [\n" +
            "        \"productDescription\",\n" +
            "        \"productUseCases\",\n" +
            "        \"acceleratorRequest\",\n" +
            "        \"productBenefits\",\n" +
            "        \"acceleratorRequest\"\n" +
            "      ]\n" +
            "    }\n" +
            "  }")
    Page<Application> multiFieldFullTextSearch(String searchString, Pageable pageable);
}
