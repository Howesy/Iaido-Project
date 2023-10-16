package com.example.iadiointerviewtest

import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.data.domain.PageRequest

@Service
class PersonService(private val personRepository: PersonRepository) {

    fun createPerson(person: Person): Person {
        if (personRepository.findUniqueUsername(person.username) != null)
            throw PersonAlreadyExists("ERROR: Unable to create that person, a person already exists with that username!")

        return personRepository.save(person)
    }

    fun retrieveAllPersons(pageNumber: Int, pageSize: Int): Page<Person> {
        val pageable = PageRequest.of(pageNumber, pageSize)
        return personRepository.findAll(pageable)
    }

    fun retrieveAllPersonsByName(personName: String):
            List<Person> = personRepository.findByPersonName(personName)

    fun retrieveAllPersonsByAge(personAge: String):
            List<Person> = personRepository.findByPersonAge(personAge)

    fun retrieveAllPersonsByIdentifiers(personName: String?, personAge: String?):
            List<Person> = personRepository.findPersonsByIdentifiers(personName, personAge)

    fun retrievePerson(personID: Long):
            Person = personRepository.findById(personID)
            .orElseThrow{PersonNotFound("ERROR: Unable to retrieve person matching that ID, they do not exist.")}

    fun retrievePersonByUnique(username: String):
            Person? = personRepository.findUniqueUsername(username)

    fun updatePerson(personID: Long, person: Person): Person {
        if (personRepository.existsById(personID)) {

            val newPerson = Person(
                    id = person.id,
                    firstName = person.firstName,
                    lastName = person.lastName,
                    email = person.email,
                    phone = person.phone,
                    dateOfBirth = person.dateOfBirth,
                    age = person.age,
                    username = person.username,
                    password = person.password
            )

            return personRepository.save(newPerson)
        } else throw PersonNotFound("ERROR: Unable to update person matching that ID, they do not exist.")
    }

    fun deletePerson(personID: Long): Unit {
        if (personRepository.existsById(personID)) {
            return personRepository.deleteById(personID)
        } else throw PersonNotFound("ERROR: Unable to delete person matching that ID, they do not exist.")
    }

}