package com.company.confinance.controller

import com.company.confinance.model.entity.MovementModel
import com.company.confinance.model.mapper.toMovementResponse
import com.company.confinance.model.response.CustomResponse
import com.company.confinance.repository.MovementRepository
import com.company.confinance.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/movement")
class MovementController {

    @Autowired
    private lateinit var repository: MovementRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @PostMapping
    fun createMovement(
        @RequestBody movement: MovementModel
    ): ResponseEntity<Any> {
        return try {

            ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(movement).toMovementResponse())
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                    CustomResponse(
                        "Erro ao criar movimento",
                        HttpStatus.INTERNAL_SERVER_ERROR.value()
                    )
                )
        }
    }


    @GetMapping("/{id}")
    fun getMovementById(@PathVariable("id") id: Long): ResponseEntity<*> {
        return if (id <= 0) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "Erro id informado inválido, por favor passe o Id correto.",
                    HttpStatus.BAD_REQUEST.value()
                )
            )
        } else {
            val movement = repository.findById(id)
            if (movement.isPresent) {
                ResponseEntity.ok(movement.get())
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    CustomResponse(
                        "Movimento não encontrado, verifique o id.",
                        HttpStatus.NOT_FOUND.value()
                    )
                )
            }
        }
    }


    @GetMapping
    fun getMovement(): ResponseEntity<Any> {
        return if (repository.findAll().isNotEmpty()) {
            ResponseEntity.ok(repository.findAll())
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CustomResponse("Nenhum movimento encontrado", HttpStatus.NOT_FOUND.value()))
        }
    }

    @GetMapping("/user/{userId}")
    fun getMovementsByUserId(
        @PathVariable("userId") id: Long
    ): ResponseEntity<Any> {
        return if (id <= 0) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "Erro id informado inválido, por favor passe o Id correto.",
                    HttpStatus.BAD_REQUEST.value()
                )
            )
        } else {
            val user = userRepository.findById(id)
            if (user.isPresent) {
                val movements = repository.findByUserId(id)
                ResponseEntity.ok(movements)
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                        CustomResponse(
                            "Nenhum movimento encontrado para o usuário especificado",
                            HttpStatus.NOT_FOUND.value()
                        )
                    )
            }
        }
    }

    @GetMapping("/totals")
    fun getTotals(): ResponseEntity<Any> {
        val movements = repository.findAll()

        var totalRevenues = 0.0
        var totalExpenses = 0.0

        for (movement in movements) {
            if (movement.type_movement == "revenue") {
                totalRevenues += movement.value
            } else if (movement.type_movement == "expense") {
                totalExpenses += movement.value
            }
        }

        val total = totalRevenues - totalExpenses

        val totals = mapOf(
            "totalRevenues" to totalRevenues,
            "totalExpenses" to totalExpenses,
            "total" to total
        )

        return ResponseEntity.ok(totals)
    }


    @PutMapping("/{id}")
    fun updateMovementById(
        @PathVariable("id") id: Long,
        @RequestBody movement: MovementModel
    ): ResponseEntity<Any> {
        val existingMovement = repository.findById(id)
        return if (existingMovement.isPresent) {
            val savedMovement = repository.save(movement.copy(id = id))
            ResponseEntity.ok(savedMovement)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Movimento não encontrado, verifique o id.",
                    HttpStatus.NOT_FOUND.value()
                )
            )
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMovement(@PathVariable(value = "id") id: Long): ResponseEntity<Any> {
        val existingMovement = repository.findById(id)
        return if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "ID de movimento inválido",
                    HttpStatus.BAD_REQUEST.value()
                )
            )
        } else if (existingMovement.isPresent) {
            repository.deleteById(id)
            ResponseEntity.ok()
                .body(CustomResponse("Movimento Deletado com sucesso", HttpStatus.OK.value()))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Movimento não encontrado, verifique o id.",
                    HttpStatus.NOT_FOUND.value()
                )
            )
        }
    }

    @DeleteMapping("/user/{userId}/movement/{movementId}")
    fun deleteMovementByUserIdAndMovementId(
        @PathVariable("userId") userId: Long,
        @PathVariable("movementId") movementId: Long
    ): ResponseEntity<Any> {
        return if (userId <= 0 || movementId <= 0) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "IDs de usuário ou movimento inválidos",
                    HttpStatus.BAD_REQUEST.value()
                )
            )
        } else {
            val user = userRepository.findById(userId)
            if (user.isPresent) {
                val movement = repository.findByUserIdAndMovementId(userId, movementId)
                if (movement.isPresent) {
                    repository.deleteById(movementId)
                    ResponseEntity.ok()
                        .body(CustomResponse("Movimento do usuário deletado com sucesso", HttpStatus.OK.value()))
                } else {
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        CustomResponse(
                            "Movimento não encontrado para o usuário especificado",
                            HttpStatus.NOT_FOUND.value()
                        )
                    )
                }
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    CustomResponse(
                        "Usuário não encontrado, verifique o ID.",
                        HttpStatus.NOT_FOUND.value()
                    )
                )
            }
        }
    }
}