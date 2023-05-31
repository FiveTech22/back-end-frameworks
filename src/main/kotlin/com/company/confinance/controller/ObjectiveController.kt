package com.company.confinance.controller

import com.company.confinance.model.entity.ObjectiveModel
import com.company.confinance.model.response.CustomResponse
import com.company.confinance.repository.ObjectiveRepository
import com.company.confinance.repository.UserRepository
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
import javax.persistence.EntityNotFoundException


@RestController
@RequestMapping("/objective")
class ObjectiveController {

    @Autowired
    private lateinit var repository: ObjectiveRepository
    @Autowired
    private lateinit var userRepository: UserRepository

    @PostMapping
    fun createObjective(
        @RequestBody objective : ObjectiveModel
    ): ObjectiveModel {
        return  repository.save(objective)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        return repository.deleteById(id)
    }


    @PutMapping("/{id}")
    fun updateObjectiveById(
        @PathVariable (value = "id") id: Long,
        @RequestBody objective: ObjectiveModel
    ): ResponseEntity<ObjectiveModel> {
        val existingObjective = repository.findById(id)
        if (existingObjective.isPresent) {
            val updateObjective = existingObjective.get()
            updateObjective.value = objective.value
            updateObjective.description = objective.description
            updateObjective.date = objective.date
            repository.save(updateObjective)
            return ResponseEntity.ok(updateObjective)
        }
        return ResponseEntity.notFound().build()
    }



    @GetMapping("/{id}")
    fun getObjectiveId(
        @PathVariable("id") id: Long,
    ): ObjectiveModel {
        return repository.findById(id).orElseThrow(){
            EntityNotFoundException()
        }
    }

    @GetMapping
    fun getObjective(): List<ObjectiveModel> {
        return repository.findAll()
    }

    @GetMapping("/user/{userId}")
    fun getObjectiveByUserId(
        @PathVariable("userId") userId: Long
    ): List<ObjectiveModel> {
        return repository.findByUserId(userId)
    }


}