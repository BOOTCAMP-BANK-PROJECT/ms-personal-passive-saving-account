package com.bootcamp.personal.passive.savingaccount;

import com.bootcamp.personal.passive.savingaccount.dto.CreateSavingAccountDto;
import com.bootcamp.personal.passive.savingaccount.dto.UpdateSavingAccountDto;
import com.bootcamp.personal.passive.savingaccount.entity.SavingAccount;
import com.bootcamp.personal.passive.savingaccount.util.handler.exceptions.BadRequestException;
import com.bootcamp.personal.passive.savingaccount.repository.SavingAccountRepository;
import com.bootcamp.personal.passive.savingaccount.util.Util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SavingAccountServiceImpl implements SavingAccountService {

    public final SavingAccountRepository repository;

    @Override
    public Flux<SavingAccount> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<SavingAccount> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<SavingAccount> save(CreateSavingAccountDto o) {

        if(!Util.isValidCurrency(o.getIsoCurrencyCode())){
            throw new BadRequestException(
                    "CURRENCY",
                    "An error occurred while trying to create an item.",
                    o.getIsoCurrencyCode() + " is an invalid currency code.",
                    SavingAccountServiceImpl.class,
                    "save"
            );
        }

        return repository.save(Util.mapCreate(o));
    }

    @Override
    public Mono<SavingAccount> update(UpdateSavingAccountDto o) {

        return repository.findById(o.getId())
                .switchIfEmpty(Mono.error(new Exception("An item with the id " + o.getId() + " was not found. >> switchIfEmpty")))
                .flatMap( p -> repository.save(Util.mapUpdate(p, o)) )
                .onErrorResume( e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        SavingAccountServiceImpl.class,
                        "update.onErrorResume"
                )));
    }

    @Override
    public Mono<SavingAccount> delete(String id) {

        return repository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("An item with the id " + id + " was not found. >> switchIfEmpty")))
                .flatMap( p -> repository.save(Util.mapDelete(p, id)) )
                .onErrorResume( e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        SavingAccountServiceImpl.class,
                        "update.onErrorResume"
                )));
    }
}