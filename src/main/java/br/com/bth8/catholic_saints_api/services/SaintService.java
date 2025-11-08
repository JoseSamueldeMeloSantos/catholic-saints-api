package br.com.bth8.catholic_saints_api.services;

import br.com.bth8.catholic_saints_api.controllers.LayPersonController;
import br.com.bth8.catholic_saints_api.controllers.SaintController;
import br.com.bth8.catholic_saints_api.dto.LayPersonDTO;
import br.com.bth8.catholic_saints_api.dto.SaintDTO;
import br.com.bth8.catholic_saints_api.exception.EntityNotFound;
import br.com.bth8.catholic_saints_api.mapper.ObjectMapper;
import br.com.bth8.catholic_saints_api.model.Saint;
import br.com.bth8.catholic_saints_api.repository.SaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    @Autowired
    private PagedResourcesAssembler<SaintDTO> assembler;

    public PagedModel<EntityModel<SaintDTO>> findAll(Pageable pageable) {
        logger.info("finding all saint");

        Page<Saint> saints = repository.findAll(pageable);

        Page<SaintDTO> saintDtoWithHateos = saints.map( saint -> {
            SaintDTO dto = mapper.parseObject(saint, SaintDTO.class);
            addHateosLinks(dto);
            return dto;
        });

        Link findAllLink =
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder
                        .methodOn(SaintController.class)
                        .findAll(pageable.getPageNumber(), pageable.getPageSize(), String.valueOf(pageable.getSort())))
                        .withSelfRel();

        return assembler.toModel(saintDtoWithHateos, findAllLink);
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

        dto.add(linkTo(methodOn(SaintController.class).findAll(1,12,"asc")).withRel("findAll").withType("GET"));

        dto.add(linkTo(methodOn(SaintController.class).delete(dto.getSaintId())).withRel("delete").withType("DELETE"));
    }
}
