package br.com.bth8.catholic_saints_api.controllers;

import br.com.bth8.catholic_saints_api.dto.ReligiousOrderDTO;
import br.com.bth8.catholic_saints_api.model.Saint;
import br.com.bth8.catholic_saints_api.services.ReligiousOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ReligiousOrder/v1")
public class ReligiousOrderController {

    @Autowired
    private ReligiousOrderService service;


    @GetMapping(
            value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public ReligiousOrderDTO findById(@PathVariable("id")UUID id) {
        return service.findById(id);
    }

//    @GetMapping(
//            produces = {
//                    MediaType.APPLICATION_JSON_VALUE,
//                    MediaType.APPLICATION_XML_VALUE,
//                    MediaType.APPLICATION_YAML_VALUE
//            }
//    )
//    public List<Saint> getAllMenbers() {
//        return service.findAllMenber();
//    }

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_YAML_VALUE
            }
    )
    public List<ReligiousOrderDTO> getAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
