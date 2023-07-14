package no.fintlabs;


import lombok.extern.slf4j.Slf4j;
import no.fintlabs.model.Informasjon;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;


@Slf4j
@Service
public class UtdanningAdmin {

    private final RestUtil2 restUtil2;

    public UtdanningAdmin(RestUtil2 restUtil2) {
        this.restUtil2 = restUtil2;
    }

    @PostConstruct
    public void test4() {
        restUtil2
                .get("/utdanning/elev/admin/cache/status")
                .doOnNext(this::logResult)
                .doOnError(trown -> log.error("Noe gikk galt: " + trown.getMessage()))
                .subscribe();
    }

    private void logResult(HashMap<String, HashMap<String, Informasjon>> result) {
        for (var row : result.keySet()) {
            int count = result.get(row).values().stream().mapToInt(Informasjon::getSize).sum();
            log.info(row + " " + count);
        }
    }

}