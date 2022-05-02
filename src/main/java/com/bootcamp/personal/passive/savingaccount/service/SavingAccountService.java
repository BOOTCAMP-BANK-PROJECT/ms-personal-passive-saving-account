package com.bootcamp.personal.passive.savingaccount.service;


import com.bootcamp.personal.passive.savingaccount.entity.SavingAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface SavingAccountService {

    public Flux<SavingAccount> getAll();

    public Mono<SavingAccount> getById(String id);

    public Mono<SavingAccount> save(SavingAccount savingAccount);

    public Mono<SavingAccount> update(SavingAccount savingAccount);

    public Mono<SavingAccount> delete(String id);
}