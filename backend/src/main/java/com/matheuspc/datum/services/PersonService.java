package com.matheuspc.datum.services;

import com.matheuspc.datum.dto.PersonDTO;
import com.matheuspc.datum.entities.Person;
import com.matheuspc.datum.repositories.PersonRepository;
import com.matheuspc.datum.services.exceptions.DatabaseException;
import com.matheuspc.datum.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional(readOnly = true)
    public List<PersonDTO> findAll() {
        List<Person> list = personRepository.findAll();
        return list.stream().map(x -> new PersonDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PersonDTO findById(Long id) {
        Optional<Person> obj = personRepository.findById(id);
        Person entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new PersonDTO(entity);
    }

    @Transactional
    public PersonDTO insert(PersonDTO dto) {
        Person entity = new Person();
        copyDtoToEntity(dto, entity);
        entity = personRepository.save(entity);
        return new PersonDTO(entity);
    }

    @Transactional
    public PersonDTO update(Long id, PersonDTO dto) {
        try {
            Person entity = personRepository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = personRepository.save(entity);
            return new PersonDTO(entity);
        } catch (EntityNotFoundException ex) {
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    public void delete(Long id) {
        try {
            personRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Id not found");
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(PersonDTO dto, Person entity){
        entity.setNome(dto.getNome());
        entity.setSexo(dto.getSexo());
        entity.setEmail(dto.getEmail());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setNaturalidade(dto.getNaturalidade());
        entity.setNacionalidade(dto.getNacionalidade());
        entity.setCpf(dto.getCpf());
    }
}
