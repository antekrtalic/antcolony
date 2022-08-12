package com.example.antcolony.controller;

import com.example.antcolony.api.service.PeopleServiceLayer;
import com.example.antcolony.dto.PeopleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class PeopleController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PeopleServiceLayer peopleServiceLayer;

    @GetMapping("/")
    public List<PeopleDTO> getAllPeople() {
        return peopleServiceLayer.findAll().stream().map(people -> modelMapper.map(people, PeopleDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PeopleDTO findPersonById(@PathVariable("id") Long id) {

        return peopleServiceLayer.findPersonById(id);
    }

    @PostMapping("/register")
    public PeopleDTO createPerson(@Valid @RequestBody PeopleDTO peopleDTO) {

        return peopleServiceLayer.addPerson(peopleDTO);
    }

    @PutMapping("/{id}")
    public PeopleDTO updatePerson(@PathVariable("id") Long id, PeopleDTO peopleDTO) {
        return peopleServiceLayer.updatePerson(id, peopleDTO);
    }

    @DeleteMapping("/delete/{id}")
    public PeopleDTO deletePerson(@PathVariable("id") Long id) {
        return peopleServiceLayer.deletePerson(id);
    }
}
