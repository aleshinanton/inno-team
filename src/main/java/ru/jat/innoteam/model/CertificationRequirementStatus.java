package ru.jat.innoteam.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CertificationRequirementStatus {
    YES_EXISTS("да, требуется сертификация и у нас она есть"),
    YES_NOT_EXISTS("да, требуется сертификация, но  у нас ее нет"),
    NO("нет, не требуется");

    private final String value;
}
