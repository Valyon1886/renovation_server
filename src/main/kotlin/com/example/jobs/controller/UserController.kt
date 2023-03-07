package com.example.jobs.controller

import com.example.jobs.models.Job
import com.example.jobs.models.User
import com.example.jobs.repository.UserRepository
import com.example.jobs.services.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/user")
class UserController(private val userService: UserService) {
    @PostMapping("/add")
    @ResponseBody
    fun addUser(@RequestBody user: User) = userService.addUser(user)

    @GetMapping("/get")
    @ResponseBody
    fun findUser(): List<User> = userService.findUser()

    @GetMapping("/get/{id}")
    @ResponseBody
    fun findUserById(@PathVariable id: Long) = userService.findUserById(id)

    @PutMapping("/update/{id}")
    @ResponseBody
    fun updateUser(@PathVariable id: Long, @RequestBody user: User) = userService.updateUser(id, user)

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    fun deleteUser(@PathVariable id: Long) = userService.deleteUser(id)
}