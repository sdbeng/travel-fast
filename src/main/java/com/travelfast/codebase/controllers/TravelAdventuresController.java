package com.travelfast.codebase.controllers;

import com.travelfast.codebase.entities.Adventure;
import com.travelfast.codebase.repositories.AdventureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController("/traveladventures")
public class TravelAdventuresController {
    private final AdventureRepository adventureRepository;
    
    public TravelAdventuresController(AdventureRepository adventureRepository) {
        this.adventureRepository = adventureRepository;
    }
    
    @GetMapping()
    public Iterable<Adventure> getAdventures() {
        return adventureRepository.findAll();
    }

    @GetMapping("/traveladventures/bycountry/{country}")
    public Iterable<Adventure> getAdventuresByCountry(@PathVariable String country) {
        return adventureRepository.findByCountry(country);
    }

    //Create a method that:
    //
    //Responds to GET requests like /traveladventures/bystate?state=Lisboa
    //Accepts a state parameter as a query parameter (it would capture Lisboa here)
    //Returns a list of adventures matching the requested state
    @GetMapping("/traveladventures/bystate?state={state}")
    public Iterable<Adventure> getAdventuresByState(@RequestParam String state) {
        return adventureRepository.findByState(state);
    }

    @PostMapping("/traveladventures")
    public Adventure addAdventure(@RequestBody Adventure adventure) {
        return adventureRepository.save(adventure);
    }

    //Responds to PUT requests like /traveladventures/10
    //Accepts an adventure object in the request body
    //Updates the adventure id as a template path variable and an adventure object in the request body
    //Returns the updated adventure object, make sure the id for the adventure object in the request body matches the
    // id in the template path variable
    //make sure th eid for the adventure exists: use the Optional<Adventure> type and its methods isPresent() and get()
//    if does not exist, throw an exception and modify the HTTP status code to NOT_FOUND
    //if id exists, toggle the blogCompleted property to either true or false, depending on the value sent by the client and
    //return the updated adventure object
    @PutMapping("/traveladventures/{id}")
    public Optional<Adventure> updateAdventureOptional(@PathVariable int id, @RequestBody Adventure adventure) {
        Optional<Adventure> adventureOptional = Optional.ofNullable(adventureRepository.findById(id));
        if (!adventureOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adventure not found");
        }

        //toggle the blogCompleted property to either true or false, depending on the value sent by the client
        adventure.setBlogCompleted(!adventure.isBlogCompleted());
        //return the updated adventure object
        adventure = adventureOptional.get();
        return Optional.ofNullable(adventureRepository.save(adventure));
    }

    //Create a method that:
    //
    //Responds to DELETE requests like /traveladventures/10
    //Accepts a path variable id (in place of 10 here)
    //Removes adventure based on an entered ID.
    @RequestMapping(value = "/traveladventures/{id}", method = RequestMethod.DELETE)
    public void deleteAdventure(@PathVariable int id) {
        adventureRepository.deleteById(id);
    }



}
