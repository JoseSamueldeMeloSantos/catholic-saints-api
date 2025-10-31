package br.com.bth8.catholic_saints_api.services;

import br.com.bth8.catholic_saints_api.dto.ConsecratedPersonDTO;
import br.com.bth8.catholic_saints_api.exception.EntityNotFound;
import br.com.bth8.catholic_saints_api.mapper.ObjectMapper;
import br.com.bth8.catholic_saints_api.model.ConsecratedPerson;
import br.com.bth8.catholic_saints_api.repository.SaintRepository;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ConsecratedPersonService {

    private Logger logger = Logger.getLogger(ConsecratedPersonService.class.getName());

    @Autowired
    private SaintRepository repository;


    public ConsecratedPersonDTO create(ConsecratedPersonDTO saint) {
        logger.info("creating a ConsecratedPerson");

        ConsecratedPerson entity = ObjectMapper.parseObject(saint, ConsecratedPerson.class);

        return ObjectMapper.parseObject(repository.save(entity), ConsecratedPersonDTO.class);
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

        return ObjectMapper.parseObject(repository.save(entity),ConsecratedPersonDTO.class);
    }
}
