package no.fintlabs;

import lombok.extern.slf4j.Slf4j;
import no.fintlabs.model.Informasjon;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class Integrasjonstest {
    private final RestUtil2 restUtil;
    public List<String> endpoints;

    public Integrasjonstest(RestUtil2 restUtil, List<String> endpoints) {
        this.restUtil = restUtil;
        this.endpoints = endpoints;
    }

    @PostConstruct
    public void fetchData() {
        List<String> endpointsListe = new ArrayList<>();
        endpointsListe.add("/administrasjon/kodeverk/admin/cache/status");
        endpointsListe.add("https://beta.felleskomponent.no/utdanning/elev/admin/cache/status");

        endpointsListe.forEach(i -> {

            restUtil.get(i)
                    .doOnNext(this::dataLog)
                    .doOnError(trown -> log.error("Noe gikk galt: " + trown.getMessage()))
                    .subscribe();
        });
    }

    public void dataLog(HashMap<String, HashMap<String, Informasjon>> response) {
        for (var row : response.keySet()) {
            log.info(String.valueOf(row));
        }
        log.info("___________");
    }
}
