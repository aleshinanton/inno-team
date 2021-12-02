package ru.jat.innoteam;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

/**
 * Сервис генерации фэйковых данных для завок
 */
@Service
public class FakerService {

    private static final Faker FAKER = new Faker();

    public String getTeamName() {
        return FAKER.team().name();
    }

    public String getPhone() {
        return FAKER.phoneNumber().cellPhone();
    }

}
