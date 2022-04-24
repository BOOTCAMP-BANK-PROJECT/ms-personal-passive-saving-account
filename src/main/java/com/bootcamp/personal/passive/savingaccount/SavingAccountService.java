package com.bootcamp.personal.passive.savingaccount;

import com.bootcamp.personal.passive.savingaccount.dto.CreateSavingAccountDto;
import com.bootcamp.personal.passive.savingaccount.dto.UpdateSavingAccountDto;
import com.bootcamp.personal.passive.savingaccount.entity.SavingAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface SavingAccountService {

    public Flux<SavingAccount> getAll();

    public Mono<SavingAccount> getById(String id);

    public Mono<SavingAccount> save(CreateSavingAccountDto o);

    public Mono<SavingAccount> update(UpdateSavingAccountDto o);

    public Mono<SavingAccount> delete(String id);
}