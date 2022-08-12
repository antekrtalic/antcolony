package com.example.antcolony.service;

import com.example.antcolony.api.service.PeopleServiceLayer;
import com.example.antcolony.dto.PeopleDTO;
import com.example.antcolony.models.People;
import com.example.antcolony.repository.PeopleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeopleServiceLayerImpl implements PeopleServiceLayer {

    private ModelMapper modelMapper;

    private PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PeopleServiceLayerImpl(ModelMapper modelMapper, PeopleRepository peopleRepository, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<PeopleDTO> findAll() {
        return peopleRepository.findAll().stream()
                .map(people -> modelMapper.map(people, PeopleDTO.class)).collect(Collectors.toList());
    }

    @Override
    public PeopleDTO findPersonById(Long id) {
        Optional<People> result = peopleRepository.findById(id);
        People thePeople = null;

        if (result.isPresent()) {
            thePeople = result.get();
        } else {
            throw new RuntimeException("Did not find any person with id " + id);
        }

        return modelMapper.map(thePeople, PeopleDTO.class);
    }

    @Override
    public PeopleDTO addPerson(PeopleDTO peopleDTO) {

        peopleDTO.setPassword(passwordEncoder.encode(peopleDTO.getPassword()));
        peopleDTO.setVerifyPassword(passwordEncoder.encode(peopleDTO.getVerifyPassword()));

        People peopleRequest = modelMapper.map(peopleDTO, People.class);

        People people = peopleRepository.save(peopleRequest);

        PeopleDTO peopleResponse = modelMapper.map(people, PeopleDTO.class);

        return peopleResponse;
    }

    @Override
    public PeopleDTO updatePerson(Long id, PeopleDTO peopleDTO) {
        peopleDTO.setId(id);

        People peopleRequest = modelMapper.map(peopleDTO, People.class);

        People people = peopleRepository.save(peopleRequest);

        PeopleDTO peopleResponse = modelMapper.map(people, PeopleDTO.class);

        return peopleResponse;
    }

    @Override
    public PeopleDTO deletePerson(Long id) {

        People people = peopleRepository.getReferenceById(id);

        PeopleDTO peopleDTO = modelMapper.map(people, PeopleDTO.class);

        peopleRepository.deleteById(id);

        return peopleDTO;
    }
}
