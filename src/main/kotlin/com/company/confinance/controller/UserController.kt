package com.company.confinance.controller

import com.company.confinance.model.UserModel
import com.company.confinance.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityNotFoundException
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    private lateinit var repository: UserRepository
    @GetMapping("/{id}")
    fun getUserId(@PathVariable(value = "id") userId:Long): UserModel {
        return repository.findById(userId).orElseThrow{
            EntityNotFoundException()
        }
    }

    @GetMapping
    fun getUser():List<UserModel>{
        return repository.findAll()
    }
    @PostMapping
    fun createUser(@Valid @RequestBody user: UserModel): UserModel {
        return repository.save(user)
    }



}