package ru.jat.innoteam.service;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import ru.jat.innoteam.model.Application;
import ru.jat.innoteam.model.ReadinessStage;
import ru.jat.innoteam.repository.ApplicationRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Сервис генерации фэйковых данных для завок
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FakerService {

    private final ApplicationRepository applicationRepository;

    private static final Faker FAKER = new Faker(new Locale("ru"));
    private static final Random RANDOM = new Random();

    public String getTeamName() {
        return FAKER.team().name();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void generateFakeData() {
        log.info("count = " + applicationRepository.count());
        if (applicationRepository.count() == 0) {
            log.info("Наполним данными эластик");
            var applications = IntStream.range(1, 100).mapToObj(i -> getApplication((long) i)).collect(Collectors.toList());
            applicationRepository.saveAll(applications);
            log.info("В эластик добавлено {} записей", applicationRepository.count());
        }
    }

    public Application getApplication(Long id) {
        return Application.builder()
                .id(id)
                .teamName(FAKER.team().name())
                .productDescription(FAKER.commerce().productName())
                .productBenefits(FAKER.commerce().price())
                .contactPhone(FAKER.phoneNumber().cellPhone())
                .contactMail(FAKER.bothify("????###???@mail.ru"))
                .stage(getReadinessStage())
                .legalEntityInn(FAKER.number().digits(12))
                .legalEntityName("ООО " + FAKER.team().name())
                .build();

    }

    public ReadinessStage getReadinessStage() {
        return ReadinessStage.values()[RANDOM.nextInt(ReadinessStage.values().length)];
    }

}
