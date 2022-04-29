package com.bootcamp.personal.passive.savingaccount.controlller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("personal/passive/saving_account/test")
@Tag(name = "CI/CD API Test", description = "CI/CD Test with Github Actions")
@CrossOrigin( origins = {"*"})
@RequiredArgsConstructor
public class GenericController {

    @GetMapping//(value = "/test")
    public Mono<ResponseEntity<Flux<String>>> getAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(Flux.just("Hola, Generic Controller FROM PerPas saving-account v1.3. with Cors *"))
        );
    }

}