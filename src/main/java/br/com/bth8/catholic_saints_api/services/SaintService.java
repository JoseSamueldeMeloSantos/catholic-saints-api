package br.com.bth8.catholic_saints_api.services;

import br.com.bth8.catholic_saints_api.controllers.SaintController;
import br.com.bth8.catholic_saints_api.dto.LayPersonDTO;
import br.com.bth8.catholic_saints_api.dto.SaintDTO;
import br.com.bth8.catholic_saints_api.exception.EntityNotFound;
import br.com.bth8.catholic_saints_api.mapper.ObjectMapper;
import br.com.bth8.catholic_saints_api.model.Saint;
import br.com.bth8.catholic_saints_api.repository.SaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class SaintService {

    private Logger logger = Logger.getLogger(SaintService.class.getName());

    @Autowired
    private SaintRepository repository;
    @Autowired
    private ObjectMapper mapper;

    public List<SaintDTO> findAll() {
        logger.info("finding all saint");

        List<SaintDTO> dtoList = mapper.parseListObjects(repository.findAll(), SaintDTO.class);
        dtoList.forEach(dto -> {addHateosLinks(dto);});

        return dtoList;
    }

    public SaintDTO findById(UUID id) {
        logger.info("finding a saint by his ID");

        Saint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity not found"));

        SaintDTO dto = mapper.parseObject(entity, SaintDTO.class);

        addHateosLinks(dto);

        return dto;
    }

    public void delete(UUID id) {
        logger.info("Deleting a saint by his ID");

        Saint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity Not Found"));


        repository.delete(entity);
    }

    private void addHateosLinks(SaintDTO dto) {
        logger.info("adding hateoas");

        dto.add(linkTo(methodOn(SaintController.class).findById(dto.getSaintId())).withSelfRel().withType("GET"));

        dto.add(linkTo(methodOn(SaintController.class).findAll()).withRel("findAll").withType("GET"));

        dto.add(linkTo(methodOn(SaintController.class).delete(dto.getSaintId())).withRel("delete").withType("DELETE"));
    }
}
