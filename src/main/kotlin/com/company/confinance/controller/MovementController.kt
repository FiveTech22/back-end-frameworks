package com.company.confinance.controller

import com.company.confinance.model.MovementModel
import com.company.confinance.model.UserModel
import com.company.confinance.repository.MovementRepository
import com.company.confinance.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/movement")
class MovementController{

    @Autowired
    private lateinit var repository: MovementRepository


    @PostMapping
    fun createMovement(
        @RequestBody movement: MovementModel
    ): MovementModel {
        return repository.save(movement)
    }

    @GetMapping("/{id}")
    fun getMovementById(
        @PathVariable("id") id: Long
    ): Optional<MovementModel> {
        return repository.findById(id)
    }

    @GetMapping
    fun getMovement(): List<MovementModel> {
        return repository.findAll()
    }
    @GetMapping("/user/{userId}")
    fun getMovementsByUserId(
        @PathVariable("userId") userId: Long
    ): List<MovementModel> {
        return repository.findByUserId(userId)
    }
    @PutMapping("/{id}")
    fun updateMovementById(
        @PathVariable("id") id: Long,
        @RequestBody movement: MovementModel
    ): MovementModel {
        if (movement.id != id) {
            throw IllegalArgumentException("")
        }
        return repository.save(movement)
    }

    @DeleteMapping("/{id}")
    fun deleteMovementById(
        @PathVariable("id") id: Long
    ) {
        return repository.deleteById(id)
    }
}
