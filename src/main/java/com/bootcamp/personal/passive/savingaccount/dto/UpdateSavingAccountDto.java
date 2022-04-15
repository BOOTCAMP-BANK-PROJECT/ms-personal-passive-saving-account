package com.bootcamp.personal.passive.savingaccount.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
public class UpdateSavingAccountDto {

    @Id
    private String id;
    private String description;
    private String abbreviation;
    //private String isoCurrencyCode;
    private BigDecimal interesRate;
    //private short registrationStatus;
    //private Date insertionDate = new Date();
    //private String fk_insertionUser;
    //private String insertionTerminal;

}