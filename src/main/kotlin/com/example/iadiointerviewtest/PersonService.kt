package com.example.iadiointerviewtest

import org.springframework.stereotype.Service
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus

@Service
class PersonService(private val personRepository: PersonRepository) {

    //CRUD - Create

    fun createPerson(person: Person): Person = personRepository.save(person)

    //CRUD - Retrieve

    fun retrieveAllPersons():
            List<Person> = personRepository.findAll()


    fun retrieveAllPersonsByName(personName: String):
            List<Person> = personRepository.findByPersonName(personName)

    fun retrieveAllPersonsByAge(personAge: String):
            List<Person> = personRepository.findAll(Sort.by(Sort.Direction.DESC, "age"))

    fun retrievePerson(personID: Long):
            Person = personRepository.findById(personID)
            .orElseThrow{PersonNotFoundException(HttpStatus.NOT_FOUND, "Unable to retrieve person matching that ID, they do not exist.")}

    //CRUD - Update

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
        } else throw PersonNotFoundException(HttpStatus.NOT_FOUND, "Unable to retrieve person matching that ID, they do not exist.")
    }

    //CRUD - Delete

    fun deletePerson(personID: Long): Unit {
        if (personRepository.existsById(personID)) {
            return personRepository.deleteById(personID)
        } else throw PersonNotFoundException(HttpStatus.NOT_FOUND, "Unable to retrieve person matching that ID, they do not exist.")
    }

}

class PersonNotFoundException(httpStatus: HttpStatus, message: String): Exception("Error Code: ${httpStatus.toString()} - $message")