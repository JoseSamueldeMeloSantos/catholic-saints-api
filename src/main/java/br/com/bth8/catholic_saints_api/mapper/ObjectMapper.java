package br.com.bth8.catholic_saints_api.mapper;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ObjectMapper {

    private final ModelMapper modelMapper;

    public ObjectMapper() {
        this.modelMapper = new ModelMapper();
    }

    public <O,D> D parseObject(O origin, Class<D> destination) {
        return this.modelMapper.map(origin, destination);
    }

    public <O,D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationList = new ArrayList<>();

        for (Object o: origin) {
            destinationList.add(this.modelMapper.map(o,destination));
        }

        return destinationList;
    }
}
