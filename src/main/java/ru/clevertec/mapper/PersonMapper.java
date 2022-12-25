package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.dto.PersonDto;
import ru.clevertec.entity.Person;

@Mapper
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    Person toEntity(PersonDto personDto);
}
