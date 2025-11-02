package br.com.bth8.catholic_saints_api.services;

import br.com.bth8.catholic_saints_api.controllers.LayPersonController;
import br.com.bth8.catholic_saints_api.dto.LayPersonDTO;
import br.com.bth8.catholic_saints_api.dto.SaintDTO;
import br.com.bth8.catholic_saints_api.exception.EntityNotFound;
import br.com.bth8.catholic_saints_api.mapper.ObjectMapper;
import br.com.bth8.catholic_saints_api.model.LayPerson;
import br.com.bth8.catholic_saints_api.repository.SaintRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class LayPersonService {

    private Logger logger = Logger.getLogger(LayPersonController.class.getName());

    @Autowired
    private SaintRepository repository;
    @Autowired
    private ObjectMapper mapper;


    public LayPersonDTO create(LayPersonDTO saint) {
        logger.info("creating a LayPerson");

        LayPerson entity = mapper.parseObject(saint, LayPerson.class);

        return mapper.parseObject( repository.save(entity),LayPersonDTO.class);
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

        return mapper.parseObject(repository.save(entity),LayPersonDTO.class);
    }
}
