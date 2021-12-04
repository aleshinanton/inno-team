package ru.jat.innoteam.service;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.jat.innoteam.model.*;
import ru.jat.innoteam.repository.ApplicationRepository;
import ru.jat.innoteam.repository.IssueRepository;
import ru.jat.innoteam.util.RandomUtil;

import java.util.*;
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
    private final RestTemplate restTemplate;

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
        applicationRepository.deleteAll();
        //Если заявок нет, то наполним
        if (applicationRepository.count() == 0) {
            log.info("Наполним данными эластик");
            var applications = IntStream.range(1, 101).mapToObj(i -> getApplication((long) i)).collect(Collectors.toList());
            applicationRepository.saveAll(applications);
            log.info("В эластик добавлено {} записей заявок", applicationRepository.count());
        }
        issueRepository.deleteAll();
        //Если проблем нет, то наполним
        if (issueRepository.count() == 0) {
            log.info("Добавим проблемы");
            var issues = IntStream.range(1, 101).mapToObj(i -> getIssue()).collect(Collectors.toList());
            issueRepository.saveAll(issues);
            log.info("В эластик добавлено {} проблем", issueRepository.count());
        }
    }

    /**
     * Метод получения разметки текста тегами
     * из интеллектуального модуля
     *
     * @param string текст для разметки
     * @return облако тегов
     */
    public List<String> getTags(String string) {
        var tags = restTemplate.postForObject("/predict/gettags", Map.of("text", string), String.class);
        List<String> tagList = Collections.emptyList();
        if (tags != null) {
            tagList = Arrays.asList(tags.split(" "));
        }
        return tagList;
    }

    public Issue getIssue() {
        var issueDescription = RandomUtil.getPhrase();
        var tags = getTags(issueDescription);
        return Issue.builder()
                .issue("Пробки на дорогах")
                .issueDescription(issueDescription)
                .affect("Экономика теряет 1% ВВП ежегодно")
                .cause("Плохо отлажено переключение дорожных контроллеров")
                .responsible(FAKER.name().fullName())
                .resolveTerm("Решить до конца 2022 года")
                .tryToResolve("Да. Пробовали изменять скорость потока.")
                .contact(FAKER.phoneNumber().cellPhone() + " " + FAKER.name().fullName())
                .tags(tags)
                .build();
    }

    /**
     * Метод для генерации фейковой заявки
     *
     * @param id идентификатор заявки
     * @return
     */
    public Application getApplication(Long id) {
        var productDescription = RandomUtil.getPhrase();
        var tags = getTags(productDescription);
        return Application.builder()
                .id(id)
                .teamName(FAKER.team().name())
                .stage(ReadinessStage.values()[RANDOM.nextInt(ReadinessStage.values().length)])
                .productDescription(productDescription)
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
                        .description(productDescription)
                        .term("Срок " + FAKER.date().birthday().toString())
                        .context("Городская среда в пробках")
                        .expectedProjectEffect(ExpectedProjectEffect.builder()
                                .id(FAKER.number().digits(2))
                                .name("Уменьшение расходов")
                                .indicator(FAKER.number().digits(2))
                                .build())
                        .expectedProjectEffect(ExpectedProjectEffect.builder()
                                .id(FAKER.number().digits(2))
                                .name("Увеличение прибыли")
                                .indicator(FAKER.number().digits(2))
                                .build())
                        .projectStage(ProjectStage.builder()
                                .stage(Stage.POSITION)
                                .term(FAKER.date().birthday().toString())
                                .build())
                        .projectStage(ProjectStage.builder()
                                .stage(Stage.DETAIL)
                                .term(FAKER.date().birthday().toString())
                                .build())
                        .projectStage(ProjectStage.builder()
                                .stage(Stage.PRE_PILOT)
                                .term(FAKER.date().birthday().toString())
                                .build())
                        .projectStage(ProjectStage.builder()
                                .stage(Stage.PILOT)
                                .term(FAKER.date().birthday().toString())
                                .build())
                        .projectStage(ProjectStage.builder()
                                .stage(Stage.PILOT_REPORT)
                                .term(FAKER.date().birthday().toString())
                                .build())
                        .projectMember(Member.builder()
                                .id(FAKER.number().digits(2))
                                .fullName(FAKER.name().fullName())
                                .position(FAKER.company().profession())
                                .organization(FAKER.company().name())
                                .contact(FAKER.phoneNumber().cellPhone())
                                .build())
                        .projectMember(Member.builder()
                                .id(FAKER.number().digits(2))
                                .fullName(FAKER.name().fullName())
                                .position(FAKER.company().profession())
                                .organization(FAKER.company().name())
                                .contact(FAKER.phoneNumber().cellPhone())
                                .build())
                        .projectMember(Member.builder()
                                .id(FAKER.number().digits(2))
                                .fullName(FAKER.name().fullName())
                                .position(FAKER.company().profession())
                                .organization(FAKER.company().name())
                                .contact(FAKER.phoneNumber().cellPhone())
                                .build())
                        .projectMember(Member.builder()
                                .id(FAKER.number().digits(2))
                                .fullName(FAKER.name().fullName())
                                .position(FAKER.company().profession())
                                .organization(FAKER.company().name())
                                .contact(FAKER.phoneNumber().cellPhone())
                                .build())
                        .expenseItem(ExpenseItem.builder()
                                .id(FAKER.number().digits(2))
                                .name("Техническое оборудование")
                                .indicator(FAKER.number().digits(7))
                                .build())
                        .expenseItem(ExpenseItem.builder()
                                .id(FAKER.number().digits(2))
                                .name("Аренда помещения")
                                .indicator(FAKER.number().digits(7))
                                .build())
                        .projectStatus(ProjectStatus.builder()
                                .status(RandomUtil.getStatus())
                                .expectedResult("Успех")
                                .expectedResult("Только Успех")
                                .term(FAKER.date().birthday().toString())
                                .build())
                        .projectStatus(ProjectStatus.builder()
                                .status(RandomUtil.getStatus())
                                .expectedResult("Успех")
                                .expectedResult("Не только успех")
                                .term(FAKER.date().birthday().toString())
                                .build())
                        .projectMeeting(ProjectMeeting.builder()
                                .comment(FAKER.chuckNorris().fact())
                                .date(FAKER.date().birthday().toString())
                                .build())
                        .projectMeeting(ProjectMeeting.builder()
                                .comment(FAKER.chuckNorris().fact())
                                .date(FAKER.date().birthday().toString())
                                .build())
                        .projectMeeting(ProjectMeeting.builder()
                                .comment(FAKER.chuckNorris().fact())
                                .date(FAKER.date().birthday().toString())
                                .build())
                        .projectMaterial(ProjectMaterial.builder()
                                .name(FAKER.harryPotter().book())
                                .url(FAKER.internet().url())
                                .build())
                        .projectMaterial(ProjectMaterial.builder()
                                .name(FAKER.harryPotter().book())
                                .url(FAKER.internet().url())
                                .build())
                        .build())
                .tags(tags)
                .build();
    }

}
