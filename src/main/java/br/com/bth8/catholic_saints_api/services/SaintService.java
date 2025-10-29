package br.com.bth8.catholic_saints_api.services;

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

    public List<SaintDTO> findAll() {
        logger.info("finding all saint");
        return ObjectMapper.parseListObjects(repository.findAll(), SaintDTO.class);
    }


    public SaintDTO create(SaintDTO saint) {
        logger.info("creating a saint");

        Saint entity = ObjectMapper.parseObject(saint, Saint.class);

        return ObjectMapper.parseObject(repository.save(entity),SaintDTO.class);
    }

    public SaintDTO findById(UUID id) {
        Saint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity not found"));

        return ObjectMapper.parseObject(entity, SaintDTO.class);
    }

    public void delete(UUID id) {

        Saint entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Entity Not Found"));

        repository.delete(entity);
    }

    public SaintDTO update(SaintDTO saint) {

        Saint entity = repository.findById(saint.getSaintId())
                .orElseThrow(() -> new EntityNotFound("Entity Not Found"));

        entity.setName(saint.getName());
        entity.setDescription(saint.getDescription());
        entity.setBaptismDate(saint.getBaptismDate());
        entity.setDeathDate(saint.getDeathDate());
        entity.setCanonizationDate(saint.getCanonizationDate());
        entity.setMiracles(saint.getMiracles());

        return ObjectMapper.parseObject(entity,SaintDTO.class);
    }
}
