package com.bootcamp.personal.passive.savingaccount.controlller;

import com.bootcamp.personal.passive.savingaccount.entity.SavingAccount;
import com.bootcamp.personal.passive.savingaccount.service.SavingAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("personal/passive/saving_account")
@Tag(name = "Personal Passive Product Saving Account Type", description = "Manage Personal Passive Product saving accounts type")
@CrossOrigin( value = { "*" })
@RequiredArgsConstructor
public class SavingAccountController {

    public final SavingAccountService service;

    @GetMapping//(value = "/fully")
    public Mono<ResponseEntity<Flux<SavingAccount>>> getAll() {
        return Mono.just(
                        ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.getAll())
        );
    }

    @PostMapping
    public Mono<ResponseEntity<SavingAccount>> create(@RequestBody SavingAccount savingAccount) {

        return service.save(savingAccount).map( p -> ResponseEntity
                .created(URI.create("/SavingAccount/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }

    @PutMapping
    public Mono<ResponseEntity<SavingAccount>> update(@RequestBody SavingAccount savingAccount) {
        return service.update(savingAccount)
                .map(p -> ResponseEntity.created(URI.create("/SavingAccount/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<ResponseEntity<SavingAccount>> delete(@RequestBody String id) {
        return service.delete(id)
                .map(p -> ResponseEntity.created(URI.create("/SavingAccount/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
