package br.com.bth8.catholic_saints_api.controllers;

import br.com.bth8.catholic_saints_api.dto.LayPersonDTO;
import br.com.bth8.catholic_saints_api.dto.SaintDTO;
import br.com.bth8.catholic_saints_api.services.LayPersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/saint/layperson/v1")
public class LayPersonController {

    private LayPersonService service;

    @PostMapping(
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
    public LayPersonDTO create(@RequestBody LayPersonDTO saint) {
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
    public LayPersonDTO update(@RequestBody LayPersonDTO saint) {
        return service.update(saint);
    }
}
