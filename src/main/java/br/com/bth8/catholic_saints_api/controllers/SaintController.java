package br.com.bth8.catholic_saints_api.controllers;

import br.com.bth8.catholic_saints_api.dto.SaintDTO;
import br.com.bth8.catholic_saints_api.mapper.ObjectMapper;
import br.com.bth8.catholic_saints_api.model.Saint;
import br.com.bth8.catholic_saints_api.services.SaintService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction;

@RestController
@RequestMapping("/api/saint/v1")
public class SaintController {

    @Autowired
    private SaintService service;

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public List<SaintDTO> findAll(
    ) {
      return service.findAll();
    }

    @GetMapping(
            value = "/{id}",
            produces = {
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
                MediaType.APPLICATION_YAML_VALUE
            }
    )
    public SaintDTO findById(@PathVariable("id")UUID id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();//retorna status code
    }
}
