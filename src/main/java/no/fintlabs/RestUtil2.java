package no.fintlabs;

import no.fintlabs.model.Informasjon;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Service
public class RestUtil2 {

    private final WebClient webClient;

    public RestUtil2(WebClient webclient) {
        this.webClient = webclient;
    }

    public Mono<HashMap<String, HashMap<String, Informasjon>>> get(String uri) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<HashMap<String, HashMap<String, Informasjon>>>() {
                });
    }
}