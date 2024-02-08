package com.travelfast.codebase.controllers;

import com.travelfast.codebase.entities.Adventure;
import com.travelfast.codebase.repositories.AdventureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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

    @GetMapping("/bycountry/{country}")
    public List<Adventure> getAdventuresByCountry(@PathVariable("country") String country) {
        return adventureRepository.findByCountry(country);
    }

    //Create a method that:
    //
    //Responds to GET requests like /traveladventures/bystate?state=Lisboa
    //Accepts a state parameter as a query parameter (it would capture Lisboa here)
    //Returns a list of adventures matching the requested state
    @GetMapping("/bystate?state={state}")
    public List<Adventure> getAdventuresByState(@RequestParam(name="state") String state) {
        return adventureRepository.findByState(state);
    }

    @PostMapping()
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
    @PutMapping("/{id}")
    public Adventure updateAdventure(@PathVariable("id") int id, @RequestBody Adventure adventure)  {
        Optional<Adventure> optionalAdventure = adventureRepository.findById(id);//todo:fix this
        if (!optionalAdventure.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adventure not found");
        }
        Adventure existingAdventure = optionalAdventure.get();
        existingAdventure.setBlogCompleted(adventure.isBlogCompleted());
        return adventureRepository.save(existingAdventure);
    }

    //Create a method that:
    //
    //Responds to DELETE requests like /traveladventures/10
    //Accepts a path variable id (in place of 10 here)
    //Removes adventure based on an entered ID.
    @DeleteMapping("/{id}")
    public void deleteAdventure(@PathVariable("id") int id) {
        Optional<Adventure> optionalAdventureToDelete = Optional.ofNullable(adventureRepository.findById(id));
        if (!optionalAdventureToDelete.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Adventure not found");
        }
        adventureRepository.delete(optionalAdventureToDelete.get());
    }



}
