package org.example.firstRESTApp.controllers;

import org.example.firstRESTApp.models.Person;
import org.example.firstRESTApp.service.PeopleService;
import org.example.firstRESTApp.util.PersonErrorResponse;
import org.example.firstRESTApp.util.PersonNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public List<Person> getPeople() {
        return peopleService.findAll(); // Jackson конвертирует эти объекты в JSON
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        return peopleService.findOne(id); // Jackson конвертирует этот объект в JSON
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFountException e) {
        PersonErrorResponse response = new PersonErrorResponse(
            "Person with this id wasn't found!",
                System.currentTimeMillis()
        );

        // Будет в HTTP ответе тело ответа (response) и статус в заголовке
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // NOT_FOUND - 404 статус
    }
}
