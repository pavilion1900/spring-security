package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.entity.Person;
import ru.clevertec.repository.PersonRepository;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final PersonRepository personRepository;

    @Transactional
    public void register(Person person) {
        personRepository.save(person);
    }
}
