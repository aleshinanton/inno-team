package ru.jat.innoteam;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Сервис генерации фэйковых данных для завок
 */
@Service
public class FakerService {

    private static final Faker FAKER = new Faker(new Locale("ru"));

    public String getTeamName() {
        return FAKER.team().name();
    }

    public String getPhone() {
        return FAKER.phoneNumber().cellPhone();
    }

}
