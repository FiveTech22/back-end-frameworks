package com.spring.confinance.controller

import com.spring.confinance.model.Objective
import com.spring.confinance.repository.ObjectiveRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ObjectiveController(private val objectiveRepository: ObjectiveRepository) {

    @GetMapping("/Objectives")
    fun getAllUsers(): List<Objective> =
        objectiveRepository.findAll()


    @PostMapping("/Objectives")
    fun createNewUser(@Valid @RequestBody Objective : Objective): Objective =
        objectiveRepository.save(Objective)

    @GetMapping("/Objectives/{id}")
    fun getUserById(@PathVariable(value = "id") UserId: Long): ResponseEntity<Objective> {
        return objectiveRepository.findById(UserId).map { Objective ->
            ResponseEntity.ok(Objective)
        }.orElse(ResponseEntity.notFound().build())
    }
    @PutMapping("/Objectives/{id}")
    fun updateUserById(@PathVariable(value = "id") ObjectiveId: Long,
                       @Valid @RequestBody newUser: Objective
    ): ResponseEntity<Objective> {

        return objectiveRepository.findById(ObjectiveId).map { existingObjective ->
            val updatedUser: Objective = existingObjective
                .copy(title = newUser.title, content = newUser.content)
            ResponseEntity.ok().body(objectiveRepository.save(updatedUser))
        }.orElse(ResponseEntity.notFound().build())

    }
    @DeleteMapping("/Users/{id}")
    fun deleteUserById(@PathVariable(value = "id") ObjectiveId: Long): ResponseEntity<Void> {

        return objectiveRepository.findById(ObjectiveId).map { User  ->
            objectiveRepository.delete(User)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())

    }
    
}