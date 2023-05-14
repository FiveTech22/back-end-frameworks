package com.company.confinance.controller

import com.company.confinance.model.ObjectiveModel
import com.company.confinance.repository.ObjectiveRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.management.loading.ClassLoaderRepository


@RestController
@RequestMapping("/objectives")
class ObjectiveController {

    @Autowired
    private lateinit var repository: ObjectiveRepository

    @PostMapping
    fun createObjective(@RequestBody objective: ObjectiveModel): ResponseEntity<Any> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED).body(repository.save(objective))
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar objetivo")
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        return repository.deleteById(id)
    }

    @PutMapping("/{id}")
    fun updateObjectiveById(
        @PathVariable("id") id: Long,
        @RequestBody objective: ObjectiveModel
    ): ResponseEntity<Any> {
        val existingObjective = repository.findById(id)
        return if (existingObjective.isPresent){
            val savedObjective = repository.save(objective.copy(id = id))
            ResponseEntity.ok(savedObjective)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Objetivo não encontrado")
        }
    }

    @GetMapping("/{id}")
    fun getObjectiveById(@PathVariable("id") id: Long): ResponseEntity<Any> {
        return if (id <= 0) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id de objetivo inválido")
        } else {
            try {
                val objective = repository.findById(id)
                if (objective.isPresent) {
                    ResponseEntity.ok(objective.get())
                } else {
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Objetivo não encontrado")
                }
            } catch (ex: Exception) {
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar objetivo")
            }
        }
    }


    @GetMapping
    fun getObjectives(): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok(repository.findAll())
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar objetivo")
        }
    }

    @GetMapping("/user/{userId}")
    fun getObjectiveByUserId(
        @PathVariable("userId") userId: Long
    ): List<ObjectiveModel> {
        return repository.findByUserId(userId)
    }


}