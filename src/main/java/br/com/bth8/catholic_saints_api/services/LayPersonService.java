package br.com.bth8.catholic_saints_api.services;

import br.com.bth8.catholic_saints_api.controllers.LayPersonController;
import br.com.bth8.catholic_saints_api.dto.ConsecratedPersonDTO;
import br.com.bth8.catholic_saints_api.dto.LayPersonDTO;
import br.com.bth8.catholic_saints_api.dto.SaintDTO;
import br.com.bth8.catholic_saints_api.exception.EntityNotFound;
import br.com.bth8.catholic_saints_api.mapper.ObjectMapper;
import br.com.bth8.catholic_saints_api.model.ConsecratedPerson;
import br.com.bth8.catholic_saints_api.model.LayPerson;
import br.com.bth8.catholic_saints_api.repository.SaintRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class LayPersonService {

    private Logger logger = Logger.getLogger(LayPersonController.class.getName());

    @Autowired
    private SaintRepository repository;
    @Autowired
    private ObjectMapper mapper;


    public LayPersonDTO findByID(UUID id) {
        logger.info("finding a LayPerson by his ID");

        LayPerson enity = (LayPerson) repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity Not Found"));

        LayPersonDTO dto = mapper.parseObject(enity, LayPersonDTO.class);
        addHateoas(dto);

        return dto;
    }

    public LayPersonDTO create(LayPersonDTO saint) {
        logger.info("creating a LayPerson");

        LayPerson entity = mapper.parseObject(saint, LayPerson.class);

        LayPersonDTO dto = mapper.parseObject( repository.save(entity),LayPersonDTO.class);
        addHateoas(dto);

        return dto;
    }


    public LayPersonDTO update(LayPersonDTO saint) {
        logger.info("updating LayPerson");

        LayPerson entity = (LayPerson) repository.findById(saint.getSaintId())
                .orElseThrow(() -> new EntityNotFound("Entity Not Found"));

        entity.setName(saint.getName());
        entity.setDescription(saint.getDescription());
        entity.setBaptismDate(saint.getBaptismDate());
        entity.setDeathDate(saint.getDeathDate());
        entity.setCanonizationDate(saint.getCanonizationDate());
        entity.setMiracles(saint.getMiracles());
        entity.setOcupation(saint.getOcupation());
        entity.setMaried(saint.getMaried());
        entity.setAssociatedMovement(saint.getAssociatedMovement());

        LayPersonDTO dto = mapper.parseObject(repository.save(entity),LayPersonDTO.class);
        addHateoas(dto);

        return dto;
    }

    private void addHateoas(LayPersonDTO dto) {
        logger.info("adding hateoas");

        dto.add(linkTo(methodOn(LayPersonController.class).findById(dto.getSaintId())).withSelfRel().withType("GET"));

        dto.add(linkTo(methodOn(LayPersonController.class).create(dto)).withRel("create").withType("POST"));

        dto.add(linkTo(methodOn(LayPersonController.class).update(dto)).withRel("update").withType("PUT"));
    }
}
