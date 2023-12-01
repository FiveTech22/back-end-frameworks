package com.company.confinance.controller

import com.company.confinance.config.EmailConfig
import com.company.confinance.config.TokenService
import com.company.confinance.model.entity.PasswordRecoveryModel
import com.company.confinance.model.LoginRequest
import com.company.confinance.model.entity.UserModel
import com.company.confinance.model.response.CustomResponse
import com.company.confinance.model.response.PasswordResetRequest
import com.company.confinance.model.response.PasswordResetValidationService
import com.company.confinance.model.response.ResetPassword
import com.company.confinance.model.response.ValidateCode
import com.company.confinance.repository.PasswordRecoveryRepository
import com.company.confinance.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var authenticationManager: AuthenticationManagerBuilder

    @Autowired
    private lateinit var tokenService: TokenService

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

    @Autowired
    private lateinit var validationService: PasswordResetValidationService

    @GetMapping("/{id}")
    fun getUserId(@PathVariable(value = "id") id: Long): ResponseEntity<Any> {
        val user = repository.findById(id)

        return if (id <= 0) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "Erro id informado invalido, Por favor passe o Id correto.",
                    HttpStatus.BAD_REQUEST.value()
                )
            )
        } else if (user.isPresent) {
            ResponseEntity.ok(user.get())
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Usuário não encontrado, verifique o id.", HttpStatus.NOT_FOUND.value()
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
                    "Email já cadastrado, por favor coloque outro.", HttpStatus.FORBIDDEN.value()
                )
            )
        } else {
            user.password = passwordEncoder.encode(user.password)
            val savedUser = repository.save(user)
            ResponseEntity.status(HttpStatus.CREATED).body(
                CustomResponse(
                    "Usuário criado com sucesso.", HttpStatus.CREATED.value(), savedUser.id

                )
            )
        }
    }

    @PutMapping("/{userId}/reset-password")
    fun resetPassword(
        @PathVariable("userId") userId: Long,
        @RequestBody passwordResetRequest: PasswordResetRequest
    ): ResponseEntity<Any> {
        val user = repository.findById(userId)
        if (user.isPresent) {
            val existingUser = user.get()
            if (passwordEncoder.matches(
                    passwordResetRequest.currentPassword, existingUser.password
                )
            ) {
                existingUser.password = passwordEncoder.encode(passwordResetRequest.newPassword)
                repository.save(existingUser)
                return ResponseEntity.ok(
                    CustomResponse(
                        "Senha atualizada com sucesso", HttpStatus.OK.value(), userId
                    )
                )
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    CustomResponse("Senha atual incorreta", HttpStatus.UNAUTHORIZED.value())
                )
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Nenhum usuário encontrado com o ID especificado.", HttpStatus.NOT_FOUND.value()
                )
            )
        }

    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable(value = "id") id: Long, @Valid @RequestBody updatedUser: UserModel
    ): ResponseEntity<Any> {
        val existingUser = repository.findById(id)
        return if (existingUser.isPresent) {
            val user = existingUser.get()
            val existingEmailUser = repository.findByEmail(updatedUser.email)
            if (existingEmailUser != null && existingEmailUser.id != id) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    CustomResponse(
                        "Email já cadastrado, por favor coloque outro.",
                        HttpStatus.FORBIDDEN.value()
                    )
                )
            }

            user.name = updatedUser.name

            if (user.email != updatedUser.email) {
                user.email = updatedUser.email
            }

            user.password = passwordEncoder.encode(updatedUser.password)

            val savedUser = repository.save(user)

            return ResponseEntity.ok(
                CustomResponse(
                    "Atualizado com sucesso.", HttpStatus.OK.value()
                )
            )
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Informações inválidas para o usúario.", HttpStatus.NOT_FOUND.value()
                )
            )
        }
    }


    @PatchMapping("/{id}")
    fun patchUser(
        @PathVariable(value = "id") id: Long, @RequestBody partialUser: UserModel
    ): ResponseEntity<Any> {
        val existingUser = repository.findById(id)
        return if (existingUser.isPresent) {
            val user = existingUser.get()
            val existingEmailUser = repository.findByEmail(partialUser.email)

            if (existingEmailUser != null && existingEmailUser.id != id) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                    CustomResponse(
                        "Email já cadastrado, por favor coloque outro.",
                        HttpStatus.FORBIDDEN.value()
                    )
                )
            }


            if (!partialUser.name.isNullOrBlank()) {
                user.name = partialUser.name
            }
            if (!partialUser.email.isNullOrBlank()) {
                user.email = partialUser.email
            }

            if (!partialUser.password.isNullOrBlank()) {
                user.password = passwordEncoder.encode(partialUser.password)
            }

            if (partialUser.photo != null) {
                user.photo = partialUser.photo
            }


            val updatedUser = repository.save(user)

            return ResponseEntity.ok(
                CustomResponse(
                    "Atualizado com sucesso.", HttpStatus.OK.value()
                )
            )
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
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
                    "ID de usuário inválido", HttpStatus.BAD_REQUEST.value()
                )
            )
        } else if (existingUser.isPresent) {
            repository.deleteById(id)
            ResponseEntity.ok()
                .body(CustomResponse("Usuário Deletado com sucesso", HttpStatus.OK.value()))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "Usuário não encontrado, verifique o id.", HttpStatus.NOT_FOUND.value()
                )
            )
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        val user = repository.findByEmail(loginRequest.email)

        return if (user != null && passwordEncoder.matches(loginRequest.password, user.password)) {
            ResponseEntity.ok(
                CustomResponse(
                    "Login Feito com Sucesso!", HttpStatus.OK.value(), user.id
                )
            )
        } else {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                CustomResponse(
                    "Credenciais Inválidas, verifique seu e-mail e senha.",
                    HttpStatus.UNAUTHORIZED.value()
                )
            )
        }
    }


    @Transactional
    @PostMapping("send-mail/{email}")
    fun sendMailCode(@PathVariable email: String): ResponseEntity<Any> {
        val user = repository.findByEmail(email)

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "E-mail não encontrado no sistema.", HttpStatus.NOT_FOUND.value()
                )
            )
        } else {
            val now = LocalDateTime.now()
            passwordrecoveryrepository.deleteByExpirationTimeBefore(now)
            val existingCode =
                passwordrecoveryrepository.findByEmailAndExpirationTimeAfter(email, now)

            if (existingCode != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    CustomResponse(
                        "Espere passar os 3 minutos antes de enviar novamente!",
                        HttpStatus.BAD_REQUEST.value()
                    )
                )
            }

            val code = emailConfig.generateRandomCode(4)
            val expirationTime = now.plusMinutes(3)

            val recoveryCode = PasswordRecoveryModel(
                email = email, code = code, expirationTime = expirationTime
            )
            passwordrecoveryrepository.save(recoveryCode)

            val message = SimpleMailMessage()
            message.from = emailConfig.getFromAddress()
            message.setTo(email)
            message.subject = "Código de Recuperação de Senha"
            message.text = "Seu código de recuperação de senha é: $code"
            emailSender.send(message)

            return ResponseEntity.ok(
                CustomResponse(
                    "Código de recuperação de senha enviado com sucesso!", HttpStatus.OK.value()
                )
            )
        }
    }


    @PostMapping("/validate-code")
    fun validateCode(@RequestBody validateCode: ValidateCode): ResponseEntity<Any> {
        val recoveryCode = passwordrecoveryrepository.findByEmail(validateCode.email)

        if (recoveryCode == null || recoveryCode.code != validateCode.code) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "Código de recuperação de senha inválido.", HttpStatus.BAD_REQUEST.value()
                )
            )
        }

        val now = LocalDateTime.now()
        if (recoveryCode.expirationTime.isBefore(now)) {
            passwordrecoveryrepository.delete(recoveryCode)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "Código de recuperação de senha expirado.", HttpStatus.BAD_REQUEST.value()
                )
            )
        } else {
            validationService.setValidationResult(validateCode.email, true)
            passwordrecoveryrepository.delete(recoveryCode)


            return ResponseEntity.ok(
                CustomResponse(
                    "Código de recuperação de senha válido.", HttpStatus.OK.value()
                )
            )
        }
    }

    @PostMapping("/reset-password")
    fun resetPassword(@RequestBody resetPassword: ResetPassword): ResponseEntity<Any> {
        val isValid = validationService.getValidationResult(resetPassword.email) ?: false

        if (!isValid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                CustomResponse(
                    "Validação de código não concluída. Por favor, valide o código primeiro.",
                    HttpStatus.BAD_REQUEST.value()
                )
            )
        }

        val user = repository.findByEmail(resetPassword.email)

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                CustomResponse(
                    "E-mail não encontrado no sistema.", HttpStatus.NOT_FOUND.value()
                )
            )
        } else {
            val hashedNewPassword = passwordEncoder.encode(resetPassword.newPassword)
            user.password = hashedNewPassword
            repository.save(user)

            validationService.setValidationResult(resetPassword.email, false)

            return ResponseEntity.ok(
                CustomResponse(
                    "Senha redefinida com sucesso!", HttpStatus.OK.value()
                )
            )
        }
    }
}
