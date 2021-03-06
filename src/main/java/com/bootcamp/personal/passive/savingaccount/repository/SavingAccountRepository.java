package com.bootcamp.personal.passive.savingaccount.repository;

import com.bootcamp.personal.passive.savingaccount.entity.SavingAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SavingAccountRepository extends ReactiveMongoRepository<SavingAccount, String> {

    Mono<SavingAccount> findByIdClient(String idClient);
}