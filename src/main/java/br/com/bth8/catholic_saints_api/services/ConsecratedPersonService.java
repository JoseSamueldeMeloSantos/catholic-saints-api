package br.com.bth8.catholic_saints_api.services;

import br.com.bth8.catholic_saints_api.controllers.ConsecratedPersonController;
import br.com.bth8.catholic_saints_api.controllers.SaintController;
import br.com.bth8.catholic_saints_api.dto.ConsecratedPersonDTO;
import br.com.bth8.catholic_saints_api.dto.SaintDTO;
import br.com.bth8.catholic_saints_api.exception.EntityNotFound;
import br.com.bth8.catholic_saints_api.mapper.ObjectMapper;
import br.com.bth8.catholic_saints_api.model.ConsecratedPerson;
import br.com.bth8.catholic_saints_api.repository.SaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ConsecratedPersonService {

    private Logger logger = Logger.getLogger(ConsecratedPersonService.class.getName());

    @Autowired
    private SaintRepository repository;
    @Autowired
    private ObjectMapper mapper;


    public ConsecratedPersonDTO create(ConsecratedPersonDTO saint) {
        logger.info("creating a ConsecratedPerson");

        ConsecratedPerson entity = mapper.parseObject(saint, ConsecratedPerson.class);

        ConsecratedPersonDTO dto = mapper.parseObject(repository.save(entity), ConsecratedPersonDTO.class);
        addHateoas(dto);

        return dto;
    }

    public ConsecratedPersonDTO update(ConsecratedPersonDTO saint) {
        logger.info("updating a ConsecratedPerson");

        ConsecratedPerson entity = (ConsecratedPerson) repository.findById(saint.getSaintId())
                .orElseThrow(() -> new EntityNotFound());

        entity.setName(saint.getName());
        entity.setDescription(saint.getDescription());
        entity.setBaptismDate(saint.getBaptismDate());
        entity.setDeathDate(saint.getDeathDate());
        entity.setCanonizationDate(saint.getCanonizationDate());
        entity.setMiracles(saint.getMiracles());
        entity.setPosition(saint.getPosition());
        entity.setDiocese(saint.getDiocese());
        entity.setOrdinationDate(saint.getOrdinationDate());
        entity.setReligiousOrder(saint.getReligiousOrder());

        ConsecratedPersonDTO dto = mapper.parseObject(repository.save(entity),ConsecratedPersonDTO.class);
        addHateoas(dto);

        return dto;
    }

    public ConsecratedPersonDTO findByID(UUID id) {
        logger.info("finding a consecratedPerson by his ID");

        ConsecratedPerson enity = (ConsecratedPerson) repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity Not Found"));

        logger.info("adding the hateoas");
        ConsecratedPersonDTO dto = mapper.parseObject(enity, ConsecratedPersonDTO.class);
        addHateoas(dto);

        return dto;
    }

    public void addHateoas(ConsecratedPersonDTO dto) {
        logger.info("adding hateoas");

        dto.add(linkTo(methodOn(ConsecratedPersonController.class).findById(dto.getSaintId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(ConsecratedPersonController.class).update(dto)).withRel("update").withType("DELETE"));
        dto.add(linkTo(methodOn(ConsecratedPersonController.class).create(dto)).withRel("create").withRel("create").withType("POST"));
    }
}
