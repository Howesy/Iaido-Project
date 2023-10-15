package com.example.iadiointerviewtest

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE p.firstName LIKE %:name%")
    fun findByPersonName(@Param("name") firstName: String): List<Person>
}