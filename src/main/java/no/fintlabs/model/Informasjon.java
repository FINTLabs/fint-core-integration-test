package no.fintlabs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Informasjon {
    @JsonProperty("lastUpdated")
    private String lastUpdated;
    @JsonProperty("size")
    private int size;
}
