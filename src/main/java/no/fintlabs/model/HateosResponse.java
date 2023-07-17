package no.fintlabs.model;

import lombok.Data;

import java.util.Map;

@Data
public class HateosResponse {
    private Map<String, Map<String, Informasjon>> response;
}
