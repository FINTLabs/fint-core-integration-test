package no.fintlabs;


import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.utdanning.elev.ElevResources;
import no.fintlabs.model.HateosResponse;
import no.fintlabs.model.Informasjon;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class UdanningFetcher {

    private final RestUtil restUtil;

    public UdanningFetcher(RestUtil restUtil) {
        this.restUtil = restUtil;
    }

    @Scheduled(cron = "0 */5 7-16 * * *")
    private void test3() {
        restUtil
                .get(ElevResources.class, "/utdanning/elev/elev")
                .doOnNext(resources -> {
                    log.info("Mottok: " + resources.getTotalItems());
                    resources
                            .getContent()
                            .forEach(resource -> log.info(resource.toString()));
                })
                .doOnError(error -> log.error("Noe gikk feil"))
                .subscribe();
    }
}