package com.example.iadiointerviewtest

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController(private val personService: PersonService) {

    @GetMapping("/persons")
    fun retrieveAllPersons():
            List<Person> = personService.retrieveAllPersons()

    @GetMapping("/personsByName/{firstName}")
    fun retrieveAllPersonsByName(@PathVariable("firstName") name: String): List<CleanedPerson> {
        val allPersons = personService.retrieveAllPersonsByName(name)
        val cleanedPersons = mutableListOf<CleanedPerson>()
        allPersons.forEach { person -> cleanedPersons.add(person.clean()) }
        return cleanedPersons
    }

    @GetMapping("/personsByAge/{age}")
    fun retrieveAllPersonsByAge(@PathVariable("age") age: String): List<CleanedPerson> {
        val allPersons = personService.retrieveAllPersonsByAge(age)
        val cleanedPersons = mutableListOf<CleanedPerson>()
        allPersons.forEach { person -> cleanedPersons.add(person.clean()) }
        return cleanedPersons
    }

    @GetMapping("/persons/{id}")
    fun retrievePerson(@PathVariable("id") id: Long):
        Person = personService.retrievePerson(id)

    @PostMapping("/persons")
    fun createPerson(@RequestBody requestBody: Person):
            Person = personService.createPerson(requestBody)

    @PutMapping("/persons/{id}")
    fun updatePerson(@PathVariable("id") personID: Long, @RequestBody requestBody: Person):
            Person = personService.updatePerson(personID, requestBody)

    @DeleteMapping("/persons/{id}")
    fun deletePerson(@PathVariable("id") personID: Long):
            Unit = personService.deletePerson(personID)
}