package com.travelfast.codebase.repositories;

import com.travelfast.codebase.entities.Adventure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdventureRepository extends CrudRepository<Adventure, Integer> {
    public List<Adventure> findByCountry(String country);
    public List<Adventure> findByState(String state);
    public List<Adventure> findByCity(String city);
    //create findById method
    public Adventure findById(int id);
    //create save method
    public Adventure save(Adventure adventure);
    //delete
    public void delete(Adventure adventure);
    //findAll: returns all entities as an Iterable<T> where T is the entity type Adventure
    public Iterable<Adventure> findAll();
}
