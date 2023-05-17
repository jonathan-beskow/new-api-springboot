package com.example.estudo.apigateway.startup.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.estudo.apigateway.startup.Model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
    
}
