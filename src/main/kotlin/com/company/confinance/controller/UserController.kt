package com.company.confinance.controller

import com.company.confinance.model.UserModel
import com.company.confinance.model.response.CustomResponse
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
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var repository: UserRepository

    @GetMapping("/{id}")
    fun getUserId(@PathVariable(value = "id") id: Long): ResponseEntity<Any> {
        val user = repository.findById(id)
        return if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                    CustomResponse(
                        "Usuário não encontrado, passe o id certo.",
                        HttpStatus.NOT_FOUND.value()
                    )
                )
        }
    }

    @GetMapping
    fun getUser(): List<UserModel> {
        return repository.findAll()
    }

    @PostMapping
    fun createUser(@Valid @RequestBody user: UserModel): ResponseEntity<Any> {
        val existingEmail = repository.findByEmail(user.email)
        return if (existingEmail != null) {
            ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                CustomResponse(
                    "Email já cadastrado, por favor coloque outro.",
                    HttpStatus.FORBIDDEN.value()
                )
            )
        } else {
            ResponseEntity.status(HttpStatus.CREATED)
            ResponseEntity.ok(repository.save(user))
        }
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable(value = "id") id: Long,
        @Valid @RequestBody updatedUser: UserModel): ResponseEntity<Any> {
        val existingUser = repository.findById(id)
        return if (existingUser.isPresent) {
            val user = existingUser.get()
            user.name = updatedUser.name
            user.email = updatedUser.email
            user.password = updatedUser.password
            ResponseEntity.ok(repository.save(user))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Usuário não encontrado, por favor verifique o id fornecido.",
                    HttpStatus.NOT_FOUND.value()
                )
            )
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable(value = "id") id: Long): ResponseEntity<Any> {
        val existingUser = repository.findById(id)
        return if (existingUser.isPresent) {
            repository.deleteById(id)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(CustomResponse("Usuário não encontrado",
                HttpStatus.NOT_FOUND.value()))
        }
    }


}

