package com.felzan.southsystem.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum VoteEnum {
    YES("Sim"),
    NO("Não");

    @JsonValue
    @Getter private String option;
}
