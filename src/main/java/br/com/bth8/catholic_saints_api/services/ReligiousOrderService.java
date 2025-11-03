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
import org.springframework.stereotype.Service;

import java.util.List;
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

    private Logger logger = Logger.getLogger(ReligiousOrderService.class.getName());

    public ReligiousOrderDTO findById(UUID id) {
        logger.info("finding a ReligiousOrder by his ID");

        ReligiousOrder entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity Not Found"));

        ReligiousOrderDTO dto = mapper.parseObject(entity, ReligiousOrderDTO.class);
        addHateoas(dto);
        return dto;
    }

    public List<ReligiousOrderDTO> findAll() {
        logger.info("finding all ReligiousOrder");

        List<ReligiousOrder> entities = repository.findAll();

        List<ReligiousOrderDTO> listDto = mapper.parseListObjects(entities,ReligiousOrderDTO.class);
        listDto.forEach((dto) -> addHateoas(dto));

        return listDto;
    }

    public void delete(UUID id) {
        logger.info("deleting a ReligiousOrder by his ID");

        ReligiousOrder entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity Not Found"));

        repository.delete(entity);
    }

    public List<ConsecratedPersonDTO> findAllMenbers(String orderName) {
        logger.info("finding all menbers of an religious order");

        Optional<ReligiousOrder> order = repository.findByName(orderName);
        List<ConsecratedPerson> menbers = order.get().getMenbers();

        List<ConsecratedPersonDTO> listDto = mapper.parseListObjects(menbers, ConsecratedPersonDTO.class);
        listDto.forEach((dto) -> cPService.addHateoas(dto));

        return listDto;
    }

    public void addHateoas(ReligiousOrderDTO dto) {
        logger.info("adding hateoas");
        dto.add(linkTo(methodOn(ReligiousOrderController.class).findById(dto.getReligiousOrderId())).withSelfRel().withType("GET"));

        dto.add(linkTo(methodOn(ReligiousOrderController.class).findAll()).withRel("findAll").withType("GET"));

        dto.add(linkTo(methodOn(ReligiousOrderController.class).findAllMenbers("")).withRel("findAllMenbers").withType("GET"));
    }
}
