package com.bootcamp.personal.passive.savingaccount.util;

import com.bootcamp.personal.passive.savingaccount.dto.CreateSavingAccountDto;
import com.bootcamp.personal.passive.savingaccount.dto.UpdateSavingAccountDto;
import com.bootcamp.personal.passive.savingaccount.entity.SavingAccount;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;
import java.util.Optional;

public class Util {

    public static boolean isValidCurrency(String code){
        try {
            Currency c = Currency.getInstance(code);
            if (c != null) return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static SavingAccount mapCreate(CreateSavingAccountDto o) {

        SavingAccount p = new SavingAccount(
                (String) null,
                o.getDescription(),
                o.getAbbreviation(),
                o.getIsoCurrencyCode(),
                new BigDecimal(0),
                (short) 1,
                new Date(),
                o.getFk_insertionUser(),
                o.getInsertionTerminal()
        );

        return p;
    }

    public static SavingAccount mapUpdate(Optional<SavingAccount> p, UpdateSavingAccountDto o) {
        SavingAccount updated_p = new SavingAccount(
                o.getId(),
                o.getDescription(),
                o.getAbbreviation(),
                p.get().getIsoCurrencyCode(),
                o.getInteresRate(),
                p.get().getRegistrationStatus(),
                p.get().getInsertionDate(),
                p.get().getFk_insertionUser(),
                p.get().getInsertionTerminal()
        );

        return updated_p;
    }

    public static SavingAccount mapDelete(Optional<SavingAccount> p, String id) {
        SavingAccount deleted_p = new SavingAccount(
                id,
                p.get().getDescription(),
                p.get().getAbbreviation(),
                p.get().getIsoCurrencyCode(),
                p.get().getInteresRate(),
                (short) 0,
                p.get().getInsertionDate(),
                p.get().getFk_insertionUser(),
                p.get().getInsertionTerminal()
        );

        return deleted_p;
    }
}
