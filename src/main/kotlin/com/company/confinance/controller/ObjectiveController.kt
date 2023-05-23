package com.company.confinance.controller

import com.company.confinance.model.entity.ObjectiveModel
import com.company.confinance.model.response.CustomResponse
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
import javax.persistence.EntityNotFoundException


@RestController
@RequestMapping("/objective")
class ObjectiveController {

    @Autowired
    private lateinit var repository: ObjectiveRepository

    @PostMapping
    fun createObjective(
        @RequestBody objective : ObjectiveModel
    ): ResponseEntity <Any> {
         return try {
             ResponseEntity.status(HttpStatus.CREATED).body(repository.save(objective))
         } catch (ex: Exception) {
             ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar objetivo")
         }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: Long
    ): ResponseEntity <Any> {
        val existingObjective = repository.findById(id)
        return if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse("ID do objectivo inválido", HttpStatus.BAD_REQUEST.value())
            )
        } else if (existingObjective.isPresent) {
            repository.deleteById(id)
            ResponseEntity.ok().body(CustomResponse
                ("Objetivo Deletado com sucesso", HttpStatus.OK.value()))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse("Não foi possível deletar o objetivo, verifique o id informado.", HttpStatus.NOT_FOUND.value())
            )

        }
    }

    @PutMapping("/{id}")
    fun updateObjectiveById(
        @PathVariable (value = "id") id: Long,
        @RequestBody objective: ObjectiveModel
    ): ResponseEntity<Any> {
        return if (repository.existsById(id)) {
            val existingObjective = repository.findById(id).get()
            existingObjective.value = objective.value
            existingObjective.description = objective.description
            existingObjective.date = objective.date
            repository.save(existingObjective)
            ResponseEntity.ok(existingObjective)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse("Não foi possível atualizar o objetivo, por favor verefique o id fornecido.", HttpStatus.NOT_FOUND.value())
            )
        }
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