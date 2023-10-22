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
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
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
            if (movement.fixedIncome == true) {
                createFixedIncomeMovements(movement) // No need to set fixedIncomeDurationMonths
            } else {
                repository.save(movement)
            }

            ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(movement).toMovementResponse())
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                CustomResponse(
                    "Erro ao criar movimento", HttpStatus.INTERNAL_SERVER_ERROR.value()
                )
            )
        }
    }

    private fun createFixedIncomeMovements(movement: MovementModel) {
        val currentYearMonth = YearMonth.now()
        val currentMonth = currentYearMonth.monthValue
        val currentYear = currentYearMonth.year

        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val originalDate = LocalDate.parse(movement.date, dateFormatter)

        for (i in 1 until 12) { // Fixed at 12 months for fixed income
            val newDate = originalDate.plusMonths(i.toLong())
            val newDateString = newDate.format(dateFormatter)
            val newMovement = movement.copy(date = newDateString)
            repository.save(newMovement)
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
                        "Movimento não encontrado, verifique o id.", HttpStatus.NOT_FOUND.value()
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
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    CustomResponse(
                        "Nenhum movimento encontrado para o usuário especificado",
                        HttpStatus.NOT_FOUND.value()
                    )
                )
            }
        }
    }

    @GetMapping("/totals/user/{userId}")
    fun getTotals(@PathVariable("userId") id: Long): Any {
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
                var totalRevenues = 0.0
                var totalExpenses = 0.0

                for (movement in movements) {
                    if (movement.type_movement == "receita") {
                        totalRevenues += movement.value
                    } else if (movement.type_movement == "despesa") {
                        totalExpenses += movement.value
                    }
                }

                val total = totalRevenues - totalExpenses

                val totals = mapOf(
                    "userId" to id,
                    "totalRevenues" to totalRevenues,
                    "totalExpenses" to totalExpenses,
                    "total" to total

                )

                return ResponseEntity.ok(totals)
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    CustomResponse(
                        "Nenhum movimento encontrado para o usuário especificado",
                        HttpStatus.NOT_FOUND.value()
                    )
                )
            }

        }
    }


    @PutMapping("/{id}")
    fun updateMovementById(
        @PathVariable("id") id: Long, @RequestBody movement: MovementModel
    ): ResponseEntity<Any> {
        val existingMovement = repository.findById(id)
        return if (existingMovement.isPresent) {
            val savedMovement = repository.save(movement.copy(id = id))
            ResponseEntity.ok(savedMovement)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Movimento não encontrado, verifique o id.", HttpStatus.NOT_FOUND.value()
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
                    "ID de movimento inválido", HttpStatus.BAD_REQUEST.value()
                )
            )
        } else if (existingMovement.isPresent) {
            repository.deleteById(id)
            ResponseEntity.ok()
                .body(CustomResponse("Movimento Deletado com sucesso", HttpStatus.OK.value()))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Movimento não encontrado, verifique o id.", HttpStatus.NOT_FOUND.value()
                )
            )
        }
    }

    @GetMapping("/user/{userId}/month/{month}/year/{year}")
    fun getMovementsByUserIdAndMonthandYear(
        @PathVariable("userId") id: Long,
        @PathVariable("month") month: Int,
        @PathVariable("year") year: Int
    ): ResponseEntity<Any> {
        return if (id <= 0 || month < 1 || month > 12) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "Parâmetros inválidos, por favor, forneça um ID de usuário válido e um número de mês entre 1 e 12.",
                    HttpStatus.BAD_REQUEST.value()
                )
            )
        } else {
            val user = userRepository.findById(id)
            if (user.isPresent) {
                val movements = repository.findByUserIdAndMonthandYear(id, month, year)
                var totalRevenues = 0.0
                var totalExpenses = 0.0

                for (movement in movements) {
                    if (movement.type_movement == "receita") {
                        totalRevenues += movement.value
                    } else if (movement.type_movement == "despesa") {
                        totalExpenses += movement.value
                    }
                }

                val total = totalRevenues - totalExpenses

                val totals = mapOf(
                    "userId" to id,
                    "totalRevenues" to totalRevenues,
                    "totalExpenses" to totalExpenses,
                    "total" to total
                )

                ResponseEntity.ok(totals)
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    CustomResponse(
                        "Nenhum movimento encontrado para o usuário especificado",
                        HttpStatus.NOT_FOUND.value()
                    )
                )
            }
        }
    }
}
