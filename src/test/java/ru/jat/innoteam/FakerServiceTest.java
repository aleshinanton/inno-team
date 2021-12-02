package ru.jat.innoteam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FakerServiceTest {

    @Autowired
    FakerService fakerService;

    @Test
    void getTeamName() {
        assertTrue(fakerService.getTeamName().length() > 0);
    }
}