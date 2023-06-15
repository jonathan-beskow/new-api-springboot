package com.example.estudo.apigateway.startup.Services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.estudo.apigateway.startup.Controller.PersonController;
import com.example.estudo.apigateway.startup.ExceptionResponse.RequiredObjectIsNullException;
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
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<PersonVO> findAll() {
        var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
        persons.stream()
                .forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return persons;
    }

    public PersonVO create(PersonVO person) {
        if (person == null) throw new RequiredObjectIsNullException();
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);

    }

    public PersonVO update(PersonVO person) {
        if (person == null) throw new RequiredObjectIsNullException();
        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        var vo = DozerMapper.parseObject(entity, PersonVO.class);
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

}
