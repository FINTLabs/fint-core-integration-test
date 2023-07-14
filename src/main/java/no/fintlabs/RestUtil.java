package no.fintlabs;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RestUtil {

    private final WebClient webClient;

    public RestUtil(WebClient webclient) {
        this.webClient = webclient;
    }

    public <T> Mono<T> get(Class<T> clazz, String uri) {
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(clazz);

    }

}