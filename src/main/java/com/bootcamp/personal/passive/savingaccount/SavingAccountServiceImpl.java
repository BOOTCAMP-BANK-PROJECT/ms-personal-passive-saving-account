package com.bootcamp.personal.passive.savingaccount;

import com.bootcamp.personal.passive.savingaccount.dto.CreateSavingAccountDto;
import com.bootcamp.personal.passive.savingaccount.dto.UpdateSavingAccountDto;
import com.bootcamp.personal.passive.savingaccount.entity.SavingAccount;
import com.bootcamp.personal.passive.savingaccount.repository.SavingAccountRepository;
import com.bootcamp.personal.passive.savingaccount.util.Util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SavingAccountServiceImpl implements ISavingAccountService {

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

        if(Util.isValidCurrency(o.getIsoCurrencyCode())){
            new Exception(o.getIsoCurrencyCode() + " is an invalid currency code.");
        }

        return repository.save(Util.mapCreate(o));
    }

    @Override
    public Mono<SavingAccount> update(UpdateSavingAccountDto o) {

        Mono<SavingAccount> p = repository.findById(o.getId());

        if (p.blockOptional().isEmpty()) {
            throw new Error("An error occurred while trying to update the item with id " + o.getId() + ". An item with the id " + o.getId() + " was not found.");
        }

        return repository.save(Util.mapUpdate(p.blockOptional(), o));
    }

    @Override
    public Mono<SavingAccount> delete(String id) {

        Mono<SavingAccount> p = repository.findById(id);

        if (p.blockOptional().isEmpty()) {
            throw new Error("An error occurred while trying to delete the item with id " + id + ". An item with the id " + id + " was not found.");
        }

        return repository.save(Util.mapDelete(p.blockOptional(), id));
    }
}