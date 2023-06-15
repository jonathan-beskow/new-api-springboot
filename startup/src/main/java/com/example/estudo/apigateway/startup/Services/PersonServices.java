package com.example.estudo.apigateway.startup.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estudo.apigateway.startup.ExceptionResponse.ResourceNotFoundException;
import com.example.estudo.apigateway.startup.Mapper.DozerMapper;
import com.example.estudo.apigateway.startup.Model.Person;
import com.example.estudo.apigateway.startup.Repository.PersonRepository;
import com.example.estudo.apigateway.startup.VO.V1.PersonVO;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    public PersonVO findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records Found for this ID"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public List<PersonVO> findAll() {
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);

    }

    public PersonVO create(PersonVO person) {
        var entity = DozerMapper.parseObject(person, Person.class);
        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public PersonVO update(PersonVO person) {
        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
    }

}
