package br.com.bth8.catholic_saints_api.services;

import br.com.bth8.catholic_saints_api.controllers.ReligiousOrderController;
import br.com.bth8.catholic_saints_api.dto.ConsecratedPersonDTO;
import br.com.bth8.catholic_saints_api.dto.ReligiousOrderDTO;
import br.com.bth8.catholic_saints_api.exception.EntityNotFound;
import br.com.bth8.catholic_saints_api.mapper.ObjectMapper;
import br.com.bth8.catholic_saints_api.model.ConsecratedPerson;
import br.com.bth8.catholic_saints_api.model.ReligiousOrder;
import br.com.bth8.catholic_saints_api.repository.ReligiousOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ReligiousOrderService {

    @Autowired
    private ReligiousOrderRepository repository;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ConsecratedPersonService cPService;
    @Autowired
    private PagedResourcesAssembler<ConsecratedPersonDTO> assemblerCP;
    @Autowired
    private PagedResourcesAssembler<ReligiousOrderDTO> assemblerRO;

    private Logger logger = Logger.getLogger(ReligiousOrderService.class.getName());

    public ReligiousOrderDTO findById(UUID id) {
        logger.info("finding a ReligiousOrder by his ID");

        ReligiousOrder entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity Not Found"));

        ReligiousOrderDTO dto = mapper.parseObject(entity, ReligiousOrderDTO.class);
        addHateoas(dto);
        return dto;
    }

    public PagedModel<EntityModel<ReligiousOrderDTO>> findAll(Pageable pageable) {
        logger.info("finding all ReligiousOrder");

        Page<ReligiousOrder> orders = repository.findAll(pageable);

        Page<ReligiousOrderDTO> orderDtoWithHateoas = orders.map(
                order -> {
                    ReligiousOrderDTO dto = mapper.parseObject(order, ReligiousOrderDTO.class);
                    addHateoas(dto);
                    return dto;
                }
        );

        Link findAllLink =
               linkTo(
                    methodOn(ReligiousOrderController.class)
                    .findAll(
                            pageable.getPageNumber(),
                            pageable.getPageSize(),
                            String.valueOf(pageable.getSort())
                    )
               )
                .withSelfRel();

        return assemblerRO.toModel(orderDtoWithHateoas, findAllLink);
    }

    public void delete(UUID id) {
        logger.info("deleting a ReligiousOrder by his ID");

        ReligiousOrder entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity Not Found"));

        repository.delete(entity);
    }

    public PagedModel<EntityModel<ConsecratedPersonDTO>> findAllMenbers(Pageable pageable, String orderName) {
        logger.info("finding all menbers of an religious order");

        Page<ConsecratedPerson> menbers = repository.findMembersByOrderName(orderName, pageable);

        Page<ConsecratedPersonDTO> menberDtoWithHateos = menbers.map(
                menber -> {
                    ConsecratedPersonDTO dto = mapper.parseObject(menber, ConsecratedPersonDTO.class);
                    cPService.addHateoas(dto);
                    return dto;
                }
        );

        Link findAllLink =
                linkTo(
                    methodOn(ReligiousOrderController.class)
                    .findAllMenbers(
                            pageable.getPageNumber(),
                            pageable.getPageSize(),
                            String.valueOf(pageable.getSort()),
                            orderName
                    )
                )
                .withSelfRel();

        return assemblerCP.toModel(menberDtoWithHateos,findAllLink);
    }

    public void addHateoas(ReligiousOrderDTO dto) {
        logger.info("adding hateoas");
        dto.add(linkTo(methodOn(ReligiousOrderController.class).findById(dto.getReligiousOrderId())).withSelfRel().withType("GET"));

        dto.add(linkTo(methodOn(ReligiousOrderController.class).findAll(0, 12, "asc")).withRel("findAll").withType("GET"));

        dto.add(linkTo(methodOn(ReligiousOrderController.class).findAllMenbers(0,12, "asc","orderName")).withRel("findAllMenbers").withType("GET"));
    }
}
