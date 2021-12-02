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
import ru.jat.innoteam.model.*;
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

    /**
     * Метод вызывается при старте приложения
     * и заполняет данными эластик, если эластик пустой
     */
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

    /**
     * Метод для генерации фейковой заявки
     * @param id идентификатор заявки
     * @return
     */
    public Application getApplication(Long id) {
        return Application.builder()
                .id(id)
                .teamName(FAKER.team().name())
                .stage(ReadinessStage.values()[RANDOM.nextInt(ReadinessStage.values().length)])
                .productDescription(FAKER.commerce().productName())
                .productUseCases(FAKER.commerce().material())
                .productBenefits(FAKER.commerce().price())
                .organization(Organization.values()[RANDOM.nextInt(ReadinessStage.values().length)])
                .acceleratorRequest("Запрос к акселератору и видение пилотного проекта " + FAKER.commerce().department())
                .certificationRequirementStatus(CertificationRequirementStatus.values()[RANDOM.nextInt(CertificationRequirementStatus.values().length)])
                .contactPersonFullName(FAKER.name().fullName())
                .contantPersonPosition(FAKER.commerce().department())
                .contactPhone(FAKER.phoneNumber().cellPhone())
                .contactMail(FAKER.bothify("????###???@mail.ru"))
                .legalEntityInn(FAKER.number().digits(12))
                .legalEntityName(FAKER.company().name())
                .peopleAmount(PeopleAmount.values()[RANDOM.nextInt(CertificationRequirementStatus.values().length)])
                .site("www." + FAKER.app().name().toLowerCase() + ".ru")
                .sourceOfInformation("СМИ")
                .presentationUrl("http://link/company_project.ptpp")
                .build();
    }

}
