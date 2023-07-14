package no.fintlabs;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.personvern.kodeverk.BehandlingsgrunnlagResources;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BehandligsgrunnlagFetcher {
    private final RestUtil restUtil;

    public BehandligsgrunnlagFetcher(RestUtil restUtil) {
        this.restUtil = restUtil;
    }

    @Scheduled(cron = "0 */5 7-16 * * *")
    private void test() {
        restUtil.get(BehandlingsgrunnlagResources.class, "/personvern/kodeverk/behandlingsgrunnlag").subscribe(l -> log.info(l.toString()));
    }
}