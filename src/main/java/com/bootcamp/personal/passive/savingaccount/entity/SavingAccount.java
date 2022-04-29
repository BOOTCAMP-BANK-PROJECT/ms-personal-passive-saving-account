package com.bootcamp.personal.passive.savingaccount.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class SavingAccount {

    @Id
    private String id;
    private String idClient;
    private String description;
    private String abbreviation;
    private String isoCurrencyCode;
    private BigDecimal interesRate;
    private short registrationStatus;
    private Date insertionDate;
    private String fk_insertionUser;
    private String insertionTerminal;

}