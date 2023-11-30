package com.company.confinance.controller

import com.company.confinance.main
import com.company.confinance.model.entity.MovementModel
import com.company.confinance.model.mapper.toMovementResponse
import com.company.confinance.model.response.CustomResponse
import com.company.confinance.model.response.MovementUpdateRequest
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
                val mainMovement = repository.save(movement)
                createFixedIncomeMovements(movement, mainMovement.id!!)
            } else if (movement.recurrenceFrequency != null) {
                val mainMovement = repository.save(movement)
                createRecurringMovements(movement, mainMovement.id!!)
            } else {
                repository.save(movement)
            }

            ResponseEntity.status(HttpStatus.CREATED).body(
                CustomResponse(
                    "Movimentação criada com sucesso!",
                    HttpStatus.CREATED.value(),
                )
            )
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                CustomResponse(
                    "Erro ao criar movimento", HttpStatus.INTERNAL_SERVER_ERROR.value()
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

    @GetMapping("/user/{userId}/movements")
    fun getMovementsByType(
        @PathVariable("userId") userId: Long, @RequestParam("type") type: String
    ): ResponseEntity<Any> {
        if (userId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "Erro: ID informado é inválido. Por favor, forneça um ID válido.",
                    HttpStatus.BAD_REQUEST.value()
                )
            )
        }
        val user = userRepository.findById(userId)
        if (!user.isPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Nenhum usuário encontrado com o ID especificado.", HttpStatus.NOT_FOUND.value()
                )
            )
        }

        val movements = when (type) {
            "receita" -> repository.findRevenuesByUserId(userId)
            "despesa" -> repository.findExpensesByUserId(userId)
            else -> repository.findByUserId(userId)
        }

        if (movements.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Nenhuma movimentação encontrada com o tipo especificado.",
                    HttpStatus.NOT_FOUND.value()
                )
            )
        }

        return ResponseEntity.ok(movements)
    }


    @GetMapping("/totals/user/{userId}")
    fun getTotals(@PathVariable("userId") id: Long): Any {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "Erro: ID informado é inválido. Por favor, forneça um ID válido.",
                    HttpStatus.BAD_REQUEST.value()
                )
            )
        }

        val user = userRepository.findById(id)
        if (user.isPresent) {
            val totalRevenues = repository.findTotalRevenuesByUserId(id)
            val totalExpenses = repository.findTotalExpensesByUserId(id)
            val total = totalRevenues - totalExpenses

            val totals = mapOf(
                "userId" to id,
                "totalRevenues" to totalRevenues,
                "totalExpenses" to totalExpenses,
                "total" to total
            )

            return ResponseEntity.ok(totals)
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Nenhum movimento encontrado para o usuário especificado",
                    HttpStatus.NOT_FOUND.value()
                )
            )
        }
    }

    @PutMapping("/{id}")
    fun updateMovementById(
        @PathVariable("id") id: Long, @RequestBody updatedMovement: MovementModel
    ): ResponseEntity<Any> {
        val existingMovement = repository.findById(id)

        return if (existingMovement.isPresent) {
            val currentMovement = existingMovement.get()

            if (updatedMovement.description != null && updatedMovement.photo != null && updatedMovement.value != null && updatedMovement.date != null) {
                currentMovement.description = updatedMovement.description
                currentMovement.photo = updatedMovement.photo
                currentMovement.value = updatedMovement.value
                currentMovement.date = updatedMovement.date

                if (currentMovement.recurrenceFrequency != updatedMovement.recurrenceFrequency || currentMovement.recurrenceIntervals != updatedMovement.recurrenceIntervals) {
                    deleteRecurringMovements(currentMovement)

                    if (updatedMovement.recurrenceFrequency != null) {
                        currentMovement.recurrenceFrequency = updatedMovement.recurrenceFrequency
                        currentMovement.recurrenceIntervals = updatedMovement.recurrenceIntervals
                        createRecurringMovements(currentMovement, currentMovement.id!!)
                    }
                }
                if (currentMovement.fixedIncome != updatedMovement.fixedIncome) {
                    if (updatedMovement.fixedIncome) {
                        currentMovement.fixedIncome = true
                        val fixedIncomeMovements = updateFixedIncomeMovement(currentMovement)
                        repository.saveAll(fixedIncomeMovements)
                    } else {
                        currentMovement.fixedIncome = false
                        deleteFixedIncomeMovements(currentMovement)
                    }
                }
                val savedMovement = repository.save(currentMovement)

                val customResponse = CustomResponse(
                    "Movimento atualizado com sucesso", HttpStatus.OK.value()
                )

                ResponseEntity.ok(customResponse)
            } else {
                val customResponse = CustomResponse(
                    "Todos os campos devem estar presentes no corpo da solicitação para atualização.",
                    HttpStatus.BAD_REQUEST.value()
                )
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customResponse)
            }
        } else {
            val customResponse = CustomResponse(
                "Movimento não encontrado, verifique o id.", HttpStatus.NOT_FOUND.value()
            )
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse)
        }
    }

    @PatchMapping("/{id}")
    fun partialUpdateMovementById(
        @PathVariable("id") id: Long, @RequestBody updatedMovement: MovementUpdateRequest
    ): ResponseEntity<Any> {
        val existingMovement = repository.findById(id)

        return if (existingMovement.isPresent) {
            val currentMovement = existingMovement.get()

            updatedMovement.description?.let { currentMovement.description = it }
            updatedMovement.photo?.let { currentMovement.photo = it }
            updatedMovement.value?.let { currentMovement.value = it }
            updatedMovement.date?.let { currentMovement.date = it }

            if (updatedMovement.fixedIncome != null) {
                if (!currentMovement.fixedIncome && updatedMovement.fixedIncome) {
                    currentMovement.fixedIncome = true
                    val fixedIncomeMovements = updateFixedIncomeMovement(currentMovement)
                    repository.saveAll(fixedIncomeMovements)
                } else if (currentMovement.fixedIncome && !updatedMovement.fixedIncome) {
                    currentMovement.fixedIncome = false
                    deleteFixedIncomeMovements(currentMovement)
                }
            }

            if (updatedMovement.recurrenceFrequency != null) {
                deleteRecurringMovements(currentMovement)
                currentMovement.recurrenceFrequency = updatedMovement.recurrenceFrequency
                currentMovement.recurrenceIntervals = updatedMovement.recurrenceIntervals
                val updatedMovements = updateRecurringMovements(currentMovement)
                repository.saveAll(updatedMovements)
            }

            val savedMovement = repository.save(currentMovement)

            val customResponse = CustomResponse(
                "Movimento atualizado com sucesso", HttpStatus.OK.value()
            )

            ResponseEntity.ok(customResponse)
        } else {
            val customResponse = CustomResponse(
                "Movimento não encontrado, verifique o ID.", HttpStatus.NOT_FOUND.value()
            )
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse)
        }
    }


    @DeleteMapping("/{id}")
    fun deleteMovement(@PathVariable(value = "id") id: Long): ResponseEntity<Any> {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "ID de movimento inválido", HttpStatus.BAD_REQUEST.value()
                )
            )
        }

        val childMovement = repository.findById(id)
        return if (childMovement.isPresent) {
            val parentMovementId = childMovement.get().parentMovementId
            val relatedMovements = repository.findByParentMovementId(parentMovementId)
            repository.deleteInBatch(relatedMovements)

            ResponseEntity.ok().body(
                CustomResponse(
                    "Movimento e movimentos relacionados excluídos com sucesso",
                    HttpStatus.OK.value()
                )
            )
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Movimento não encontrado, verifique o id.", HttpStatus.NOT_FOUND.value()
                )
            )
        }
    }


    @GetMapping("/user/{userId}/revenues")
    fun getRevenuesByUserIdAndMonthAndYear(
        @PathVariable("userId") userId: Long,
        @RequestParam("month") month: Int,
        @RequestParam("year") year: Int
    ): ResponseEntity<Any> {
        val revenues = repository.findRevenuesByUserIdAndMonthAndYear(userId, month, year)

        return if (revenues.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Nenhuma receita encontrada para o usuário, mês e ano especificados.",
                    HttpStatus.NOT_FOUND.value()
                )
            )
        } else {
            ResponseEntity.ok(revenues)
        }
    }

    @GetMapping("/user/{userId}/expenses")
    fun getExpensesByUserIdAndMonthAndYear(
        @PathVariable("userId") userId: Long,
        @RequestParam("month") month: Int,
        @RequestParam("year") year: Int
    ): ResponseEntity<Any> {
        val expenses = repository.findExpensesByUserIdAndMonthAndYear(userId, month, year)

        return if (expenses.isEmpty()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Nenhuma despesa encontrada para o usuário, mês e ano especificados.",
                    HttpStatus.NOT_FOUND.value()
                )
            )
        } else {
            ResponseEntity.ok(expenses)
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

    private fun createFixedIncomeMovements(mainMovement: MovementModel, parentMovementId: Long) {
        val currentYearMonth = YearMonth.now()
        val currentMonth = currentYearMonth.monthValue
        val currentYear = currentYearMonth.year

        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val originalDate = LocalDate.parse(mainMovement.date, dateFormatter)

        mainMovement.parentMovementId = parentMovementId
        repository.save(mainMovement)


        for (i in 1 until 12) {
            val newDate = originalDate.plusMonths(i.toLong())
            val newDateString = newDate.format(dateFormatter)
            val newMovement = mainMovement.copy(
                id = null,
                date = newDateString,
                fixedIncome = true,
                parentMovementId = parentMovementId
            )
            repository.save(newMovement)
        }
    }

    private fun createRecurringMovements(mainMovement: MovementModel, parentMovementId: Long) {
        val recurrenceFrequency = mainMovement.recurrenceFrequency
        val recurrenceIntervals = mainMovement.recurrenceIntervals

        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val originalDate = LocalDate.parse(mainMovement.date, dateFormatter)


        mainMovement.parentMovementId = parentMovementId
        repository.save(mainMovement)

        for (i in 1 until recurrenceIntervals!!) {
            val newDate: LocalDate

            when (recurrenceFrequency) {
                "daily" -> {
                    newDate = originalDate.plusDays(i.toLong())
                }

                "weekly" -> {
                    newDate = originalDate.plusWeeks(i.toLong())
                }

                "monthly" -> {
                    newDate = originalDate.plusMonths(i.toLong())
                }

                "annually" -> {
                    val currentYear = originalDate.year
                    val newYear = currentYear + i
                    newDate = originalDate.withYear(newYear)
                }

                else -> {
                    throw IllegalArgumentException("Unsupported recurrence frequency: $recurrenceFrequency")
                }
            }

            val newDateString = newDate.format(dateFormatter)
            val newMovement = mainMovement.copy(
                id = null, date = newDateString, parentMovementId = parentMovementId
            )
            repository.save(newMovement)
        }
    }

    private fun updateFixedIncomeMovement(movement: MovementModel): List<MovementModel> {
        val currentYearMonth = YearMonth.now()
        val currentMonth = currentYearMonth.monthValue
        val currentYear = currentYearMonth.year

        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val originalDate = LocalDate.parse(movement.date, dateFormatter)

        val fixedIncomeMovements = mutableListOf<MovementModel>()

        for (i in 1 until 12) {
            val newDate = originalDate.plusMonths(i.toLong())
            val newDateString = newDate.format(dateFormatter)
            val newMovement = movement.copy(
                id = null, date = newDateString, fixedIncome = true, parentMovementId = movement.id
            )
            fixedIncomeMovements.add(newMovement)
        }

        return fixedIncomeMovements
    }

    private fun updateRecurringMovements(movement: MovementModel): List<MovementModel> {
        val recurrenceFrequency = movement.recurrenceFrequency
        val recurrenceIntervals = movement.recurrenceIntervals

        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val originalDate = LocalDate.parse(movement.date, dateFormatter)

        val updatedMovements = mutableListOf<MovementModel>()

        when (recurrenceFrequency) {
            "daily" -> {
                for (i in 1 until recurrenceIntervals!!) {
                    val newDate = originalDate.plusDays(i.toLong())
                    val newDateString = newDate.format(dateFormatter)
                    val newMovement = movement.copy(id = null, date = newDateString)
                    updatedMovements.add(newMovement)
                }
            }

            "weekly" -> {
                for (i in 1 until recurrenceIntervals!!) {
                    val newDate = originalDate.plusWeeks(i.toLong())
                    val newDateString = newDate.format(dateFormatter)
                    val newMovement = movement.copy(id = null, date = newDateString)
                    updatedMovements.add(newMovement)
                }
            }

            "monthly" -> {
                for (i in 1 until recurrenceIntervals!!) {
                    val newDate = originalDate.plusMonths(i.toLong())
                    val newDateString = newDate.format(dateFormatter)
                    val newMovement = movement.copy(id = null, date = newDateString)
                    updatedMovements.add(newMovement)
                }
            }

            "annually" -> {
                val currentYear = originalDate.year
                for (i in 1 until recurrenceIntervals!!) {
                    val newYear = currentYear + i
                    val newDate = originalDate.withYear(newYear)
                    val newDateString = newDate.format(dateFormatter)
                    val newMovement = movement.copy(id = null, date = newDateString)
                    updatedMovements.add(newMovement)
                }
            }

            else -> {
                throw IllegalArgumentException("Unsupported recurrence frequency: $recurrenceFrequency")
            }
        }

        return updatedMovements
    }

    private fun deleteFixedIncomeMovements(movement: MovementModel) {
        val fixedIncomeMovements =
            repository.findByParentMovementIdAndFixedIncome(movement.id, true)
        repository.deleteAll(fixedIncomeMovements)
    }

    private fun deleteRecurringMovements(movement: MovementModel) {
        val recurringMovements =
            repository.findByParentMovementIdAndRecurrenceFrequencyNotNull(movement.id)
        repository.deleteAll(recurringMovements)
    }

}
