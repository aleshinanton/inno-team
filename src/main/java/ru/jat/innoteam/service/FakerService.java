package ru.jat.innoteam.service;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.jat.innoteam.model.*;
import ru.jat.innoteam.repository.ApplicationRepository;
import ru.jat.innoteam.repository.IssueRepository;

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
    private final IssueRepository issueRepository;

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
        //Если заявок нет, то наполним
        if (applicationRepository.count() == 0) {
            log.info("Наполним данными эластик");
            var applications = IntStream.range(1, 101).mapToObj(i -> getApplication((long) i)).collect(Collectors.toList());
            applicationRepository.saveAll(applications);
            log.info("В эластик добавлено {} записей заявок", applicationRepository.count());
        }
        //Если проблем нет, то наполним
        if (issueRepository.count() == 0) {
            issueRepository.save(getIssue());
            log.info("В эластик добавлена запись проблемы");
        }
    }

    public Issue getIssue() {
        return Issue.builder()
                .issue("Пробки на дорогах")
                .issueDescription("Ежедневно Москва тратить в пробках N часов.")
                .affect("Экономика теряет 1% ВВП ежегодно")
                .cause("Плохо отлажено переключение дорожных контроллеров")
                .initiator(FAKER.name().fullName())
                .responsible(FAKER.name().fullName())
                .resolveTerm("Решить до конца 2022 года")
                .contact(FAKER.phoneNumber().cellPhone() + " " + FAKER.name().fullName())
                .build();
    }

    /**
     * Метод для генерации фейковой заявки
     *
     * @param id идентификатор заявки
     * @return
     */
    public Application getApplication(Long id) {
        var productName = FAKER.commerce().productName();
        return Application.builder()
                .id(id)
                .teamName(FAKER.team().name())
                .stage(ReadinessStage.values()[RANDOM.nextInt(ReadinessStage.values().length)])
                .productDescription(productName)
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
                .project(Project.builder()
                        .name(FAKER.commerce().productName())
                        .orgName(Organization.values()[RANDOM.nextInt(ReadinessStage.values().length)].getValue())
                        .participant("Да")
                        .projectManager(FAKER.name().fullName())
                        .programCoordinator(FAKER.name().fullName())
                        .orgCoordinator(FAKER.name().fullName())
                        .description(productName)
                        .term("Срок " + FAKER.date().birthday().toString())
                        .context("Городская среда в пробках")
                        .profit("Сокращаем пробки")
                        .projectStage(ProjectStage.values()[RANDOM.nextInt(ProjectStage.values().length)])
                        .build())
                .build();
    }

}
