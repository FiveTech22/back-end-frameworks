package com.company.confinance.controller

import com.company.confinance.config.EmailConfig
import com.company.confinance.model.entity.PasswordRecoveryModel
import com.company.confinance.model.LoginRequest
import com.company.confinance.model.entity.UserModel
import com.company.confinance.model.response.CustomResponse
import com.company.confinance.model.response.RecoverPassword
import com.company.confinance.model.response.ValidatePassword
import com.company.confinance.repository.PasswordRecoveryRepository
import com.company.confinance.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.annotation.security.PermitAll
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var repository: UserRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var emailSender: JavaMailSender

    @Autowired
    private lateinit var emailConfig: EmailConfig

    @Autowired
    private lateinit var passwordrecoveryrepository: PasswordRecoveryRepository

    @GetMapping("/{id}")
    fun getUserId(@PathVariable(value = "id") id: Long): ResponseEntity<Any> {
        val user = repository.findById(id)

        return if (id <= 0) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                    CustomResponse(
                        "Erro id informado invalido, Por favor passe o Id correto.",
                        HttpStatus.BAD_REQUEST.value()
                    )
                )
        } else if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                    CustomResponse(
                        "Usuário não encontrado, verifique o id.",
                        HttpStatus.NOT_FOUND.value()
                    )
                )
        }
    }

    @GetMapping
    fun getUser(): List<UserModel> {
        return repository.findAll()
    }

    @PostMapping("/create")
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
            user.password = passwordEncoder.encode(user.password)
            val savedUser = repository.save(user)
            ResponseEntity.status(HttpStatus.CREATED).body(
                CustomResponse(
                    "Usuário criado com sucesso.",
                    HttpStatus.CREATED.value()
                )
            )
        }
    }


    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable(value = "id") id: Long,
        @Valid @RequestBody updatedUser: UserModel
    ): ResponseEntity<Any> {
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
        return if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "ID de usuário inválido",
                    HttpStatus.BAD_REQUEST.value()
                )
            )
        } else if (existingUser.isPresent) {
            repository.deleteById(id)
            ResponseEntity.ok()
                .body(CustomResponse("Usuário Deletado com sucesso", HttpStatus.OK.value()))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Usuário não encontrado, verifique o id.",
                    HttpStatus.NOT_FOUND.value()
                )
            )
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        val user = repository.findByEmail(loginRequest.email)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(CustomResponse("Email Inválido, verifique.", HttpStatus.UNAUTHORIZED.value()))
        val passwordMatch = passwordEncoder.matches(loginRequest.password, user.password)
        if (!passwordMatch) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                    CustomResponse(
                        "Senha Inválida, verifique.",
                        HttpStatus.UNAUTHORIZED.value()
                    )
                )
        }
        val response = CustomResponse("Login Feito com Sucesso!", HttpStatus.OK.value(), user.id)
        return ResponseEntity.ok(response)
    }

    @PostMapping("recover-password/{email}")
    fun recoverPassword(@PathVariable email: String): ResponseEntity<Any> {
        val user = repository.findByEmail(email)

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "E-mail não encontrado no sistema.",
                    HttpStatus.NOT_FOUND.value()
                )
            )
        } else {
            val code = emailConfig.generateRandomCode(6)
            val expirationTime = LocalDateTime.now()
                .plusMinutes(3)

            val recoveryCode = PasswordRecoveryModel(
                email = email,
                code = code,
                expirationTime = expirationTime
            )
            passwordrecoveryrepository.save(recoveryCode)

            val message = SimpleMailMessage()
            message.setTo(email)
            message.subject = "Código de Recuperação de Senha"
            message.text = "Seu código de recuperação de senha é: $code"
            emailSender.send(message)

            return ResponseEntity.ok(
                CustomResponse(
                    "Código de recuperação de senha enviado com sucesso!",
                    HttpStatus.OK.value()
                )
            )
        }
    }

    @PostMapping("/validate-password")
    fun validatePassword(@RequestBody validatePassword: ValidatePassword): ResponseEntity<Any> {
        val user = repository.findByEmail(validatePassword.email)

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "E-mail não encontrado no sistema.",
                    HttpStatus.NOT_FOUND.value()
                )
            )
        } else {
            val recoveryCode = passwordrecoveryrepository.findByEmail(validatePassword.email)

            if (recoveryCode == null || recoveryCode.code != validatePassword.code) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    CustomResponse(
                        "Código de recuperação de senha inválido.",
                        HttpStatus.BAD_REQUEST.value()
                    )
                )
            }
            val now = LocalDateTime.now()
            if (recoveryCode.expirationTime.isBefore(now)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    CustomResponse(
                        "Código de recuperação de senha expirado.",
                        HttpStatus.BAD_REQUEST.value()
                    )
                )
            } else {
                passwordrecoveryrepository.delete(recoveryCode)

                val updatedUser = UserModel(
                    id = user.id,
                    name = user.name,
                    email = user.email,
                    password = validatePassword.newPassword
                )
                repository.save(updatedUser)
                return ResponseEntity.ok(
                    CustomResponse(
                        "Senha alterada com Sucesso!",
                        HttpStatus.OK.value()
                    )

                )

            }
        }
    }
}

