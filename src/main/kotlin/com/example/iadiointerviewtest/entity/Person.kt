package com.example.iadiointerviewtest.entity

import com.example.iadiointerviewtest.dto.CleanedPerson
import jakarta.persistence.*

@Entity
@Table(name = "person")
data class Person (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        @Column(name = "name", nullable = false)
        val firstName: String,
        @Column(name = "surname", nullable = false)
        val lastName: String,
        @Column(name = "email", nullable = false)
        val email: String,
        @Column(name = "phone", nullable = false)
        val phone: String,
        @Column(name = "dateOfBirth", nullable = false)
        val dateOfBirth: String,
        @Column(name = "age", nullable = false)
        val age: String,
        @Column(name = "username", unique = true, nullable = false)
        val username: String,
        @Column(name = "password", nullable = false)
        val password: String
)

fun Person.clean() = CleanedPerson(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phone = phone,
        dateOfBirth = dateOfBirth,
        age = age
)