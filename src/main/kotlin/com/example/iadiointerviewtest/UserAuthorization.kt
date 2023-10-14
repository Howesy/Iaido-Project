package com.example.iadiointerviewtest

import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Bean
fun users(): UserDetailsService {
    
    val users = User.withDefaultPasswordEncoder()

    val guest = users
            .username("guest")
            .password("guestPassword")
            .roles("GUEST")
            .build()

    val admin = users
            .username("admin")
            .password("adminPassword")
            .roles("GUEST", "ADMIN")
            .build()

    return InMemoryUserDetailsManager(guest, admin)
}
