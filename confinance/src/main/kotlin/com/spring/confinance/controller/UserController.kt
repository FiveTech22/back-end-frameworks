package com.spring.confinance.controller

import com.spring.confinance.model.User
import com.spring.confinance.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/Users")
    fun getAllUsers(): List<User> =
            userRepository.findAll()


    @PostMapping("/Users")
    fun createNewUser(@Valid @RequestBody User: User): User =
            userRepository.save(User)


    @GetMapping("/Users/{id}")
    fun getUserById(@PathVariable(value = "id") UserId: Long): ResponseEntity<User> {
        return userRepository.findById(UserId).map { User ->
            ResponseEntity.ok(User)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/Users/{id}")
    fun updateUserById(@PathVariable(value = "id") UserId: Long,
                          @Valid @RequestBody newUser: User
    ): ResponseEntity<User> {

        return userRepository.findById(UserId).map { existingUser ->
            val updatedUser: User = existingUser
                    .copy(title = newUser.title, content = newUser.content)

            ResponseEntity.ok().body(userRepository.save(updatedUser))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/Users/{id}")
    fun deleteUserById(@PathVariable(value = "id") UserId: Long): ResponseEntity<Void> {

        return userRepository.findById(UserId).map { User  ->
            userRepository.delete(User)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
}