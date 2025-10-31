package br.com.bth8.catholic_saints_api.services;

import br.com.bth8.catholic_saints_api.dto.ReligiousOrderDTO;
import br.com.bth8.catholic_saints_api.exception.EntityNotFound;
import br.com.bth8.catholic_saints_api.mapper.ObjectMapper;
import br.com.bth8.catholic_saints_api.model.ReligiousOrder;
import br.com.bth8.catholic_saints_api.repository.ReligiousOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ReligiousOrderService {

    @Autowired
    private ReligiousOrderRepository repository;

    private Logger logger = Logger.getLogger(ReligiousOrderService.class.getName());


    public ReligiousOrderDTO create(ReligiousOrderDTO religiousOrder) {
        logger.info("creating ReligiousOrder");

        ReligiousOrder entity = ObjectMapper.parseObject(religiousOrder, ReligiousOrder.class);

        return ObjectMapper.parseObject(repository.save(entity),ReligiousOrderDTO.class);
    }

    public ReligiousOrderDTO findById(UUID id) {
        logger.info("finding a ReligiousOrder by his ID");

        ReligiousOrder entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity Not Found"));

        return ObjectMapper.parseObject(entity, ReligiousOrderDTO.class);
    }

    public List<ReligiousOrderDTO> findAll() {
        logger.info("finding all ReligiousOrder");

        List<ReligiousOrder> entities = repository.findAll();

        return ObjectMapper.parseListObjects(entities,ReligiousOrderDTO.class);
    }


    public void delete(UUID id) {
        logger.info("deleting a ReligiousOrder by his ID");

        ReligiousOrder entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity Not Found"));

        repository.delete(entity);
    }

    public ReligiousOrderDTO update(ReligiousOrderDTO religiousOrder) {
        logger.info("updating a ReligiousOrder");

        ReligiousOrder entity = repository.findById(religiousOrder.getReligiousOrderId())
                .orElseThrow(() -> new EntityNotFound("Enity Not Found"));

        entity.setFounder(religiousOrder.getFounder());
        entity.setName(religiousOrder.getName());
        entity.setFoundationDate(religiousOrder.getFoundationDate());
        entity.setVows(religiousOrder.getVows());

        return ObjectMapper.parseObject(repository.save(entity), ReligiousOrderDTO.class);
    }
}
