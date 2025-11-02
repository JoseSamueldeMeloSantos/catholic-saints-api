package br.com.bth8.catholic_saints_api.services;

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

@Service
public class SaintService {

    private Logger logger = Logger.getLogger(SaintService.class.getName());

    @Autowired
    private SaintRepository repository;
    @Autowired
    private ObjectMapper mapper;

    public List<SaintDTO> findAll() {
        logger.info("finding all saint");
        return mapper.parseListObjects(repository.findAll(), SaintDTO.class);
    }

    public SaintDTO findById(UUID id) {
        logger.info("finding a saint by his ID");

        Saint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity not found"));

        logger.info("retornando dto");
        return mapper.parseObject(entity, SaintDTO.class);
    }

    public void delete(UUID id) {
        logger.info("Deleting a saint by his ID");

        Saint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity Not Found"));


        repository.delete(entity);
    }
}
