package no.fintlabs;


import lombok.extern.slf4j.Slf4j;
import no.fintlabs.model.Informasjon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class UtdanningAdmin {

    @Value("${fint.utdanning.endpoints}")
    private List<String> endpoints;
    private final RestUtil2 restUtil2;

    public UtdanningAdmin(RestUtil2 restUtil2) {
        this.restUtil2 = restUtil2;
    }

    @Scheduled(cron = "0 */5 7-16 * * *")
    public void test4() {
        endpoints.forEach(e -> {
            String endpoint = "/utdanning" + e + "/admin/cache/status";
            restUtil2.get(endpoint)
                    .doOnNext(this::Result)
                    .doOnError(trown -> log.error("Noe gikk galt: " + trown.getMessage()))
                    .subscribe();
        });
    }

    private void Result(HashMap<String, HashMap<String, Informasjon>> result) {
        for (var row : result.keySet()) {
            int count = result.get(row).values().stream().mapToInt(Informasjon::getSize).sum();
            log.info(row + " " + count);
        }
    }

        /*
    public void logKeyValues(HashMap<String, HashMap<String, Informasjon>> keyValues) {
        for (String row : keyValues.keySet()) {
            log.info(row);
        }
    }

         */
}