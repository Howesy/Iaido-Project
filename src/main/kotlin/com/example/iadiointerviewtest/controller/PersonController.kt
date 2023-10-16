package com.example.iadiointerviewtest.controller

import com.example.iadiointerviewtest.entity.CleanedPerson
import com.example.iadiointerviewtest.entity.Person
import com.example.iadiointerviewtest.entity.clean
import com.example.iadiointerviewtest.service.PersonService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonController(private val personService: PersonService) {

    @GetMapping("/persons")
    fun retrieveAllPersons(@RequestParam page: Int, @RequestParam amount: Int): Page<Person> {
        return personService.retrieveAllPersons(page, amount)
    }

    @GetMapping("/searchPersons")
    fun searchPersons(@RequestParam(required = false) name: String?, @RequestParam(required = false) age: String?): List<CleanedPerson> {

        //Neither parameter supplied.
        if (name == null && age == null)
            return listOf<CleanedPerson>()

        //Either or parameter supplied.
        if (name != null && age == null) {
            val allPersons = personService.retrieveAllPersonsByName(name)
            return cleanPersonList(allPersons)
        } else if (age != null && name == null) {
            val allPersons = personService.retrieveAllPersonsByAge(age)
            return cleanPersonList(allPersons)
        }

        //Both parameters supplied.
        val persons = personService.retrieveAllPersonsByIdentifiers(name, age)
        return cleanPersonList(persons)
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

fun cleanPersonList(personList: List<Person>): List<CleanedPerson> {
    val cleanedPersons = mutableListOf<CleanedPerson>()
    personList.forEach { person -> cleanedPersons.add(person.clean()) }
    return cleanedPersons
}