package com.spring.confinance.controller
import com.spring.confinance.model.Movement
import com.spring.confinance.repository.MovementRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

    @RestController
    @RequestMapping("/api")
    class MovementController(private val MovementRepository: MovementRepository) {

        @GetMapping("/Movements")
        fun getAllMovements(): List<Movement> =
            MovementRepository.findAll()


        @PostMapping("/Movements")
        fun createNewMovement(@Valid @RequestBody Movement: Movement): Movement =
            MovementRepository.save(Movement)


        @GetMapping("/Movements/{id}")
        fun getMovementById(@PathVariable(value = "id") MovementId: Long): ResponseEntity<Movement> {
            return MovementRepository.findById(MovementId).map { Movement ->
                ResponseEntity.ok(Movement)
            }.orElse(ResponseEntity.notFound().build())
        }

        @PutMapping("/Movements/{id}")
        fun updateMovementById(
            @PathVariable(value = "id") MovementId: Long,
            @Valid @RequestBody newMovement: Movement
        ): ResponseEntity<Movement> {

            return MovementRepository.findById(MovementId).map { existingMovement ->
                val updatedMovement: Movement = existingMovement
                    .copy(title = newMovement.title, content = newMovement.content)

                ResponseEntity.ok().body(MovementRepository.save(updatedMovement))
            }.orElse(ResponseEntity.notFound().build())

        }

        @DeleteMapping("/Movements/{id}")
        fun deleteMovementById(@PathVariable(value = "id") MovementId: Long): ResponseEntity<Void> {

            return MovementRepository.findById(MovementId).map { Movement ->
                MovementRepository.delete(Movement)
                ResponseEntity<Void>(HttpStatus.OK)
            }.orElse(ResponseEntity.notFound().build())

        }
    }
