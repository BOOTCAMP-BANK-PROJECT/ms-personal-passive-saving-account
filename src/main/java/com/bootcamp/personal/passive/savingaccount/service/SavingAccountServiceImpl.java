package com.bootcamp.personal.passive.savingaccount.service;

import com.bootcamp.personal.passive.savingaccount.entity.SavingAccount;
import com.bootcamp.personal.passive.savingaccount.util.Constant;
import com.bootcamp.personal.passive.savingaccount.util.handler.exceptions.BadRequestException;

import com.bootcamp.personal.passive.savingaccount.repository.SavingAccountRepository;

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
    public Mono<SavingAccount> save(SavingAccount savingAccount) {
        return repository.findByIdClient(savingAccount.getIdClient())
                .map(sa -> {
                    throw new BadRequestException(
                            "ID",
                            "Client have one ore more accounts",
                            sa.getId(),
                            SavingAccountServiceImpl.class,
                            "save.onErrorResume"
                    );
                })
                .switchIfEmpty(repository.save(savingAccount))
                .onErrorResume(e -> Mono.error(e)).cast(SavingAccount.class);
    }

    @Override
    public Mono<SavingAccount> update(SavingAccount savingAccount) {

        return repository.findById(savingAccount.getId())
                .switchIfEmpty(Mono.error(new Exception("An item with the id " + savingAccount.getId() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(savingAccount))
                .onErrorResume(e -> Mono.error(new BadRequestException(
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
                .flatMap(p -> {
                    p.setRegistrationStatus(Constant.STATUS_INACTIVE);
                    return repository.save(p);
                })
                .onErrorResume(e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        SavingAccountServiceImpl.class,
                        "update.onErrorResume"
                )));
    }
}