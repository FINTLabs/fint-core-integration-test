package no.fintlabs;

import lombok.extern.slf4j.Slf4j;
import no.fintlabs.model.Informasjon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class Integrasjonstest {
    private final RestUtil restUtil;
    @Value("${fint.cache-status-endpoints}")
    private List<String> cacheStatusEndpoints;
    private List<Class> classes;

    public Integrasjonstest(RestUtil restUtil, List<String> cacheStatusEndpoints, List<Class> classes) {
        this.restUtil = restUtil;
        this.cacheStatusEndpoints = cacheStatusEndpoints;
        this.classes = classes;
    }

    @PostConstruct
    public void logCacheStatus() {
        cacheStatusEndpoints.forEach(endpoint ->
                restUtil.getCacheStatus(endpoint + "/admin/cache/status")
                        .subscribe(response -> dataLog(response, endpoint))
        );
    }

    @PostConstruct
    public void fetchData() {
        log.info(String.valueOf(cacheStatusEndpoints));

//        endpointsListe.forEach(i -> {
//            restUtil.get(i).doOnError(throwable -> {
//                classes.forEach(y -> {
//                    restUtil.get(y, i).subscribe(l -> log.info(String.valueOf(l)));
//                });
//            }).doOnNext(this::dataLog).subscribe();
//        });
    }

    public void dataLog(HashMap<String, HashMap<String, Informasjon>> response, String endpoint) {
        log.info("Cache status for endpoint: " + endpoint);
        for (var row : response.keySet()) {
            log.info(String.valueOf(row) + ": " + response.get(row));
        }
        log.info("________________");
    }
}

