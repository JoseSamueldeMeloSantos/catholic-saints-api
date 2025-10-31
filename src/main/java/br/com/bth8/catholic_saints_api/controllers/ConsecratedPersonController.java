package br.com.bth8.catholic_saints_api.controllers;

import br.com.bth8.catholic_saints_api.dto.ConsecratedPersonDTO;
import br.com.bth8.catholic_saints_api.services.ConsecratedPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/saint/ConsecratedPerson")
public class ConsecratedPersonController {

    @Autowired
    private ConsecratedPersonService service;


    @PostMapping (
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    private ConsecratedPersonDTO create(@RequestBody ConsecratedPersonDTO saint) {
        return service.create(saint);
    }

    @PutMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    private ConsecratedPersonDTO update(ConsecratedPersonDTO saint) {
        return service.update(saint);
    }
}
