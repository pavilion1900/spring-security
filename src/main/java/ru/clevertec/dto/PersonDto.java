package ru.clevertec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {

    @NotBlank(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов")
    private String username;

    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    private Integer yearOfBirth;

    @NotBlank
    private String password;
}
