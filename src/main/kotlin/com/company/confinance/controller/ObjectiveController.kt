package com.company.confinance.controller

import com.company.confinance.model.ObjectiveModel
import com.company.confinance.repository.ObjectiveRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.management.loading.ClassLoaderRepository


@RestController
@RequestMapping("/objectives")
class ObjectiveController {

     @Autowired
     private  lateinit var repository: ObjectiveRepository

     @PostMapping
     fun create(
         @RequestBody objective : ObjectiveModel
     ): ObjectiveModel {
         return  repository.save(objective)
     }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        return repository.deleteById(id)
    }

    @PutMapping("/{id}")
        fun update(
            @PathVariable id: Long,
            @RequestBody objective: ObjectiveModel
        ): ObjectiveModel {
            return repository.save(objective)
        }

    @GetMapping
    fun getAllObjectives(): List<ObjectiveModel>{
        return repository.findAll();
    }

    @GetMapping("/user/{userId}")
    fun getObjectivesdByUserId(
        @PathVariable("userId") userId: Long
    ): List<ObjectiveModel> {
        return repository.findByUserId(userId)
    }


}