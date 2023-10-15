package com.example.iadiointerviewtest

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RegexRequestMatcher

@Configuration
@EnableWebSecurity
class HTTPSecurityConfiguration {
    @Bean
    fun userDetailsServiceHandler(): UserDetailsService {
        val users = User.withDefaultPasswordEncoder()

        val guest = users
                .username("guest")
                .password("superMegaGuestPassword123@")
                .roles("GUEST")
                .build()

        val admin = users
                .username("admin")
                .password("UltimateUberAdminPassword123@")
                .roles("GUEST", "ADMIN")
                .build()

        return InMemoryUserDetailsManager(guest, admin)
    }

    @Bean
    fun apiFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()

        val personNameMatcher = AntPathRequestMatcher("/personsByName/**", "GET")
        val personsAgeMatcher = AntPathRequestMatcher("/personsByAge/**", "GET")
        val personsMatcher = RegexRequestMatcher.regexMatcher("/persons")

        http.authorizeHttpRequests()
            .requestMatchers(personsAgeMatcher)
            .hasAnyRole("GUEST", "ADMIN")
            .requestMatchers(personNameMatcher)
            .hasAnyRole("GUEST", "ADMIN")
            .requestMatchers(personsMatcher)
            .hasRole("ADMIN")
            .and()
            .httpBasic()

        return http.build()
    }


}

