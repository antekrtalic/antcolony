package com.example.antcolony.api.service;

import com.example.antcolony.dto.PeopleDTO;

import java.util.List;

public interface PeopleServiceLayer {

    List<PeopleDTO> findAll();
    PeopleDTO findPersonById(Long id);
    PeopleDTO addPerson(PeopleDTO peopleDTO);
    PeopleDTO updatePerson(Long id, PeopleDTO peopleDTO);
    PeopleDTO deletePerson(Long id);
}
