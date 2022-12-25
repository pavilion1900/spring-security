package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.clevertec.dto.AuthenticationDto;
import ru.clevertec.dto.PersonDto;
import ru.clevertec.entity.Person;
import ru.clevertec.mapper.PersonMapper;
import ru.clevertec.service.JWTUtil;
import ru.clevertec.service.RegistrationService;
import ru.clevertec.util.PersonValidator;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final PersonMapper personMapper;
    private final AuthenticationManager authenticationManager;

//    @GetMapping("/login")
//    public String loginPage() {
//        return "auth/login";
//    }
//
//    @GetMapping("/registration")
//    public String registrationPage(@ModelAttribute("person") Person person) {
//        return "auth/registration";
//    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDto personDto,
                                                   BindingResult bindingResult) {
        Person person = personMapper.toEntity(personDto);
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return Map.of("message", "Error");
        }
        registrationService.register(person);
        String token = jwtUtil.generateToken(person.getUsername());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                authenticationDto.getUsername(), authenticationDto.getPassword());
        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException ex) {
            return Map.of("message", "Incorrect credentials");
        }
        String token = jwtUtil.generateToken(authenticationDto.getUsername());
        return Map.of("jwt-token", token);
    }
}
