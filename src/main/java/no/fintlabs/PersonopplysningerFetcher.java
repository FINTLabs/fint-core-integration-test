package no.fintlabs;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.personvern.kodeverk.PersonopplysningResources;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class PersonopplysningerFetcher {
    private final RestUtil restutil;

    public PersonopplysningerFetcher(RestUtil restutil) {
        this.restutil = restutil;
    }

    @Scheduled(cron = "0 */5 7-16 * * *")
    private void test2() {
        restutil.get(PersonopplysningResources.class, "/personvern/kodeverk/personopplysning").subscribe(l -> log.info(l.toString()));
    }
}