package com.bootcamp.personal.passive.savingaccount.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CreateSavingAccountDto {

    private String description;
    private String abbreviation;
    private String isoCurrencyCode;
    private BigDecimal interesRate;
    private String fk_insertionUser;
    private String insertionTerminal;

}