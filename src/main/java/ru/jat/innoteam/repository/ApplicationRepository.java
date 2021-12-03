package ru.jat.innoteam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.jat.innoteam.model.Application;

public interface ApplicationRepository extends ElasticsearchRepository<Application, Long> {

    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"productUseCases\"], \"fuzziness\": \"AUTO\"}}")
    Page<Application> find(String productUseCases, Pageable pageable);
}
