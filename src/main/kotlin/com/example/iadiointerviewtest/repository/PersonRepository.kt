package com.example.iadiointerviewtest.repository

import com.example.iadiointerviewtest.entity.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p WHERE p.firstName LIKE %:name%")
    fun findByPersonName(@Param("name") firstName: String): List<Person>

    @Query("SELECT p FROM Person p WHERE p.age = :age")
    fun findByPersonAge(@Param("age") age: String): List<Person>

    @Query("SELECT p FROM Person p WHERE p.firstName LIKE %:name% AND p.age = :age")
    fun findPersonsByIdentifiers(@Param("name") firstName: String?, @Param("age") age: String?): List<Person>

    @Query("SELECT p FROM Person p WHERE p.username = :username")
    fun findUniqueUsername(@Param("username") username: String): Person?
}