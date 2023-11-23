package com.company.confinance.controller

import com.company.confinance.model.entity.ObjectiveModel
import com.company.confinance.model.response.CustomResponse
import com.company.confinance.model.response.UpdateObjectiveResponse
import com.company.confinance.repository.ObjectiveRepository
import com.company.confinance.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/objective")
class ObjectiveController {

    @Autowired
    private lateinit var repository: ObjectiveRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @PostMapping("/create")
    fun createObjective(
        @RequestBody objective: ObjectiveModel
    ): ResponseEntity<Any> {
        return try {

            if(objective.value == 0.0){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    CustomResponse(
                        "Campos incompletos. Certifique-se de preencher todos os campos obrigatórios.",
                        HttpStatus.BAD_REQUEST.value()
                    )
                )
        }
            val savedObjective = repository.save(objective)

            ResponseEntity.status(HttpStatus.CREATED).body(
                CustomResponse(
                    "Objetivo criado com sucesso!", HttpStatus.CREATED.value(), savedObjective.id
                )
            )
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                CustomResponse(
                    "Erro ao criar objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value()
                )
            )
        }
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: Long): ResponseEntity<Any> {
        val existingObjective = repository.findById(id)
        return when {
            id <= 0 -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "ID do objetivo inválido. Informe um ID válido", HttpStatus.BAD_REQUEST.value()
                )
            )

            existingObjective.isPresent -> {
                repository.deleteById(id)
                ResponseEntity.ok().body(
                    CustomResponse(
                        "Objetivo deletado com sucesso!", HttpStatus.OK.value()
                    )
                )
            }

            else -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Não foi possível deletar o objetivo, verifique o ID informado.",
                    HttpStatus.NOT_FOUND.value()
                )
            )
        }
    }

    @PutMapping("/{id}")
    fun updateObjectiveById(
        @PathVariable(value = "id") id: Long, @RequestBody objective: ObjectiveModel
    ): ResponseEntity<Any> {
        return if (repository.existsById(id)) {
            val existingObjective = repository.findById(id).get()
            existingObjective.apply {
                value = objective.value
                name = objective.name
                date = objective.date
                photo = objective.photo
            }
            repository.save(existingObjective)

            ResponseEntity.ok(
                CustomResponse(
                    "Objetivo atualizado com sucesso!", HttpStatus.OK.value(), existingObjective.id
                )
            )
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Não foi possível atualizar o objetivo, por favor verifique o ID e tente novamente.",
                    HttpStatus.NOT_FOUND.value()
                )
            )
        }
    }

    @PatchMapping("/{id}")
    fun updateObjective(
        @PathVariable id: Long, @RequestBody updatedObjective: UpdateObjectiveResponse
    ): ResponseEntity<Any> {
        return try {
            val existingObjective = repository.findById(id)

            if (existingObjective.isPresent) {
                val currentObjective = existingObjective.get()

                updatedObjective.name?.let { currentObjective.name = it }
                updatedObjective.value?.let { currentObjective.value = it }
                updatedObjective.savedValue?.let { currentObjective.savedValue = it }
                updatedObjective.photo?.let { currentObjective.photo = it }
                updatedObjective.date?.let { currentObjective.date = it }

                val savedObjective = repository.save(currentObjective)

                ResponseEntity.ok(
                    CustomResponse(
                        "Objetivo atualizado com sucesso!", HttpStatus.OK.value(), savedObjective.id
                    )
                )
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    CustomResponse(
                        "Objetivo não encontrado", HttpStatus.NOT_FOUND.value()
                    )
                )
            }
        } catch (ex: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                CustomResponse(
                    "Erro ao atualizar objetivo", HttpStatus.INTERNAL_SERVER_ERROR.value()
                )
            )
        }
    }


    @GetMapping("/{id}")
    fun getObjectiveById(@PathVariable("id") id: Long): ResponseEntity<Any> {
        return if (id <= 0) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "Erro, o ID informado é inválido. Por favor, insira um ID correto.",
                    HttpStatus.BAD_REQUEST.value()
                )
            )
        } else {
            val objective = repository.findById(id)
            if (objective.isPresent) {
                ResponseEntity.ok(objective.get())
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    CustomResponse(
                        "Objetivo não encontrado, verifique o ID e tente novamente.",
                        HttpStatus.NOT_FOUND.value()
                    )
                )
            }
        }
    }

    @GetMapping
    fun getObjectives(): ResponseEntity<Any> {
        return if (repository.findAll().isNotEmpty()) {
            ResponseEntity.ok(repository.findAll())
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Nenhum objetivo encontrado", HttpStatus.NOT_FOUND.value()
                )
            )
        }
    }

    @GetMapping("/user/{userId}")
    fun getObjectivesByUserId(@PathVariable("userId") userId: Long): ResponseEntity<Any> {
        return if (userId <= 0) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "Erro, ID inválido. Por favor, informe um ID correto.",
                    HttpStatus.BAD_REQUEST.value()
                )
            )
        } else {
            val user = userRepository.findById(userId)
            if (user.isPresent) {
                val objectives = repository.findByUserId(userId)
                ResponseEntity.ok(objectives)
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    CustomResponse(
                        "Nenhum objetivo encontrado para o usuário", HttpStatus.NOT_FOUND.value()
                    )
                )
            }
        }
    }
}
