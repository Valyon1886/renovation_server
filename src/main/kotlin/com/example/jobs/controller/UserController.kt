package com.example.jobs.controller

import com.example.jobs.models.Employer
import com.example.jobs.models.Job
import com.example.jobs.models.Material
import com.example.jobs.models.User
import com.example.jobs.repository.UserRepository
import com.example.jobs.services.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/user")
class UserController(private val userService: UserService) {
    @GetMapping("/get/token/{idToken}")
    @ResponseBody
    fun checkIdTokenUser(@PathVariable idToken: String) = userService.checkIdTokenUser(idToken)

    @PostMapping("/add")
    @ResponseBody
    fun addUser(@RequestBody user: User) = userService.addUser(user)

    @GetMapping("/get")
    @ResponseBody
    fun findUser(): List<User> = userService.findUser()

    @GetMapping("/get/idToken/{idToken}")
    @ResponseBody
    fun findUserByIdToken(@PathVariable idToken: String) = userService.findUserByIdToken(idToken)

    @GetMapping("/get/{id}")
    @ResponseBody
    fun findUserById(@PathVariable id: Long) = userService.findUserById(id)

    @PutMapping("/update/{id}")
    @ResponseBody
    fun updateUser(@PathVariable id: Long, @RequestBody userUpdate: User) = userService.updateUser(id, userUpdate)
    
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    fun deleteUser(@PathVariable id: Long) = userService.deleteUser(id)

    @PostMapping("/{userId}/choise/{jobId}")
    @ResponseBody
    fun choiseTask(@PathVariable userId: Long, @PathVariable jobId: Long): User = userService.choiseTask(userId, jobId)

    @PostMapping("/{userId}/finish/{jobId}")
    @ResponseBody
    fun finishTask(@PathVariable userId: Long, @PathVariable jobId: Long): User = userService.finishTask(userId, jobId)

    @GetMapping("/get/allTask/{id}")
    @ResponseBody
    fun getUserTask(@PathVariable id: Long): List<Job> = userService.getUserTask(id)

    @DeleteMapping("/{userId}/deleteTaskFrom/{jobId}")
    @ResponseBody
    fun deleteTask(@PathVariable userId: Long, @PathVariable jobId: Long): User = userService.deleteTask(userId, jobId)

    @GetMapping("/get/allMaterial/{jobId}")
    @ResponseBody
    fun getUserMaterial(@PathVariable jobId: Long): List<Material> = userService.getUserMaterial(jobId)

    @PostMapping("/employer/add/{userId}")
    @ResponseBody
    fun addEmployerToUser(@PathVariable userId: Long, @RequestBody employer: Employer): User = userService.addEmployerToUser(userId, employer)

    @DeleteMapping("/{userId}/deleteEmFrom/{employerId}")
    @ResponseBody
    fun deleteEmployerFromUser(@PathVariable userId: Long, @PathVariable employerId: Long) = userService.deleteEmployerFromUser(userId, employerId)

    @GetMapping("/get/allEmployer/{userId}")
    @ResponseBody
    fun getUserEmployer(@PathVariable userId: Long): List<Employer> = userService.getUserEmployer(userId)
}