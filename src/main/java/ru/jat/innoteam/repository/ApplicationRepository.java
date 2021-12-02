package ru.jat.innoteam.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ru.jat.innoteam.model.Application;

public interface ApplicationRepository extends ElasticsearchRepository<Application, Long> {
}
